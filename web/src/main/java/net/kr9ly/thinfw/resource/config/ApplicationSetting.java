package net.kr9ly.thinfw.resource.config;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import net.kr9ly.thinfw.inject.AppInject;
import net.kr9ly.thinfw.inject.AppInjectFactoryProvider;
import net.kr9ly.thinfw.resource.IndexResource;
import net.kr9ly.thinfw.template.PebbleProcessor;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

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
@ApplicationPath("/")
public class ApplicationSetting extends ResourceConfig {

    public ApplicationSetting() {
        packages(true, IndexResource.class.getPackage().getName());

        register(PebbleProcessor.class);
        register(MvcFeature.class);
        register(LoggingFilter.class);

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(AppInjectFactoryProvider.class)
                        .to(ValueFactoryProvider.class)
                        .in(Singleton.class);

                bind(AppInjectFactoryProvider.InjectionResolver.class)
                        .to(new TypeLiteral<InjectionResolver<AppInject>>() {
                        })
                        .in(Singleton.class);
            }
        });

        register(ApiListingResource.class);
        register(SwaggerSerializers.class);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage(IndexResource.class.getPackage().getName());
        beanConfig.setScan(true);
    }
}
