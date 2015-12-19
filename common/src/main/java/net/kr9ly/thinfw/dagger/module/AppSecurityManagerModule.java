package net.kr9ly.thinfw.dagger.module;

import dagger.Module;
import dagger.Provides;
import net.kr9ly.thinfw.dagger.scope.ApplicationScope;
import net.kr9ly.thinfw.shiro.realms.RealmSet;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;

import javax.sql.DataSource;

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
public class AppSecurityManagerModule {

    @ApplicationScope
    @Provides
    RealmSet realmSet(DataSource dataSource) {
        DefaultPasswordService passwordService = new DefaultPasswordService();
        PasswordMatcher passwordMatcher = new PasswordMatcher();
        passwordMatcher.setPasswordService(passwordService);

        JdbcRealm realm = new JdbcRealm();
        realm.setName("default");
        realm.setAuthenticationQuery("SELECT passwordHash FROM User JOIN UserCredentials ON User.userId = UserCredentials.userId WHERE AuthKey = ?");
        realm.setCredentialsMatcher(passwordMatcher);
        realm.setDataSource(dataSource);

        RealmSet realms = new RealmSet();
        realms.add(realm);
        return realms;
    }

    @ApplicationScope
    @Provides
    SessionManager sessionManager() {
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
        sessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        sessionManager.setCacheManager(new EhCacheManager());
        return sessionManager;
    }
}
