package net.kr9ly.thinfw.template;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.spi.TemplateProcessor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Copyright 2016 kr9ly
 * <br />
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <br />
 * http://www.apache.org/licenses/LICENSE-2.0
 * <br />
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@Provider
public class PebbleProcessor implements TemplateProcessor<String> {

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @Context
    private ServletContext servletContext;

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private Lock readLock = rwLock.readLock();

    private Lock writeLock = rwLock.writeLock();

    private PebbleEngine engine;

    @Override
    public String resolve(String name, MediaType mediaType) {
        return name;
    }

    @Override
    public void writeTo(String templateReference, Viewable viewable, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream out) throws IOException {
        try {
            readLock.lock();
            if (engine == null) {
                try {
                    readLock.unlock();
                    writeLock.lock();
                    if (engine == null) {
                        ClasspathLoader loader = new ClasspathLoader();
                        loader.setPrefix("/template/");
                        loader.setSuffix(".pebble");
                        engine = new PebbleEngine.Builder()
                                .loader(loader)
                                .build();
                    }
                } finally {
                    readLock.lock();
                    writeLock.unlock();
                }
            }

            OutputStreamWriter writer = new OutputStreamWriter(out);
            try {
                PebbleTemplate template = engine.getTemplate(templateReference.substring(1));
                template.evaluate(writer, new HashMap<String, Object>() {{
                    put("this", viewable.getModel());
                }});
            } catch (PebbleException e) {
                writer.write(e.getLocalizedMessage());
            }
            writer.flush();
        } finally {
            readLock.unlock();
        }
    }
}
