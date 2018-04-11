package com.free.framework.core.organization.mapper;

import com.free.framework.core.organization.entity.Organization;
import com.free.framework.core.organization.vo.OrganizationTreeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * com.free.framework.core.organization.mapper.OrganizationMapper
 * 组织数据库操作
 * @author lipeng
 * @dateTime 2017/9/17 3:33
 */
@Mapper
public interface OrganizationMapper {
	
	/**
	 * 新增保存
	 * @param organization
	 */
	int saveOrganization(Organization organization);
	
	/**
	 * 修改
	 * @param organization
	 */
	int updateOrganization(Organization organization);
	
	/**
	 * 查询详情信息
	 * @param id
	 * @return
	 */
	Organization getOrganization(Integer id);
	
	/**
	 * 查询列表信息
	 * @return
	 */
	List<Organization> listOrganization();

	/**
	 * 查询组织树
	 * @return
	 */
    List<OrganizationTreeVO> treeOrganization();
}
