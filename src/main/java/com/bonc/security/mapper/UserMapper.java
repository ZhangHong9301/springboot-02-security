package com.bonc.security.mapper;


import com.bonc.security.domain.Permission;
import com.bonc.security.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    /**
     * 查询当前用户对象
     */

    public User findByUsername(String username);

    /**
     * 查询当前用户拥有的权限
     */
    public List<Permission> findPermissionByUsername(String username);

    /**
     * 修改密码
     */
    public void updatePassword(User user);
}
