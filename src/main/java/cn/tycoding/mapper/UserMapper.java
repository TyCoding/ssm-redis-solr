package cn.tycoding.mapper;

import cn.tycoding.entity.User;

/**
 * @author tycoding
 * @date 18-4-7下午9:10
 */
public interface UserMapper {

    User findByName(String username);

    User findById(Long id);

    void update(User user);

    void create(User user);

    void delete(Long id);
}
