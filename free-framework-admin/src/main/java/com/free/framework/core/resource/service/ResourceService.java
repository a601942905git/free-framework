package com.free.framework.core.resource.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.framework.core.resource.controller.param.ResourceParam;
import com.free.framework.core.resource.entity.Resource;
import com.free.framework.core.resource.mapper.ResourceMapper;
import com.free.framework.plateform.common.service.CommonService;
import com.github.pagehelper.PageInfo;
/**
 * 操作相关
 */
@Service
@Slf4j
public class ResourceService extends CommonService {

	@Autowired
	private ResourceMapper resourceMapper;
	
	/**
	 * 查询列表信息
	 * @param resourceParam
	 * @return
	 */
	public PageInfo<Resource> pageResource(ResourceParam resourceParam){
		// 分页
		startPage(resourceParam);
		// 用户列表
		List<Resource> resourceList = resourceMapper.listResource(resourceParam);
		// 设置分页信息
    	PageInfo<Resource> pageResource = new PageInfo(resourceList);
        return pageResource;
	}
	
	/**
	 * 查询详情信息
	 * @param ID
	 * @return
	 */
	public Resource getResource(Integer ID){
		return resourceMapper.getResource(ID);
	}

	/**
	 * 新增保存
	 * @param resource
	 */
	public Integer saveResource(Resource resource){
		return resourceMapper.saveResource(resource);
	}
	
	/**
	 * 修改
	 * @param resource
	 */
	public Integer updateResource(Resource resource){
		return resourceMapper.updateResource(resource);
	}
}
