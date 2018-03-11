package com.zl.service.impl;

import com.zl.bean.User;
import com.zl.dao.UserDAO;
import com.zl.service.AbstractBaseService;
import com.zl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/12/21
 */
@Service
public class UserServiceImpl extends AbstractBaseService implements UserService {
    private UserDAO userDAO;

    private Boolean isForceFefresh;

    public void setForceFefresh(Boolean forceFefresh) {
        isForceFefresh = forceFefresh;
    }

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        super.setBaseDAO(userDAO);
        this.userDAO = userDAO;
    }

    @Override
    public User getByPhonePwd(String phone, String upwd) {
        User user = new User();
        user = (User) redisTemplate.opsForValue().get("user");
        if(user == null) {
            System.out.println("数据库");
            user = userDAO.getByPhonePwd(phone, upwd);
            redisTemplate.opsForValue().set("user",user);
            return user;
        }
        System.out.println("缓存");
        return user;
    }

    @Override
    public User getByPhone(String uname) {
        return userDAO.getByPhone(uname);
    }

    @Override
    public User getByUserCode(String tzm) {
        return userDAO.getByUserCode(tzm);
    }

    @Override
    public User getByIdPassword(Long id) {
        return userDAO.getByIdPassword(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public Integer getProductId() {
        return null;
    }

    @Override
    public boolean isForceFefresh() {
        return isForceFefresh;
    }

}
