package com.free.framework.core.organization.service;

import com.free.framework.core.organization.entity.Organization;
import com.free.framework.core.organization.mapper.OrganizationMapper;
import com.free.framework.core.organization.vo.OrganizationTreeVO;
import com.free.framework.core.user.util.UserUtils;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.common.service.CommonService;
import com.free.framework.plateform.constant.NumberConstants;
import com.free.framework.plateform.constant.StatusEnum;
import com.free.framework.util.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.free.framework.core.organization.service.OrganizationService
 * 组织业务
 * @author lipeng
 * @dateTime 2017/9/17 3:33
 */
@Service
@Slf4j
public class OrganizationService extends CommonService<Organization> {

	@Autowired
	private OrganizationMapper organizationMapper;

	/**
	 * 查询列表信息
	 * @return
	 */
	public List<Organization> listOrganization() {
		// 用户列表
		List<Organization> organizationList = organizationMapper.listOrganization();
		return organizationList;
	}

	/**
	 * 查询详情信息
	 *
	 * @param id
	 * @return
	 */
	public Organization getOrganization(Integer id) {
		return organizationMapper.getOrganization(id);
	}

	/**
	 * 新增保存
	 *
	 * @param organization
	 */
	public ResponseData saveOrganization(Organization organization) {
		organization.setSavePerson(UserUtils.getUserLoginCode());
		organization.setSaveDate(DateUtils.getDate());
		organization.setStatus(StatusEnum.ENABLE_STATUS.getId());
		int count = organizationMapper.saveOrganization(organization);
		return count == NumberConstants.ONE ? ResponseData.success() : ResponseData.fail();
	}

	/**
	 * 修改
	 *
	 * @param organization
	 */
	public ResponseData updateOrganization(Organization organization) {
		organization.setUpdatePerson(UserUtils.getUserLoginCode());
		organization.setUpdateDate(DateUtils.getDate());
		int count = organizationMapper.updateOrganization(organization);
		return count == NumberConstants.ONE ? ResponseData.success() : ResponseData.fail();
	}

	/**
	 * 查询组织列表,提供给前台select展示
	 *
	 * @return
	 */
	public List<OrganizationTreeVO> treeOrganization() {
		List<OrganizationTreeVO> organizationTreeVoList = organizationMapper.treeOrganization();
		return organizationTreeVoList;
	}

}
