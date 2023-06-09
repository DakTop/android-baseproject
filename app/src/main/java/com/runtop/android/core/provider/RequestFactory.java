package com.runtop.android.core.provider;

import java.util.HashMap;
import java.util.Map;

public class RequestFactory {

    private RequestFactory() {

    }

    public static RequestBuilder obtainRequest() {
        return new RequestBuilder();
    }

   public static class RequestBuilder {
        private Map<String, Object> baseParam = new HashMap<>();

        public RequestBuilder() {
//            baseParam.put("app_id", "");
        }

        public RequestBuilder addParam(String key, Object val) {
            baseParam.put(key, val);
            return this;
        }

        public Map<String, Object> build() {
            return baseParam;
        }
    }

}
