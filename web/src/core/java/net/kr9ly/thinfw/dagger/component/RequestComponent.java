package net.kr9ly.thinfw.dagger.component;

import dagger.Component;
import net.kr9ly.thinfw.dagger.module.*;
import net.kr9ly.thinfw.dagger.scope.RequestScope;
import net.kr9ly.thinfw.providers.*;

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
@RequestScope
@Component(modules = {
        RequestModule.class,
        ConnectionModule.class,
        JooqModule.class,
        AppSubjectModule.class,
        SessionIdModule.class,
        FormProvidersModule.class,
        ControllerProvidersModule.class,
        ModelProvidersModule.class
}, dependencies = {ApplicationComponent.class})
public interface RequestComponent extends ConnectionModuleSupport, JooqModuleSupport, ModelProvidersModuleSupport, ControllerProvidersModuleSupport {
}
