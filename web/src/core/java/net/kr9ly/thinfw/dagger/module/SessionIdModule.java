package net.kr9ly.thinfw.dagger.module;

import dagger.Module;
import dagger.Provides;
import net.kr9ly.thinfw.dagger.scope.RequestScope;
import net.kr9ly.thinfw.session.RequestSessionIdProvider;
import net.kr9ly.thinfw.session.SessionIdProvider;

import javax.servlet.http.HttpServletRequest;

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
@Module
public class SessionIdModule {

    private HttpServletRequest request;

    public SessionIdModule(HttpServletRequest request) {
        this.request = request;
    }

    @RequestScope
    @Provides
    SessionIdProvider sessionIdProvider() {
        return new RequestSessionIdProvider(request);
    }
}
