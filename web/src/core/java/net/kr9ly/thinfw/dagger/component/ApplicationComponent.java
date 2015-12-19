package net.kr9ly.thinfw.dagger.component;

import dagger.Component;
import net.kr9ly.dagger.module.DatabaseEnvionmentModule;
import net.kr9ly.thinfw.dagger.module.AppSecurityManagerModule;
import net.kr9ly.thinfw.dagger.module.DatabaseModule;
import net.kr9ly.thinfw.dagger.module.SecurityModule;
import net.kr9ly.thinfw.dagger.scope.ApplicationScope;
import org.apache.shiro.mgt.SecurityManager;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;

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
@ApplicationScope
@Component(modules = {
        DatabaseModule.class,
        DatabaseEnvionmentModule.class,
        SecurityModule.class,
        AppSecurityManagerModule.class
})
public interface ApplicationComponent {

    SQLDialect sqlDialect();

    Settings settings();

    DataSource dataSource();

    SecurityManager securityManager();
}
