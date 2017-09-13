package com.free.framework.core.resource.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.free.framework.core.resource.controller.param.ResourceParam;
import com.free.framework.core.resource.entity.Resource;
import com.free.framework.core.resource.service.ResourceService;

import com.free.framework.plateform.common.controller.BaseController;
import com.free.framework.plateform.constant.StatusEnum;

import java.util.Date;


/**
 * 
 *
 */
@Controller
@RequestMapping(ResourceControllerMappingURL.RESOURCE_CONTROLLER)
public class ResourceController extends BaseController {
	
	@Autowired
	private ResourceService resourceService;

	/**
	 * 查询列表信息
	 * @param
	 * @return
	 */
    @GetMapping(ResourceControllerMappingURL.RESOURCE)
	public String listResourceList(Model model, ResourceParam resourceParam){
		PageInfo pageInfo = resourceService.pageResource(resourceParam);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("resourceParam", resourceParam);
		return ResourceControllerMappingURL.PAGE_LIST_RETURN;
	}

	/**
	* 跳转新增页面
	* @param
	* @return
	*/
	@GetMapping(ResourceControllerMappingURL.PAGE_ADD)
	public String addPage(){
		return ResourceControllerMappingURL.PAGE_ADD_RETURN;
	}

	/**
	* 跳转修改页面
	* @param id
	* @return
	*/
	@GetMapping(ResourceControllerMappingURL.PAGE_UPDATE)
	public String updatePage(Model model,Integer id){
		Resource resource = resourceService.getResource(id);
		model.addAttribute("resource", resource);
		return ResourceControllerMappingURL.PAGE_UPDATE_RETURN;

	}
	
	/**
	 * 查询详情信息
	 * @param id
	 * @return
	 */
	@GetMapping(ResourceControllerMappingURL.ONE_RESOURCE)
	public String getResourceDetail(@PathVariable("id") Integer id, Model model){
		Resource resource = resourceService.getResource(id);
		model.addAttribute("resource", resource);
		return ResourceControllerMappingURL.PAGE_DETAIL_RETURN;
	}

	/**
	 * 新增保存
	 * @param resource
	 */
	@PostMapping(ResourceControllerMappingURL.RESOURCE)
	@ResponseBody
	public Integer saveResource(Resource resource){
		resource.setSavePerson("111111");
		resource.setSaveDate(new Date());
		resource.setStatus(StatusEnum.ENABLE_STATUS.getId());
		Integer count = resourceService.saveResource(resource);
		return count;
	}
	
	/**
	 * 修改
	 * @param resource
	 */
	@PutMapping(ResourceControllerMappingURL.RESOURCE)
	@ResponseBody
	public Integer updateResource(Resource resource){
		resource.setUpdatePerson("111111");
		resource.setUpdateDate(new Date());
		Integer count = resourceService.updateResource(resource);
		return count;
	}
}
