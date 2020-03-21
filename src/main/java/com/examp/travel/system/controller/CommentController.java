package com.examp.travel.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.model.Comment;
import com.examp.travel.system.service.ICommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author msi
 * @since 2020-03-21
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    ICommentService commentService;

    @PostMapping
    public Response post(@RequestBody Comment comment) {
        return commentService.add(comment);
    }

    @DeleteMapping("/{commentId}")
    public Response delete(@PathVariable("commentId") Integer commentId) {
        return commentService.delete(commentId);
    }

    @GetMapping("/{userId}")
    public Response findComment(@PathVariable("userId") Integer userId) {
        return commentService.findByUserId(userId);
    }

    @PostMapping("/page")
    public Response findByPage(@RequestBody Map<String,String> queryMap) {
        //获取分页信息，并从查询条件中去除
        Integer page = Integer.valueOf(queryMap.get("page"));
        Integer size = Integer.valueOf(queryMap.get("size"));
        queryMap.remove("page");
        queryMap.remove("size");

        Page<Comment> pageInfo = PageHelper.startPage(page,size);
        List<Comment> userList = commentService.findWrapper(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), userList, result);
    }
}
