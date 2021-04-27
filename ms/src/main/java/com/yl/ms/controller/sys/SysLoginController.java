package com.yl.ms.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.ms.entity.SysUser;
import com.yl.ms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * @author 001
 */

@RequestMapping("/sys_user")
@Controller
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    @ResponseBody
    public HashMap<String,Object> loginSystem(@RequestBody SysUser sysUser){
        HashMap<String,Object> map = new HashMap<>(4);
        boolean login = sysUserService.login(sysUser);
        if(login){
            map.put("flag",true);
            map.put("mes","登陆成功");
            return map;
        }
        map.put("flag",false);
        map.put("mes","账号或密码不正确,请重新输入");
        return map;
    }

    @GetMapping("/list")
    @ResponseBody
    public HashMap<String,Object> listsSysUser(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(4);
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        if("".equals(userCode) && "".equals(userName)) {
            List<SysUser> list = sysUserService.list();
            map.put("userList",list);

            return map;
        }else {
            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
            if(!"".equals(userCode)){
                queryWrapper.eq("user_code",userCode);
            }
            if (!"".equals(userName)){
                queryWrapper.like("user_name",userName);
            }
            List<SysUser> list = sysUserService.list(queryWrapper);
            map.put("userList",list);
            return map;
        }
    }

    @GetMapping("/input")
    @ResponseBody
    public HashMap<String,Object> insertUser(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        String result = "";
        String inObjectCode = request.getParameter("inObjectCode");
        String inUserCode = request.getParameter("inUserCode");
        String inUserName = request.getParameter("inUserName");
        String inUserPass = request.getParameter("inUserPass");
        String inUserAddr = request.getParameter("inUserAddr");
        result = sysUserService.insertUser(inObjectCode,inUserCode,inUserName,inUserPass,inUserAddr);
        map.put("result",result);
        return map;
    }
}

