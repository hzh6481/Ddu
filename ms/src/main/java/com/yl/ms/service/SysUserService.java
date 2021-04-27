package com.yl.ms.service;

import com.yl.ms.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.ms.entity.User;

/**
 * @author yl
 */
public interface SysUserService extends IService<SysUser> {


    public boolean login(SysUser sysUser);

    public User getUserByName(String getMapByName);

    String insertUser(String ObjectCode,String inUserCode, String inUserName, String inUserPass, String inUserAddr);
}

