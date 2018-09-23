package cn.tycoding.service;

import cn.tycoding.entity.User;

/**
 * @author tycoding
 * @date 18-4-7下午9:09
 */
public interface UserService extends BaseService<User> {

    User findByName(String username);

    void changePassword(Long id, String newPassword);
}
