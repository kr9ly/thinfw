package net.kr9ly.thinfw.authentication.realm;

import net.kr9ly.thinfw.authentication.token.LoginAuthenticationToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.JdbcUtils;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;

import java.sql.SQLException;

import static org.jooq.impl.DSL.using;
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
public class LoginRealm extends JdbcRealm {

    private SQLDialect dialect;

    private Settings settings;

    public LoginRealm(SQLDialect dialect, Settings settings) {
        this.dialect = dialect;
        this.settings = settings;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LoginAuthenticationToken loginToken = (LoginAuthenticationToken) token;
        String username = loginToken.getUserName();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        DSLContext dslContext = null;
        SimpleAuthenticationInfo info = null;
        try {
            dslContext = using(dataSource.getConnection(), dialect, settings);

            String password = dslContext.select(USERCREDENTIALS.PASSWORDHASH)
                    .from(USERCREDENTIALS)
                    .where(USERCREDENTIALS.AUTHKEY.equal(username))
                    .fetchOne(USERCREDENTIALS.PASSWORDHASH);

            if (password == null) {
                throw new UnknownAccountException("No account found for user [" + username + "]");
            }

            info = new SimpleAuthenticationInfo(username, password.toCharArray(), getName());
        } catch (SQLException e) {
            final String message = "There was a SQL error while authenticating user [" + username + "]";

            // Rethrow any SQL errors as an authentication exception
            throw new AuthenticationException(message, e);
        } finally {
            if (dslContext != null) {
                dslContext.close();
            }
        }

        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return super.doGetAuthorizationInfo(principals);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof LoginAuthenticationToken;
    }
}
