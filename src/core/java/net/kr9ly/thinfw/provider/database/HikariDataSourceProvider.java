package net.kr9ly.thinfw.provider.database;

import com.google.inject.Provider;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Map;

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
public class HikariDataSourceProvider implements Provider<HikariDataSource> {

    @Override
    public HikariDataSource get() {
        Config dbConf = ConfigFactory.load().getConfig("db");
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(dbConf.getString("driver"));
        config.setJdbcUrl(dbConf.getString("jdbcUrl"));
        config.setUsername(dbConf.getString("username"));
        config.setPassword(dbConf.getString("password"));
        config.setMaximumPoolSize(dbConf.getInt("maxPoolSize"));
        for (Map.Entry<String, ConfigValue> entry : dbConf.getConfig("properties").entrySet()) {
            config.addDataSourceProperty(entry.getKey(), entry.getValue().render());
        }

        return new HikariDataSource(config);
    }
}
