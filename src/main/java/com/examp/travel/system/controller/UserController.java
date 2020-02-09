package com.examp.travel.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.CommUtil;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.model.User;
import com.examp.travel.system.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class {@code Object} is 视图层，数据接收、返回与校验.
 *
 * @author MSI
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String LOGIN_OK = "登录成功！";

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Response login(@RequestBody User user, HttpSession session) {
        if (CommUtil.isNullString(user.getName(),user.getPassword())) {
            return Response.factoryResponse(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }
        if (userService.login(user.getUserName(),user.getPassword(),session)) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),LOGIN_OK);
        }
        return  Response.factoryResponse(StatusEnum.USER_ERROR_1001.getCode(),StatusEnum.USER_ERROR_1001.getData());
    }

    @PostMapping("/loginOut")
    public Response loginOut(HttpSession session) {
        //查看session是否存在用户数据，有就清空
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
    }

    @PostMapping
    public Response postUser(@RequestBody User user) {
        if (CommUtil.isNullString(user.getName(),user.getPassword(),user.getPhone())) {
            return Response.factoryResponse(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }
        return userService.add(user);
    }

    @PutMapping()
    public Response updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping(value = "/{userId}")
    public Response delete(@PathVariable(value = "userId")Integer userId) {
        return userService.delete(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Response getUserById(@PathVariable("userId") Integer userId) {
        User user = userService.findUser(userId);
        if (user != null) {
            return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), user);
        }
        return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Response getUsers() {
        List<User> userList = userService.findUserList();
        if ((userList == null) || userList.isEmpty()) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), userList);
    }

    @GetMapping("/page/{page}/{size}")
    public Response findByPage(@PathVariable(value = "page")Integer page, @PathVariable(value = "size")Integer size) {
        //分页并查询
        Page<User> pageInfo = PageHelper.startPage(page, size);
        List<User> users = userService.findUserList();
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), users, result);
    }
}
