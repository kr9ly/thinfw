package net.kr9ly.thinfw.request;

import spark.QueryParamsMap;
import spark.Request;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
public class RequestProxy<T> {

    private Request request;

    private Class<T> paramClass;

    private Object proxy;

    public RequestProxy(Request request, Class<T> paramClass) {
        this.request = request;
        this.paramClass = paramClass;
        proxy = Proxy.newProxyInstance(
                paramClass.getClassLoader(),
                new Class[]{ paramClass },
                new RequestHandler(request, request.queryMap())
        );
    }

    public T toProxy() {
        return paramClass.cast(proxy);
    }

    private static class RequestHandler implements InvocationHandler {

        private Request request;

        private QueryParamsMap queryParamsMap;

        public RequestHandler(Request request, QueryParamsMap queryParamsMap) {
            this.request = request;
            this.queryParamsMap = queryParamsMap;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String paramName = toParamName(method.getName());
            if (paramName == null) {
                return null;
            }
            QueryParamsMap value = this.queryParamsMap.get(paramName);
            Class<?> returnType = method.getReturnType();
            if (returnType == String.class) {
                return value.hasValue() ? value.value() : null;
            } else if (returnType.isPrimitive()) {
                if (returnType == int.class) {
                    return value.hasValue() ? value.integerValue() : 0;
                } else if (returnType == long.class) {
                    return value.hasValue() ? value.longValue() : 0L;
                } else if (returnType == boolean.class) {
                    return value.hasValue() ? value.booleanValue() : false;
                } else if (returnType == float.class) {
                    return value.hasValue() ? value.floatValue() : 0f;
                } else if (returnType == double.class) {
                    return value.hasValue() ? value.doubleValue() : 0d;
                }
                throw new RuntimeException("Unsupported primitive type:" + returnType);
            } else if (returnType.isInterface()) {
                if (!value.hasKeys()) {
                    return null;
                }
                return returnType.cast(Proxy.newProxyInstance(
                        returnType.getClassLoader(),
                        new Class[]{ returnType },
                        new RequestHandler(request, value)
                ));
            }
            return null;
        }

        private String toParamName(String methodName) {
            if (methodName.startsWith("get")) {
                return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
            } else if (methodName.startsWith("is")) {
                return methodName.substring(2, 3).toLowerCase() + methodName.substring(3);
            }
            return null;
        }
    }
}
