package com.free.framework.plateform.datasource;

/**
 * com.free.framework.plateform.datasource.DataSourceHolder
 * 数据源持有类
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
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
