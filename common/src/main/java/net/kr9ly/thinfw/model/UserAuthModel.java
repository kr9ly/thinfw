package net.kr9ly.thinfw.model;

import net.kr9ly.thinfw.entity.db.ImmutableUser;
import net.kr9ly.thinfw.entity.db.User;
import net.kr9ly.thinfw.providers.ModelProviders;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.jooq.DSLContext;

import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

import static net.kr9ly.thinfw.db.Tables.*;

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
@ModelProviders
public class UserAuthModel {

    @Inject
    Subject subject;

    @Inject
    DSLContext dslContext;

    public Serializable login(String authKey, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(authKey, password);
        try {
            subject.login(token);
            User user = dslContext.select()
                    .from(USERS)
                    .join(USERCREDENTIALS).on(USERS.USERID.equal(USERCREDENTIALS.USERID))
                    .where(USERCREDENTIALS.AUTHKEY.equal(authKey))
                    .fetchOneInto(ImmutableUser.class);
            Session session = subject.getSession(true);
            session.setAttribute("user", user);
            return session.getId();
        } catch (AuthenticationException e) {
            return null;
        }
    }
}
