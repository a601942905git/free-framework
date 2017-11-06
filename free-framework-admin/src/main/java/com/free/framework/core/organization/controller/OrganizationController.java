package com.free.framework.core.organization.controller;


import com.alibaba.fastjson.JSON;
import com.free.framework.core.organization.controller.param.OrganizationParam;
import com.free.framework.core.organization.entity.Organization;
import com.free.framework.core.organization.service.OrganizationService;
import com.free.framework.core.organization.vo.OrganizationTreeVO;
import com.free.framework.plateform.common.controller.BaseController;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.csrf.annotation.GenerateToken;
import com.free.framework.plateform.csrf.annotation.RefreshToken;
import com.free.framework.plateform.csrf.annotation.ValidateToken;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 组织管理
 * @author lipeng
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
	@ApiOperation(value = "查询组织列表信息")
	public String listOrganizationList(OrganizationParam organizationParam){
		PageInfo pageInfo = organizationService.pageOrganization(organizationParam);
		setRequestAttribute("pageInfo", pageInfo);
		setRequestAttribute("organizationParam", organizationParam);
		return OrganizationControllerMappingURL.PAGE_LIST_RETURN;
	}

	/**
	* 跳转新增页面
	* @param
	* @return
	*/
	@ApiOperation(value = "跳转到组织新增界面")
	@GetMapping(OrganizationControllerMappingURL.PAGE_ADD)
	@GenerateToken
	public String addPage(){
		return OrganizationControllerMappingURL.PAGE_ADD_RETURN;
	}

	/**
	* 跳转修改页面
	* @param id
	* @return
	*/
	@ApiOperation(value = "跳转到组织修改界面")
	@GetMapping(OrganizationControllerMappingURL.PAGE_UPDATE)
	public String updatePage(Integer id){
		Organization organization = organizationService.getOrganization(id);
		setRequestAttribute("organization", organization);
		return OrganizationControllerMappingURL.PAGE_UPDATE_RETURN;

	}
	
	/**
	 * 查询详情信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查看组织详情信息")
	@GetMapping(OrganizationControllerMappingURL.ONE_ORGANIZATION)
	public String getOrganizationDetail(@PathVariable(ID) Integer id, Model model){
		Organization organization = organizationService.getOrganization(id);
		setRequestAttribute("organization", organization);
		return OrganizationControllerMappingURL.PAGE_DETAIL_RETURN;
	}

	/**
	 * 新增保存
	 * @param organization
	 */
	@ApiOperation(value = "新增组织信息")
	@ValidateToken
	@RefreshToken
	@PostMapping(OrganizationControllerMappingURL.ORGANIZATION)
	@ResponseBody
	public ResponseData saveOrganization(Organization organization){
		ResponseData responseData = organizationService.saveOrganization(organization);
		return responseData;
	}
	
	/**
	 * 修改
	 * @param organization
	 */
	@ApiOperation(value = "修改组织信息")
	@PutMapping(OrganizationControllerMappingURL.ONE_ORGANIZATION)
	@ResponseBody
	public ResponseData updateOrganization(@PathVariable(ID) Integer id, Organization organization){
		organization.setId(id);
		ResponseData responseData = organizationService.updateOrganization(organization);
		return responseData;
	}


	/**
	 * 跳转到组织树页面
	 * @return
	 */
	@ApiOperation(value = "跳转到组织树页面")
	@GetMapping(OrganizationControllerMappingURL.PAGE_ORGANIZATION_TREE)
	public String organizationTreePage() {
		List<OrganizationTreeVO> organizationTreeVOList = organizationService.treeOrganization();
		setRequestAttribute("organizationTreeVOList", JSON.toJSONString(organizationTreeVOList));
		return OrganizationControllerMappingURL.PAGE_ORGANIZATION_TREE_RETURN;
	}
}
