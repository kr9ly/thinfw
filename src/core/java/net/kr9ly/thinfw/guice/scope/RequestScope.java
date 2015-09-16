package net.kr9ly.thinfw.guice.scope;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;

import java.util.HashMap;
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
public class RequestScope implements Scope {

    private Map<Key<?>, Object> objectMap = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> Provider<T> scope(Key<T> key, final Provider<T> unscoped) {
        return () -> {
            if (objectMap.containsKey(key)) {
                return (T) objectMap.get(key);
            }
            T object = unscoped.get();
            objectMap.put(key, object);
            return object;
        };
    }

    public boolean isProvided(Class<?> clazz) {
        return objectMap.containsKey(Key.get(clazz));
    }
}
