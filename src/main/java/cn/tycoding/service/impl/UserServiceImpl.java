package cn.tycoding.service.impl;

import cn.tycoding.entity.User;
import cn.tycoding.mapper.UserMapper;
import cn.tycoding.service.PasswordHelper;
import cn.tycoding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tycoding
 * @date 18-4-7下午9:09
 */
@Service
public class UserServiceImpl implements UserService {

    //注入
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        User user = userMapper.findById(id);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userMapper.update(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findById(Long id) {
        return null;
    }

    @Override
    public void create(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        userMapper.create(user);
    }

    @Override
    public void update(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        userMapper.update(user);
    }

    @Override
    public void delete(Long... ids) {
        for (Long id : ids){
            userMapper.delete(id);
        }
    }
}
