package com.free.framework.core.resource.mapper;

import com.free.framework.core.resource.entity.Resource;
import com.free.framework.core.resource.vo.ResourceTreeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * com.free.framework.core.resource.mapper.ResourceMapper
 * 资源数据库操作
 * @author lipeng
 * @dateTime 2017/9/22 23:40
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
	 * @return
	 */
	List<Resource> listResource();

	/**
	 * 查询资源树信息
	 * @return
	 */
	List<ResourceTreeVO> treeResource();
}
