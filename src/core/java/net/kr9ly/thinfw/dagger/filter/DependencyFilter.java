package net.kr9ly.thinfw.dagger.filter;

import net.kr9ly.thinfw.dagger.component.ApplicationComponent;
import net.kr9ly.thinfw.dagger.component.DaggerRequestComponent;
import net.kr9ly.thinfw.dagger.module.ConnectionModule;
import net.kr9ly.thinfw.dagger.module.JooqModule;
import net.kr9ly.thinfw.dagger.module.RequestModule;
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

    private final ApplicationComponent applicationComponent;

    public DependencyFilter(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        request.attribute(DEPENDENCY_INJECTOR_ATTRIBUTE,
                DaggerRequestComponent.builder()
                        .applicationComponent(applicationComponent)
                        .requestModule(new RequestModule(request))
                        .connectionModule(new ConnectionModule())
                        .jooqModule(new JooqModule())
                        .build()
        );
    }
}
