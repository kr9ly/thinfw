package net.kr9ly.thinfw.request;

import net.kr9ly.doubler.CustomProvider;
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
public class RequestParamProvider implements CustomProvider {

    private Request request;

    public RequestParamProvider(Request request) {
        this.request = request;
    }

    @Override
    public <T> T provide(Class<T> aClass) {
        return new RequestProxy<>(request, aClass).toProxy();
    }
}
