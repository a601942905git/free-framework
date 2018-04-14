package com.free.framework.core.resource.service;

import com.free.framework.core.resource.entity.Resource;
import com.free.framework.core.resource.mapper.ResourceMapper;
import com.free.framework.core.resource.vo.ResourceTreeVO;
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
 * com.free.framework.core.resource.service.ResourceService
 * 资源业务操作
 * @author lipeng
 * @dateTime 2017/9/22 23:40
 */
@Service
@Slf4j
public class ResourceService extends CommonService<Resource> {

	@Autowired
	private ResourceMapper resourceMapper;
	
	/**
	 * 查询列表信息
	 * @return
	 */
	public List<Resource> listResource(){
		// 用户列表
		List<Resource> resourceList = resourceMapper.listResource();
        return resourceList;
	}
	
	/**
	 * 查询详情信息
	 * @param id
	 * @return
	 */
	public Resource getResource(Integer id){
		return resourceMapper.getResource(id);
	}

	/**
	 * 新增保存
	 * @param resource
	 */
	public ResponseData saveResource(Resource resource){
		resource.setSavePerson(UserUtils.getUserLoginCode());
		resource.setSaveDate(DateUtils.getDate());
		resource.setStatus(StatusEnum.ENABLE_STATUS.getId());
		int count = resourceMapper.saveResource(resource);
		return count == NumberConstants.ONE ? ResponseData.success() : ResponseData.fail();
	}
	
	/**
	 * 修改
	 * @param resource
	 */
	public ResponseData updateResource(Resource resource){
		resource.setUpdatePerson(UserUtils.getUserLoginCode());
		resource.setUpdateDate(DateUtils.getDate());
		int count = resourceMapper.updateResource(resource);
		return count == NumberConstants.ONE ? ResponseData.success() : ResponseData.fail();
	}

	/**
	 * 查询资源树信息
	 * @return
	 */
	public List<ResourceTreeVO> treeResource() {
		return resourceMapper.treeResource();
	}
}
