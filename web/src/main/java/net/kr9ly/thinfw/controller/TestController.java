package net.kr9ly.thinfw.controller;

import net.kr9ly.thinfw.form.TestForm;
import net.kr9ly.thinfw.providers.ControllerProviders;

import javax.inject.Inject;

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
@ControllerProviders
public class TestController {

    @Inject
    TestForm testForm;

    public void test() {
        System.out.println(testForm.getA());
        System.out.println(testForm.getB());
        System.out.println(testForm.getC());
    }
}
