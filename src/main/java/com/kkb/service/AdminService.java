package com.kkb.service;

import com.kkb.mapper.AdminsMapper;
import com.kkb.pojo.Admins;
import com.kkb.pojo.AdminsExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author APPDE
 */

@Service
public class AdminService {

    @Resource
    private AdminsMapper adminsMapper;
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Admins> checkLogin(String loginName, String password){
        AdminsExample example = new AdminsExample();
        AdminsExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameLike(loginName);
        criteria.andAdminPwdLike(password);
        List<Admins> admins = adminsMapper.selectByExample(example);
        return admins;
    }
}
