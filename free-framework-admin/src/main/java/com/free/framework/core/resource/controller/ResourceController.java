package com.free.framework.core.resource.controller;


import com.free.framework.core.resource.controller.param.ResourceParam;
import com.free.framework.core.resource.entity.Resource;
import com.free.framework.core.resource.service.ResourceService;
import com.free.framework.plateform.common.controller.BaseController;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.csrf.annotation.GenerateToken;
import com.free.framework.plateform.csrf.annotation.ValidateToken;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 */
@Controller
@RequestMapping(ResourceControllerMappingUrl.RESOURCE_CONTROLLER)
public class ResourceController extends BaseController {
	
	@Autowired
	private ResourceService resourceService;

	/**
	 * 查询列表信息
	 * @param
	 * @return
	 */
    @GetMapping(ResourceControllerMappingUrl.RESOURCE)
	public String listResourceList(ResourceParam resourceParam){
		PageInfo pageInfo = resourceService.pageResource(resourceParam);
		setRequestAttribute("pageInfo", pageInfo);
		setRequestAttribute("resourceParam", resourceParam);
		return ResourceControllerMappingUrl.PAGE_LIST_RETURN;
	}

	/**
	* 跳转新增页面
	* @param
	* @return
	*/
	@GenerateToken
	@GetMapping(ResourceControllerMappingUrl.PAGE_ADD)
	public String addPage(){
		return ResourceControllerMappingUrl.PAGE_ADD_RETURN;
	}

	/**
	* 跳转修改页面
	* @param id
	* @return
	*/
	@GenerateToken
	@GetMapping(ResourceControllerMappingUrl.PAGE_UPDATE)
	public String updatePage(Integer id){
		Resource resource = resourceService.getResource(id);
		setRequestAttribute("resource", resource);
		return ResourceControllerMappingUrl.PAGE_UPDATE_RETURN;

	}
	
	/**
	 * 查询详情信息
	 * @param id
	 * @return
	 */
	@GetMapping(ResourceControllerMappingUrl.ONE_RESOURCE)
	public String getResourceDetail(@PathVariable("id") Integer id){
		Resource resource = resourceService.getResource(id);
		setRequestAttribute("resource", resource);
		return ResourceControllerMappingUrl.PAGE_DETAIL_RETURN;
	}

	/**
	 * 新增保存
	 * @param resource
	 */
	@ValidateToken
	@PostMapping(ResourceControllerMappingUrl.RESOURCE)
	@ResponseBody
	public ResponseData saveResource(Resource resource){
		ResponseData responseData = resourceService.saveResource(resource);
		return responseData;
	}
	
	/**
	 * 修改
	 * @param resource
	 */
	@ValidateToken
	@PutMapping(ResourceControllerMappingUrl.RESOURCE)
	@ResponseBody
	public ResponseData updateResource(Resource resource){
		ResponseData responseData = resourceService.updateResource(resource);
		return responseData;
	}
}
