package com.runtop.android.baselibrary.retrofit.converter;

import com.alibaba.fastjson.JSON;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

final class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(T value) {
        return RequestBody.create(JSON.toJSONBytes(value), MEDIA_TYPE);
    }

}
