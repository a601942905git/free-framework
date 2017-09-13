package com.free.framework.core.resource.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.free.framework.core.resource.entity.Resource;
import com.free.framework.core.resource.controller.param.ResourceParam;

/**
 * 操作相关
 */
@Mapper
public interface ResourceMapper {
	
	/**
	 * 新增保存
	 * @param resource
	 */
	int saveResource(Resource resource);
	
	/**
	 * 修改
	 * @param resource
	 */
	int updateResource(Resource resource);
	
	/**
	 * 查询详情信息
	 * @param id
	 * @return
	 */
	Resource getResource(Integer id);
	
	/**
	 * 查询列表信息
	 * @param resourceParam
	 * @return
	 */
	List<Resource> listResource(ResourceParam resourceParam);
}
