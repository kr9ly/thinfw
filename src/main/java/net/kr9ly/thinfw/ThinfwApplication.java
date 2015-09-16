package net.kr9ly.thinfw;

import net.kr9ly.thinfw.controller.Index;
import net.kr9ly.thinfw.controller.Session;
import net.kr9ly.thinfw.database.filter.DatabaseFilter;
import net.kr9ly.thinfw.guice.filter.DependencyFilter;
import net.kr9ly.thinfw.module.DatabaseModule;
import net.kr9ly.thinfw.module.ServicesModule;
import spark.servlet.SparkApplication;
import spark.template.pebble.PebbleTemplateEngine;

import static spark.Spark.*;

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
public class ThinfwApplication implements SparkApplication {

    @Override
    public void init() {
        before(new DependencyFilter(
                new ServicesModule(),
                new DatabaseModule()
        ));
        after(DatabaseFilter::close);

        PebbleTemplateEngine pebble = new PebbleTemplateEngine();
        get("/", Index::index, pebble);
        get("/login", Session::login, pebble);
    }
}
