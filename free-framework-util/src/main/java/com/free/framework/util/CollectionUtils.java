package com.free.framework.util;

import java.util.Collection;
import java.util.Map;

/**
 * com.free.framework.util.CollectionUtils
 *
 * @author lipeng
 * @dateTime 2017/8/6 12:35
 */
public class CollectionUtils {

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null || !map.isEmpty();
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
