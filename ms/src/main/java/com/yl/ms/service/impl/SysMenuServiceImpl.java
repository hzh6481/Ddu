package com.yl.ms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.ms.entity.SysMenu;
import com.yl.ms.dao.SysMenuMapper;
import com.yl.ms.service.SysMenuService;
/**
 * @author yl
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService{

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findAllByMenuCode(String MenuCode) {
        System.out.println(MenuCode);
        return sysMenuMapper.selectAllByMenuCode(MenuCode);
    }
}
