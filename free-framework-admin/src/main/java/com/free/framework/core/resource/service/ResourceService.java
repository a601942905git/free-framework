package com.free.framework.core.resource.service;

import com.free.framework.core.resource.controller.param.ResourceParam;
import com.free.framework.core.resource.entity.Resource;
import com.free.framework.core.resource.mapper.ResourceMapper;
import com.free.framework.core.resource.vo.ResourceTreeVO;
import com.free.framework.core.user.util.UserUtils;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.common.service.CommonService;
import com.free.framework.plateform.constant.StatusEnum;
import com.free.framework.plateform.constant.SystemConstants;
import com.free.framework.util.date.DateUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	 * @param resourceParam
	 * @return
	 */
	public PageInfo<Resource> pageResource(ResourceParam resourceParam){
		// 分页
		startPage(resourceParam);
		// 用户列表
		List<Resource> resourceList = resourceMapper.listResource(resourceParam);
        return getPageInfo(resourceList);
	}
	
	/**
	 * 查询详情信息
	 * @param ID
	 * @return
	 */
	public Resource getResource(Integer ID){
		return resourceMapper.getResource(ID);
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
		return count == 1 ? ResponseData.success() : ResponseData.fail();
	}
	
	/**
	 * 修改
	 * @param resource
	 */
	public ResponseData updateResource(Resource resource){
		resource.setUpdatePerson(UserUtils.getUserLoginCode());
		resource.setUpdateDate(DateUtils.getDate());
		int count = resourceMapper.updateResource(resource);
		return count == 1 ? ResponseData.success() : ResponseData.fail();
	}

	/**
	 * 查询资源列表信息
	 * @return
	 */
	public List<ResourceTreeVO> listResourceTree() {
		List<ResourceTreeVO> resourceTreeVOList = resourceMapper.listResourceTree();
		// 组装成树
		Optional<List<ResourceTreeVO>> optional = organizeResourceTreeVOList(resourceTreeVOList);
		return optional.orElseGet(ArrayList::new);
	}

	/**
	 * 组装资源树
	 * @param resourceTreeVOList
	 * @return
	 */
	private Optional<List<ResourceTreeVO>> organizeResourceTreeVOList(List<ResourceTreeVO> resourceTreeVOList) {
		if (CollectionUtils.isEmpty(resourceTreeVOList)) {
			return Optional.empty();
		}

		List<ResourceTreeVO> resourceTreeVOList1 = resourceTreeVOList.stream()
				.map(resourceTreeVO -> {
					// 查找当前节点下面所有的子节点
					resourceTreeVO.getResourceTreeVOList().addAll(resourceTreeVOList.stream()
							.filter(resourceTreeVO1 -> resourceTreeVO.getId().equals(resourceTreeVO1.getPid()))
							.collect(Collectors.toList()));
					return resourceTreeVO;
				})
				// 过滤的到所有的一级节点
				.filter(resourceTreeVO -> SystemConstants.PARENT_ID.equals(resourceTreeVO.getPid()))
				.collect(Collectors.toList());

		return Optional.of(resourceTreeVOList1);
	}
}
