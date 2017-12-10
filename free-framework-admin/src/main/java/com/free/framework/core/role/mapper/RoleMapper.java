package com.free.framework.core.role.mapper;


import com.free.framework.core.role.controller.param.RoleParam;
import com.free.framework.core.role.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * com.free.framework.core.role.mapper.RoleMapper
 * 角色数据库操作
 * @author lipeng
 * @dateTime 2017/9/9 23:04
 */
@Mapper
public interface RoleMapper {

    List<Role> listRole(RoleParam roleParam);

    Role getRole(Integer id);

    Integer saveRole(Role role);

    Integer updateRole(Role role);
}
