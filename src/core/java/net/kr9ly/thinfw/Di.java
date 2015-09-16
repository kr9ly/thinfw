package net.kr9ly.thinfw;

import com.google.inject.Injector;
import net.kr9ly.thinfw.guice.annotation.RequestScoped;
import net.kr9ly.thinfw.guice.filter.DependencyFilter;
import net.kr9ly.thinfw.guice.scope.RequestScope;
import spark.Request;

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
public class Di {

    public static <T> T require(Request request, Class<T> clazz) {
        return ((Injector) request.attribute(DependencyFilter.DEPENDENCY_INJECTOR_ATTRIBUTE)).getInstance(clazz);
    }

    public static boolean isProvidedOn(Request request, Class<?> clazz) {
        return ((RequestScope) ((Injector) request.attribute(DependencyFilter.DEPENDENCY_INJECTOR_ATTRIBUTE)).getScopeBindings().get(RequestScoped.class)).isProvided(clazz);
    }
}
