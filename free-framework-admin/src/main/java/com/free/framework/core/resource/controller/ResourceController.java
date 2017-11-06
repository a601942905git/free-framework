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
	public String listResourceList(ResourceParam resourceParam){
		PageInfo pageInfo = resourceService.pageResource(resourceParam);
		setRequestAttribute("pageInfo", pageInfo);
		setRequestAttribute("resourceParam", resourceParam);
		return ResourceControllerMappingURL.PAGE_LIST_RETURN;
	}

	/**
	* 跳转新增页面
	* @param
	* @return
	*/
	@GenerateToken
	@GetMapping(ResourceControllerMappingURL.PAGE_ADD)
	public String addPage(){
		return ResourceControllerMappingURL.PAGE_ADD_RETURN;
	}

	/**
	* 跳转修改页面
	* @param id
	* @return
	*/
	@GenerateToken
	@GetMapping(ResourceControllerMappingURL.PAGE_UPDATE)
	public String updatePage(Integer id){
		Resource resource = resourceService.getResource(id);
		setRequestAttribute("resource", resource);
		return ResourceControllerMappingURL.PAGE_UPDATE_RETURN;

	}
	
	/**
	 * 查询详情信息
	 * @param id
	 * @return
	 */
	@GetMapping(ResourceControllerMappingURL.ONE_RESOURCE)
	public String getResourceDetail(@PathVariable("id") Integer id){
		Resource resource = resourceService.getResource(id);
		setRequestAttribute("resource", resource);
		return ResourceControllerMappingURL.PAGE_DETAIL_RETURN;
	}

	/**
	 * 新增保存
	 * @param resource
	 */
	@ValidateToken
	@PostMapping(ResourceControllerMappingURL.RESOURCE)
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
	@PutMapping(ResourceControllerMappingURL.RESOURCE)
	@ResponseBody
	public ResponseData updateResource(Resource resource){
		ResponseData responseData = resourceService.updateResource(resource);
		return responseData;
	}
}
