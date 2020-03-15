package com.examp.travel.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.CommUtil;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.model.Scenery;
import com.examp.travel.system.service.ISceneryService;
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
 * @since 2020-01-03
 */
@RestController
@RequestMapping("/scenery")
@CrossOrigin
public class SceneryController {

    @Autowired
    ISceneryService sceneryService;

    @PostMapping
    public Response postScenery(@RequestBody Scenery scenery) {
        if (CommUtil.isNullString(scenery.getName(),scenery.getLocation(), scenery.getType())) {
            return Response.factoryResponse(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }
        return sceneryService.add(scenery);
    }

    @PutMapping
    public Response updateScenery(@RequestBody Scenery scenery) {
        return sceneryService.update(scenery);
    }

    @DeleteMapping(value = "/{sceneryId}")
    public Response delete(@PathVariable(value = "sceneryId")Integer sceneryId) {
        return sceneryService.delete(sceneryId);
    }

    @GetMapping(value = "/{sceneryId}")
    public Response getSceneryById(@PathVariable("sceneryId") Integer sceneryId) {
        Scenery scenery = sceneryService.findScenery(sceneryId);
        if (scenery != null) {
            return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), scenery);
        }
        return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @GetMapping(value = "/list")
    public Response getScenerys() {
        List<Scenery> specialtyList = sceneryService.findSceneryList();
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


        Page<Scenery> pageInfo = PageHelper.startPage(page,size);
        List<Scenery> userList = sceneryService.findWrapper(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), userList, result);
    }
}
