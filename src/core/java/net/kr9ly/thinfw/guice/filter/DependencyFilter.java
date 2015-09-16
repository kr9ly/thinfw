package net.kr9ly.thinfw.guice.filter;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import net.kr9ly.thinfw.Di;
import net.kr9ly.thinfw.guice.module.ApplicationScopeModule;
import net.kr9ly.thinfw.guice.module.RequestScopeModule;
import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Copyright 2015 kr9ly
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
public class DependencyFilter implements Filter {

    public static final String DEPENDENCY_INJECTOR_ATTRIBUTE = "DEPENDENCY_INJECTOR_ATTRIBUTE";

    private static final Injector globalInjector = Guice.createInjector(new ApplicationScopeModule());

    private final Iterable<Module> modules;

    public DependencyFilter(Module... modules) {
        this.modules = Lists.asList(new RequestScopeModule(), modules);
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        request.attribute(DEPENDENCY_INJECTOR_ATTRIBUTE,
                globalInjector.createChildInjector(modules)
        );
    }
}
