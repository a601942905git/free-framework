package com.free.framework.plateform.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * com.free.framework.plateform.datasource.DynamicDataSource
 * 动态数据源
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSourceKey();
    }
}
