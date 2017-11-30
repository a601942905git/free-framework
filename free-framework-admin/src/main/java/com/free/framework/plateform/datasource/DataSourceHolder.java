package com.free.framework.plateform.datasource;

public class DataSourceHolder {

    public static final ThreadLocal<String> DATA_SOURCE_KEY_HOLDER = new ThreadLocal<>();

    public static void setDataSourceKey(String key) {
        DATA_SOURCE_KEY_HOLDER.set(key);
    }

    public static String getDataSourceKey() {
        return DATA_SOURCE_KEY_HOLDER.get();
    }

    public static void removeDataSourceKey() {
        DATA_SOURCE_KEY_HOLDER.remove();
    }
}
