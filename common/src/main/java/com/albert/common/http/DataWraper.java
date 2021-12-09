package com.albert.common.http;

import androidx.annotation.NonNull;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.albert.common.gson.GsonHelper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * code message data 形式
 *
 * @Copyright : Copyright (c) 2018.
 * @authour : jiaoya.
 * @Created Time : 2018/4/23.
 */
public class DataWraper<T> {

    @SerializedName("code")
    public String code;

    @SerializedName("body")
    public T data;

    @SerializedName("message")
    public String message;

    @NonNull
    public static Converter.Factory getFactory() {
        return getFactory("200");
    }

    @NonNull
    public static Converter.Factory getFactory(final String code) {
        return new Converter.Factory() {
            @Override
            public Converter<ResponseBody, ?> responseBodyConverter(@NotNull final Type type, @NotNull Annotation[] annotations, @NotNull final Retrofit retrofit) {

                return new Converter<ResponseBody, Object>() {
                    @Override
                    public Object convert(ResponseBody value) throws IOException {

                        final Type wraperType = new ParameterizedType() {
                            @Override
                            public Type[] getActualTypeArguments() {
                                Type[] types = new Type[1];
                                types[0] = type;
                                return types;
                            }

                            @Override
                            public Type getRawType() {
                                return DataWraper.class;
                            }

                            @Override
                            public Type getOwnerType() {
                                return DataWraper.class;
                            }
                        };

                        TypeAdapter<?> adapter = GsonHelper.getInstance().getAdapter(TypeToken.get(wraperType));
                        JsonReader jsonReader = GsonHelper.getInstance().newJsonReader(value.charStream());
                        DataWraper dataWraper = (DataWraper) adapter.read(jsonReader);

                        if (code.equals(dataWraper.code)) {
                            if (type == Null.class) {
                                return Null.INSTANCE;
                            } else if (null == dataWraper.data) {
                                try {
                                    return ((Class) type).newInstance();
                                } catch (Exception e) {
                                    throw new IOException(e);
                                }
                            } else {
                                return dataWraper.data;
                            }
                        } else {
                            // 返回错误码处理
                            throw HttpApiExceptionEngine.handleException(dataWraper.code, dataWraper.data, dataWraper.message);
                        }
                    }
                };
            }

            @Override
            public Converter<?, RequestBody> requestBodyConverter(Type type, @NotNull Annotation[]
                    parameterAnnotations, @NotNull Annotation[] methodAnnotations, @NotNull Retrofit retrofit) {
                return GsonConverterFactory.create().requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
            }

            @Override
            public Converter<?, String> stringConverter(@NotNull Type type, @NotNull Annotation[] annotations, @NotNull Retrofit retrofit) {
                return GsonConverterFactory.create().stringConverter(type, annotations, retrofit);
            }
        };
    }
}
