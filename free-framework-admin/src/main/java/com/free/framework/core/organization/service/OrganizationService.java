package com.free.framework.core.organization.service;

import com.free.framework.core.organization.controller.param.OrganizationParam;
import com.free.framework.core.organization.entity.Organization;
import com.free.framework.core.organization.mapper.OrganizationMapper;
import com.free.framework.core.organization.vo.OrganizationTreeVO;
import com.free.framework.core.user.util.UserUtils;
import com.free.framework.plateform.common.service.CommonService;
import com.free.framework.plateform.constant.StatusEnum;
import com.free.framework.plateform.constant.SystemConstants;
import com.free.framework.util.date.DateUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		organization.setSavePerson(UserUtils.getUserLoginCode());
		organization.setSaveDate(DateUtils.getCurrentDate());
		organization.setStatus(StatusEnum.ENABLE_STATUS.getId());
		return organizationMapper.saveOrganization(organization);
	}
	
	/**
	 * 修改
	 * @param organization
	 */
	public Integer updateOrganization(Organization organization){
		organization.setUpdatePerson(UserUtils.getUserLoginCode());
		organization.setUpdateDate(DateUtils.getCurrentDate());
		return organizationMapper.updateOrganization(organization);
	}

	/**
	 * 查询组织列表,提供给前台select展示
	 * @return
	 */
	public List<OrganizationTreeVO> listOrganizationSelect() {
		return organizationMapper.listOrganizationSelect();
	}

	/**
	 * 组装组织树
	 * @param organizationTreeVOList
	 * @return
	 */
	private Optional<List<OrganizationTreeVO>> organizeResourceTreeVOList(List<OrganizationTreeVO> organizationTreeVOList) {
		if (CollectionUtils.isEmpty(organizationTreeVOList)) {
			return Optional.empty();
		}

		List<OrganizationTreeVO> organizationTreeVOList1 = organizationTreeVOList.stream()
				.map(organizationTreeVO -> {
					// 查找当前节点下面所有的子节点
					organizationTreeVO.getOrganizationTreeVOList().addAll(organizationTreeVOList.stream()
							.filter(organizationTreeVO1 -> organizationTreeVO.getId().equals(organizationTreeVO1.getPid()))
							.collect(Collectors.toList()));
					return organizationTreeVO;
				})
				// 过滤的到所有的一级节点
				.filter(resourceTreeVO -> SystemConstants.PARENT_ID.equals(resourceTreeVO.getPid()))
				.collect(Collectors.toList());

		return Optional.of(organizationTreeVOList1);
	}
}
