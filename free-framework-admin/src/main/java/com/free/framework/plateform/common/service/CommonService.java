package com.free.framework.plateform.common.service;

import com.free.framework.plateform.common.controller.param.BaseParam;
import com.free.framework.plateform.constant.SystemConstants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
public class CommonService<T> {

    /**
     * 分页
     * @param baseParam  前端提交参数
     */
    public void startPage(BaseParam baseParam) {
        Integer pageNo = baseParam.getPageNo();
        pageNo = null == pageNo ? SystemConstants.PAGE_NO : pageNo;
        Integer pageSize = baseParam.getPageSize();
        pageSize = null == pageSize ? SystemConstants.PAGE_SIZE : pageSize;
        PageHelper.startPage(pageNo, pageSize);
    }

    /**
     * 获取分页数据
     * @param t
     * @return
     */
    public PageInfo<T> getPageInfo(List<T> t) {
        return new PageInfo<>(t, SystemConstants.navigatePages);
    }
}
