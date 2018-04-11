package com.free.framework.core.organization.service;

import com.free.framework.core.organization.controller.param.OrganizationParam;
import com.free.framework.core.organization.entity.Organization;
import com.free.framework.core.organization.mapper.OrganizationMapper;
import com.free.framework.core.organization.vo.OrganizationTreeVO;
import com.free.framework.core.user.util.UserUtils;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.common.service.CommonService;
import com.free.framework.plateform.constant.StatusEnum;
import com.free.framework.plateform.constant.SystemConstants;
import com.free.framework.util.CollectionUtils;
import com.free.framework.util.date.DateUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	 * @param organizationParam
	 * @return
	 */
	public PageInfo<Organization> pageOrganization(OrganizationParam organizationParam) {
		// 分页
		startPage(organizationParam);
		// 用户列表
		List<Organization> organizationList = organizationMapper.listOrganization(organizationParam);
		return getPageInfo(organizationList);
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
		return count == 1 ? ResponseData.success() : ResponseData.fail();
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
		return count == 1 ? ResponseData.success() : ResponseData.fail();
	}

	/**
	 * 查询组织列表,提供给前台select展示
	 *
	 * @return
	 */
	public List<OrganizationTreeVO> treeOrganization() {
		List<OrganizationTreeVO> organizationTreeVOList = organizationMapper.treeOrganization();
		Optional<List<OrganizationTreeVO>> optional = addRootNode(organizationTreeVOList);
		return optional.orElseGet(ArrayList::new);
	}

	/**
	 * 添加根节点
	 *
	 * @param organizationTreeVOList
	 * @return
	 */
	private Optional<List<OrganizationTreeVO>> addRootNode(List<OrganizationTreeVO> organizationTreeVOList) {
		if (null == organizationTreeVOList) {
			return Optional.empty();
		}

		OrganizationTreeVO organizationTreeVO =
				OrganizationTreeVO.builder().id(0).pId(0).name("公司").open("true").build();
		organizationTreeVOList.add(organizationTreeVO);

		return Optional.of(organizationTreeVOList);
	}


	/**
	 * 组装组织树
	 * @param organizationList
	 * @return
	 */
	private Optional<List<Organization>> treeOrganizationList(List<Organization> organizationList) {
		if (CollectionUtils.isEmpty(organizationList)) {
			return Optional.empty();
		}

		List<Organization> treeOrganizationList = organizationList.stream()
				.map(treeOrganization -> {
					// 查找当前节点下面所有的子节点
					treeOrganization.getOrganizationList().addAll(organizationList.stream()
							.filter(organizationTreeVO1 -> treeOrganization.getId().equals(organizationTreeVO1.getPid()))
							.collect(Collectors.toList()));
					return treeOrganization;
				})
				// 过滤的到所有的一级节点
				.filter(treeOrganization -> SystemConstants.PARENT_ID.equals(treeOrganization.getPid()))
				.collect(Collectors.toList());

		return Optional.of(treeOrganizationList);
	}
}
