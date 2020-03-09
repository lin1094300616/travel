package com.examp.travel.system.service;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.system.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Class {@code Object} is 用户业务层接口类.
 *
 * @author MSI
 * @version 1.0
 */
public interface UserService {

    Response login(String userName, String password, HttpSession session);

    Response add(User user);

    Response update(User user);

    Response delete(Integer userId);

    User findUser(Integer userId);

    List<User> findUserList();

    List<User> findWrapper(Map<String, String> querymap);

}