package com.examp.travel.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.CommUtil;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.model.Guide;
import com.examp.travel.system.model.User;
import com.examp.travel.system.service.IGuideService;
import com.examp.travel.system.service.UserService;
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
 * @since 2020-03-22
 */
@RestController
@RequestMapping("/guide")
public class GuideController {

    @Autowired
    IGuideService guideService;

    @Autowired
    UserService userService;

    @PostMapping
    public Response postGuide(@RequestBody Guide guide) {
        User user = userService.findUser(guide.getUserId());
        if (user == null) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), "用户类型错误，请重新输入");
        }
        return guideService.add(guide);
    }

    @PutMapping
    public Response updateGuide(@RequestBody Guide guide) {
        return guideService.update(guide);
    }

    @DeleteMapping(value = "/{guideId}")
    public Response delete(@PathVariable(value = "guideId")Integer guideId) {
        return guideService.delete(guideId);
    }

    @GetMapping(value = "/{guideId}")
    public Response getGuideById(@PathVariable("guideId") Integer guideId) {
        Guide guide = guideService.findGuide(guideId);
        if (guide != null) {
            return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), guide);
        }
        return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @GetMapping(value = "/list")
    public Response getGuides() {
        List<Guide> specialtyList = guideService.findGuideList();
        if (specialtyList == null || specialtyList.isEmpty()) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getCode());
        }
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), specialtyList);
    }

    @PostMapping("/page")
    public Response findByPage(@RequestBody Map<String,String> queryMap) {
        //获取分页信息，并从查询条件中去除
        Integer page = Integer.valueOf(queryMap.get("page"));
        Integer size = Integer.valueOf(queryMap.get("size"));
        queryMap.remove("page");
        queryMap.remove("size");


        Page<Guide> pageInfo = PageHelper.startPage(page,size);
        List<Guide> userList = guideService.findWrapper(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), userList, result);
    }

}
