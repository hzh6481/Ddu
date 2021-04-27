package com.yl.ms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.ms.entity.Permissions;
import com.yl.ms.entity.SysRole;
import com.yl.ms.entity.User;
import com.yl.ms.utils.ExpDemo.AopDemoTe;
import com.yl.ms.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.ms.dao.SysUserMapper;
import com.yl.ms.entity.SysUser;
import com.yl.ms.service.SysUserService;

/**
 * @author yl
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    public final static Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public boolean login(SysUser sysUser) {
        /**
         * 1、判断传入用户是否为空
         * 2、根据用户编码查询，判断用户是否存在
         * 3、判断传入密码与加密后的密码是否一致
         */
        if(Objects.isNull(sysUser)){
            System.out.println("传入用户为空");
            return false;
        }
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("user_code",sysUser.getUserCode());
        SysUser findedUser = sysUserMapper.selectOne(sysUserQueryWrapper);
        if(Objects.isNull(findedUser)){
            System.out.println("该用户不存在!");
            return false;
        }
        if (Objects.equals(findedUser.getPassword(), PasswordUtils.digestPassword(sysUser.getPassword()))){
            System.out.println("密码匹配成功");
            return true;
        }

        return false;
    }

    @Override
    public User getUserByName(String getMapByName) {
        return getMapByName(getMapByName);
    }

    @AopDemoTe
    @Override
    public String insertUser(String ObjectCode,String inUserCode, String inUserName, String inUserPass, String inUserAddr) {
        logger.info("执行插入用户的操作");
        logger.info("ObjectCode:"+ObjectCode);
        SysUser sys= new SysUser();
        sys.setUserCode(inUserCode);
        sys.setUserName(inUserName);
        sys.setPassword(PasswordUtils.digestPassword(inUserPass));//MD5加密
        sys.setAddress(inUserAddr);
        try {
            if (sysUserMapper.insert(sys) > 0) {
                return "插入成功";
            } else {
                return "插入失败";
            }
        }catch (Exception e){
            log.error("插入异常",e);
            return "插入异常";

        }
    }

    /**
     * 模拟数据库查询
     *
     * @param userName 用户名
     * @return User
     */
    private User getMapByName(String userName) {
        Permissions permissions1 = new Permissions("1", "query");
        Permissions permissions2 = new Permissions("2", "add");
        Set<Permissions> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);
        SysRole role = new SysRole("1", "admin", permissionsSet);
        Set<SysRole> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User("1", "wsl", "123456", roleSet);
        Map<String, User> map = new HashMap<>();
        map.put(user.getUserName(), user);

        Set<Permissions> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions1);
        SysRole role1 = new SysRole("2", "user", permissionsSet1);
        Set<SysRole> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User("2", "zhangsan", "123456", roleSet1);
        map.put(user1.getUserName(), user1);
        return map.get(userName);
    }
}

