package net.kr9ly.thinfw.swagger;

import com.google.common.io.Files;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertNotNull;

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
public class SwaggerTest {

    @Test
    public void getSwaggerJson() throws IOException {
        String response = ClientBuilder.newClient()
                .target("http://localhost:8080/swagger.json")
                .request()
                .get(String.class);

        assertNotNull(response);

        File dir = new File("docs/swagger");
        dir.mkdirs();

        File file = new File(dir, "swagger.json");
        Files.write(response.getBytes("UTF-8"), file);
    }
}
