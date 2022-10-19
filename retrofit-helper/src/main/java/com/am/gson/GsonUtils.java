/*
 * Copyright (C) 2022 AlexMofer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.am.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Gson辅助器
 * Created by Alex on 2022/5/17.
 */
public class GsonUtils {

    private static Gson mGson;

    private static Gson getGson() {
        if (mGson == null) {
            mGson = new GsonBuilder()
                    .setDateFormat(DateFormat.LONG)
                    .create();
        }
        return mGson;
    }

    /**
     * 实体对象序列化
     *
     * @param src 需要序列化的实体对象
     * @return 格式化的字符串
     */
    public static String toJson(Object src) {
        return getGson().toJson(src);
    }

    /**
     * 范型对象序列化
     *
     * @param src       需要序列化的范型对象
     * @param typeOfSrc 范型类型
     *                  可使用 {@link com.google.gson.reflect.TypeToken} 类辅助。
     *                  例如获取 {@code Collection<Foo>} 类型：
     *                  {@code Type typeOfSrc = new TypeToken<Collection<Foo>>(){}.getType();}
     *                  还可以使用 {@link #getType(Type, Type...)} 静态方法辅助。
     *                  例如获取 {@code Collection<Foo>} 类型：
     *                  {@code Type typeOfSrc = GsonUtils.getType(Collection.class, Foo.class);}
     * @return 格式化的字符串
     */
    public static String toJson(Object src, Type typeOfSrc) {
        return getGson().toJson(src, typeOfSrc);
    }

    /**
     * 范型对象序列化
     *
     * @param src        需要序列化的范型对象
     * @param clazz      类型类
     * @param parameters 类型参数类
     * @return 格式化的字符串
     */
    public static String toJson(Object src, Class<?> clazz, Class<?>... parameters) {
        return toJson(src, getType(clazz, parameters));
    }

    /**
     * List对象序列化
     *
     * @param list  需要序列化的List对象
     * @param clazz 类型参数类
     * @param <T>   目标类型
     * @return 格式化的字符串
     */
    public static <T> String listToJson(List<T> list, Class<T> clazz) {
        return toJson(list, List.class, clazz);
    }

    /**
     * 反序列实体对象
     *
     * @param json     字符串
     * @param classOfT 实体类型
     * @param <T>      目标类型
     * @return 实体对象
     * @throws JsonSyntaxException 错误
     */
    public static <T> T fromJsonOrThrow(String json, Class<T> classOfT) throws JsonSyntaxException {
        return getGson().fromJson(json, classOfT);
    }

    /**
     * 反序列实体对象
     *
     * @param json     字符串
     * @param classOfT 实体类型
     * @param <T>      目标类型
     * @return 实体对象
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        if (json == null || json.length() == 0)
            return null;
        try {
            return fromJsonOrThrow(json, classOfT);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    /**
     * 反序列范型对象
     *
     * @param json    字符串
     * @param typeOfT 范型类型
     *                可使用 {@link com.google.gson.reflect.TypeToken} 类辅助。
     *                例如获取 {@code Collection<Foo>} 类型：
     *                {@code Type typeOfSrc = new TypeToken<Collection<Foo>>(){}.getType();}
     *                还可以使用 {@link #getType(Type, Type...)} 静态方法辅助。
     *                例如获取 {@code Collection<Foo>} 类型：
     *                {@code Type typeOfSrc = GsonUtils.getType(Collection.class, Foo.class);}
     * @param <T>     目标类型
     * @return 泛型对象
     * @throws JsonSyntaxException 错误
     */
    public static <T> T fromJsonOrThrow(String json, Type typeOfT) throws JsonSyntaxException {
        return getGson().fromJson(json, typeOfT);
    }

    /**
     * 反序列范型对象
     *
     * @param json    字符串
     * @param typeOfT 范型类型
     *                可使用 {@link com.google.gson.reflect.TypeToken} 类辅助。
     *                例如获取 {@code Collection<Foo>} 类型：
     *                {@code Type typeOfSrc = new TypeToken<Collection<Foo>>(){}.getType();}
     *                还可以使用 {@link #getType(Type, Type...)} 静态方法辅助。
     *                例如获取 {@code Collection<Foo>} 类型：
     *                {@code Type typeOfSrc = GsonUtils.getType(Collection.class, Foo.class);}
     * @param <T>     目标类型
     * @return 泛型对象
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        if (json == null || json.length() == 0)
            return null;
        try {
            return fromJsonOrThrow(json, typeOfT);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    /**
     * 反序列范型对象
     *
     * @param json       字符串
     * @param clazz      类型类
     * @param parameters 类型参数类
     * @param <T>        目标类型
     * @return 泛型对象
     */
    public static <T> T fromJson(String json, Class<?> clazz, Class<?>... parameters) {
        return fromJson(json, getType(clazz, parameters));
    }

    /**
     * 反序列List对象
     *
     * @param json  字符串
     * @param clazz 类型参数类
     * @param <T>   目标类型
     * @return List对象
     */
    public static <T> List<T> listFromJson(String json, Class<T> clazz) {
        return fromJson(json, List.class, clazz);
    }

    /**
     * 获取类型
     *
     * @param type       类型
     * @param parameters 类型参数
     * @return 类型
     */
    public static ParameterizedType getType(Type type, Type... parameters) {
        return new ParameterizedTypeImpl(type, parameters);
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {

        private final Type mRaw;
        private final Type[] mArguments;

        public ParameterizedTypeImpl(Type raw, Type... arguments) {
            mRaw = raw;
            if (arguments == null || arguments.length <= 0) {
                mArguments = new Type[0];
            } else {
                mArguments = Arrays.copyOf(arguments, arguments.length);
            }
        }

        @Override
        public Type[] getActualTypeArguments() {
            return mArguments;
        }

        @Override
        public Type getRawType() {
            return mRaw;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
