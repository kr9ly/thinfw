package net.kr9ly.thinfw.authentication.token;

import org.apache.shiro.authc.AuthenticationToken;

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
public class LoginAuthenticationToken implements AuthenticationToken {

    private String userName;

    private String password;

    public LoginAuthenticationToken(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}