package net.kr9ly.thinfw.dagger.module;

import dagger.Module;
import dagger.Provides;
import net.kr9ly.thinfw.dagger.scope.ApplicationScope;
import net.kr9ly.thinfw.shiro.realms.RealmSet;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;

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
public class SecurityModule {

    @ApplicationScope
    @Provides
    SecurityManager securityManager(RealmSet realmSet, SessionManager sessionManager) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager(realmSet);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
}
