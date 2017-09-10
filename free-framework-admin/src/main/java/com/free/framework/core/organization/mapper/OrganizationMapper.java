package com.free.framework.core.organization.mapper;

import com.free.framework.core.organization.controller.param.OrganizationParam;
import com.free.framework.core.organization.entity.Organization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作相关
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
	 * @param organizationParam
	 * @return
	 */
	List<Organization> listOrganization(OrganizationParam organizationParam);
}
