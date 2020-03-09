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
import java.util.Map;

/**
 * Class {@code Object} is 视图层，数据接收、返回与校验.
 *
 * @author MSI
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private static final String LOGIN_OK = "登录成功！";

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Response login(@RequestBody User user, HttpSession session) {
        if (CommUtil.isNullString(user.getUserName(),user.getPassword())) {
            return Response.factoryResponse(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }
        return userService.login(user.getUserName(), user.getPassword(), session);
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

    @GetMapping(value = "/{userId}")
    public Response getUserById(@PathVariable("userId") Integer userId) {
        User user = userService.findUser(userId);
        if (user != null) {
            return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), user);
        }
        return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @GetMapping(value = "/list")
    public Response getUsers() {
        List<User> userList = userService.findUserList();
        if ((userList == null) || userList.isEmpty()) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), userList);
    }

    @PostMapping("/page")
    public Response findByPage(@RequestBody Map<String,String> queryMap) {
        //获取分页信息，并从查询条件中去除
        Integer page = Integer.valueOf(queryMap.get("page"));
        Integer size = Integer.valueOf(queryMap.get("size"));
        queryMap.remove("page");
        queryMap.remove("size");

        Page<User> pageInfo = PageHelper.startPage(page,size);
        List<User> userList = userService.findWrapper(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), userList, result);
    }
}
