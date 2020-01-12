package com.examp.travel.system.service.imp;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.Md5Util;
import com.examp.travel.system.dao.UserMapper;
import com.examp.travel.system.model.User;
import com.examp.travel.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class {@code Object} is 用户业务层实现类，实现业务处理.
 * @author MSI
 * @version 1.0
 */
@Service
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean login(String userName, String password, HttpSession session) {
        User user = userMapper.findByUserName(userName);
        if (user == null) {
            return  false;
        }
        if (!user.getPassword().equals(Md5Util.transMD5(userName + password))) {
            return false;
        }
        session.setAttribute("user",user);
        return true;
    }

    @Override
    public Response add(User user) {
        /**判断用户名是否存在**/
        if (userMapper.findByUserName(user.getUserName()) != null) {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_EXIST.getCode(),StatusEnum.RET_INSERT_EXIST.getData());
        }
        /**获得MD5加密密码**/
        String md5Password = Md5Util.transMD5(user.getUserName() + user.getPassword());
        user.setPassword(md5Password);
        if(userMapper.add(user) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Response update(User user) {
        if (userMapper.findByUserName(user.getUserName()) != null) {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_EXIST.getCode(),StatusEnum.RET_INSERT_EXIST.getData());
        }
        if(userMapper.update(user) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Response delete(Integer userId) {
        if (userMapper.findUser(userId) == null) {
            return  Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if(userMapper.delete(userId) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public User findUser(Integer userId) {
        return userMapper.findUser(userId);
    }

    @Override
    public List<User> findUserList() {
        return userMapper.findUserList();
    }
}