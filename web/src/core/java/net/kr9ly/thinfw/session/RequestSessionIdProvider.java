package net.kr9ly.thinfw.session;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import javax.servlet.http.Cookie;
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
public class RequestSessionIdProvider implements SessionIdProvider {

    private HttpServletRequest request;

    public RequestSessionIdProvider(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getSessionId() {
        Config sessionConf = ConfigFactory.load().getConfig("session");
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(sessionConf.getString("cookieKey"))) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
