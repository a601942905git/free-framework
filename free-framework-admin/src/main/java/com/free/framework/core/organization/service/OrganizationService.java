package com.free.framework.core.organization.service;

import com.free.framework.core.organization.controller.param.OrganizationParam;
import com.free.framework.core.organization.entity.Organization;
import com.free.framework.core.organization.mapper.OrganizationMapper;
import com.free.framework.plateform.common.service.CommonService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 操作相关
 */
@Service
@Slf4j
public class OrganizationService extends CommonService {

	@Autowired
	private OrganizationMapper organizationMapper;
	
	/**
	 * 查询列表信息
	 * @param organizationParam
	 * @return
	 */
	public PageInfo<Organization> pageOrganization(OrganizationParam organizationParam){
		// 分页
		startPage(organizationParam);
		// 用户列表
		List<Organization> organizationList = organizationMapper.listOrganization(organizationParam);
		// 设置分页信息
    	PageInfo<Organization> pageOrganization = new PageInfo(organizationList);
        return pageOrganization;
	}
	
	/**
	 * 查询详情信息
	 * @param ID
	 * @return
	 */
	public Organization getOrganization(Integer ID){
		return organizationMapper.getOrganization(ID);
	}

	/**
	 * 新增保存
	 * @param organization
	 */
	public Integer saveOrganization(Organization organization){
		return organizationMapper.saveOrganization(organization);
	}
	
	/**
	 * 修改
	 * @param organization
	 */
	public Integer updateOrganization(Organization organization){
		return organizationMapper.updateOrganization(organization);
	}
}
