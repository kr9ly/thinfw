package net.kr9ly.thinfw.module;

import com.google.inject.AbstractModule;
import net.kr9ly.thinfw.guice.annotation.RequestScoped;
import net.kr9ly.thinfw.provider.service.SampleServiceProvider;
import net.kr9ly.thinfw.service.SampleService;

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
public class ServicesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SampleService.class).toProvider(SampleServiceProvider.class).in(RequestScoped.class);
    }
}
