package com.free.framework.core.resource.controller;


import com.alibaba.fastjson.JSON;
import com.free.framework.core.resource.entity.Resource;
import com.free.framework.core.resource.service.ResourceService;
import com.free.framework.core.resource.vo.ResourceTreeVO;
import com.free.framework.plateform.common.controller.BaseController;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.csrf.annotation.GenerateToken;
import com.free.framework.plateform.csrf.annotation.ValidateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * com.free.framework.core.resource.controller.ResourceController
 * 资源请求控制器
 * @author lipeng
 * @dateTime 2017/9/22 23:40
 */
@Controller
@RequestMapping(ResourceControllerMappingUrl.RESOURCE_CONTROLLER)
public class ResourceController extends BaseController {
	
	@Autowired
	private ResourceService resourceService;

	@GetMapping(ResourceControllerMappingUrl.PAGE_LIST)
	public String listPage() {
		return ResourceControllerMappingUrl.PAGE_LIST_RETURN;
	}

	/**
	 * 查询列表信息
	 * @param
	 * @return
	 */
    @GetMapping(ResourceControllerMappingUrl.RESOURCE)
	@ResponseBody
	public List<Resource> listResource(){
		List<Resource> resourceList = resourceService.listResource();
		return resourceList;
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
	@PutMapping(ResourceControllerMappingUrl.ONE_RESOURCE)
	@ResponseBody
	public ResponseData updateResource(Resource resource){
		ResponseData responseData = resourceService.updateResource(resource);
		return responseData;
	}

	/**
	 * 跳转到组织树页面
	 * @return
	 */
	@GetMapping(ResourceControllerMappingUrl.PAGE_RESOURCE_TREE)
	public String resourceTreePage() {
		List<ResourceTreeVO> resourceTreeVOList = resourceService.treeResource();
		setRequestAttribute("resourceTreeVOList", JSON.toJSONString(resourceTreeVOList));
		return ResourceControllerMappingUrl.PAGE_RESOURCE_TREE_RETURN;
	}
}
