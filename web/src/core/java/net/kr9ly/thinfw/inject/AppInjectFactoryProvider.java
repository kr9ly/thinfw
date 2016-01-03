package net.kr9ly.thinfw.inject;

import net.kr9ly.thinfw.dagger.component.ApplicationComponent;
import net.kr9ly.thinfw.dagger.component.DaggerApplicationComponent;
import net.kr9ly.thinfw.dagger.component.DaggerRequestComponent;
import net.kr9ly.thinfw.dagger.component.RequestComponent;
import net.kr9ly.thinfw.dagger.module.DatabaseModule;
import net.kr9ly.thinfw.dagger.module.SessionIdModule;
import net.kr9ly.thinfw.providers.AppProvidersModuleSupportHelper;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.internal.inject.ParamInjectionResolver;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

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
@Singleton
public class AppInjectFactoryProvider extends AbstractValueFactoryProvider {

    private static ApplicationComponent applicationComponent
            = DaggerApplicationComponent
            .builder()
            .databaseModule(new DatabaseModule())
            .build();

    /**
     * Initialize the provider.
     *
     * @param mpep              multivalued map parameter extractor provider.
     * @param locator           HK2 service locator.
     */
    @Inject
    public AppInjectFactoryProvider(MultivaluedParameterExtractorProvider mpep, ServiceLocator locator) {
        super(mpep, locator, Parameter.Source.UNKNOWN);
    }

    @Singleton
    public static final class InjectionResolver extends ParamInjectionResolver<AppInject> {

        /**
         * Initialize the base parameter injection resolver.
         *
         */
        public InjectionResolver() {
            super(AppInjectFactoryProvider.class);
        }
    }

    private static final class AppInjectFactory extends AbstractContainerRequestValueFactory<Object> {

        private static final String ATTRIBUTE_REQUEST_COMPONENT = "REQUEST_COMPONENT";

        private Class<?> rawType;

        @Context
        private HttpServletRequest request;

        public AppInjectFactory(Class<?> rawType) {
            this.rawType = rawType;
        }

        @Override
        public Object provide() {
            RequestComponent requestComponent = (RequestComponent) request.getAttribute(ATTRIBUTE_REQUEST_COMPONENT);
            if (requestComponent == null) {
                requestComponent = DaggerRequestComponent.builder()
                        .applicationComponent(applicationComponent)
                        .sessionIdModule(new SessionIdModule(request))
                        .build();
                request.setAttribute(ATTRIBUTE_REQUEST_COMPONENT, requestComponent);
            }

            return AppProvidersModuleSupportHelper.get(requestComponent, rawType);
        }
    }

    @Override
    protected Factory<?> createValueFactory(Parameter parameter) {
        return new AppInjectFactory(parameter.getRawType());
    }
}
