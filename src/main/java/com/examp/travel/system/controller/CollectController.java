package com.examp.travel.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.CommUtil;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.model.Collect;
import com.examp.travel.system.service.ICollectService;
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
 * 交通
 * @author msi
 * @since 2020-03-11
 */
@RestController
@RequestMapping("/collect")
@CrossOrigin
public class CollectController {

    @Autowired
    ICollectService collectService;

    @PostMapping
    public Response post(@RequestBody Collect collect) {
        return collectService.stock(collect);
    }

    @GetMapping("/{userId}")
    public Response findCollect(@PathVariable("userId") Integer userId) {
        return collectService.findByUserId(userId);
    }

    @PostMapping("/check")
    public Response check(@RequestBody Collect collect) {
        return collectService.check(collect);
    }

    @PostMapping("/page")
    public Response findByPage(@RequestBody Map<String,String> queryMap) {
        //获取分页信息，并从查询条件中去除
        Integer page = Integer.valueOf(queryMap.get("page"));
        Integer size = Integer.valueOf(queryMap.get("size"));
        queryMap.remove("page");
        queryMap.remove("size");

        Page<Collect> pageInfo = PageHelper.startPage(page,size);
        List<Collect> userList = collectService.findWrapper(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), userList, result);
    }
}
