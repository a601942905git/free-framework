package com.free.framework.core.organization.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.free.framework.core.organization.controller.param.OrganizationParam;
import com.free.framework.core.organization.entity.Organization;
import com.free.framework.core.organization.service.OrganizationService;

import com.free.framework.plateform.common.controller.BaseController;
import com.free.framework.plateform.constant.StatusEnum;

import java.util.Date;


/**
 * 
 *
 */
@Controller
@RequestMapping(OrganizationControllerMappingURL.ORGANIZATION_CONTROLLER)
public class OrganizationController extends BaseController {
	
	@Autowired
	private OrganizationService organizationService;

	/**
	 * 查询列表信息
	 * @param
	 * @return
	 */
    @GetMapping(OrganizationControllerMappingURL.ORGANIZATION)
	public String listOrganizationList(Model model, OrganizationParam organizationParam){
		PageInfo pageInfo = organizationService.pageOrganization(organizationParam);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("organizationParam", organizationParam);
		return OrganizationControllerMappingURL.PAGE_LIST_RETURN;
	}

	/**
	* 跳转新增页面
	* @param
	* @return
	*/
	@GetMapping(OrganizationControllerMappingURL.PAGE_ADD)
	public String addPage(){
		return OrganizationControllerMappingURL.PAGE_ADD_RETURN;
	}

	/**
	* 跳转修改页面
	* @param id
	* @return
	*/
	@GetMapping(OrganizationControllerMappingURL.PAGE_UPDATE)
	public String updatePage(Model model,Integer id){
		Organization organization = organizationService.getOrganization(id);
		model.addAttribute("organization", organization);
		return OrganizationControllerMappingURL.PAGE_UPDATE_RETURN;

	}
	
	/**
	 * 查询详情信息
	 * @param id
	 * @return
	 */
	@GetMapping(OrganizationControllerMappingURL.ONE_ORGANIZATION)
	public String getOrganizationDetail(@PathVariable("id") Integer id, Model model){
		Organization organization = organizationService.getOrganization(id);
		model.addAttribute("organization", organization);
		return OrganizationControllerMappingURL.PAGE_DETAIL_RETURN;
	}

	/**
	 * 新增保存
	 * @param organization
	 */
	@PostMapping(OrganizationControllerMappingURL.ORGANIZATION)
	@ResponseBody
	public Integer saveOrganization(Organization organization){
		organization.setSavePerson("111111");
		organization.setSaveDate(new Date());
		organization.setStatus(StatusEnum.ENABLE_STATUS.getId());
		Integer count = organizationService.saveOrganization(organization);
		return count;
	}
	
	/**
	 * 修改
	 * @param organization
	 */
	@PutMapping(OrganizationControllerMappingURL.ORGANIZATION)
	@ResponseBody
	public Integer updateOrganization(Organization organization){
		organization.setUpdatePerson("111111");
		organization.setUpdateDate(new Date());
		Integer count = organizationService.updateOrganization(organization);
		return count;
	}
}
