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
    public Response delete(@PathVariable(value = "sceneryId")Long sceneryId) {
        return sceneryService.delete(sceneryId);
    }

    @RequestMapping(value = "/{sceneryId}", method = RequestMethod.GET)
    public Response getSceneryById(@PathVariable("sceneryId") Long sceneryId) {
        Scenery scenery = sceneryService.findScenery(sceneryId);
        if (scenery != null) {
            return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), scenery);
        }
        return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Scenery> getScenerys() {
        return sceneryService.findSceneryList();
    }

    @GetMapping("/page/{page}/{size}")
    public Response findByPage(@PathVariable(value = "page")Integer page, @PathVariable(value = "size")Integer size) {
        //分页并查询
        Page<Scenery> pageInfo = PageHelper.startPage(page, size);
        List<Scenery> scenerys = sceneryService.findSceneryList();
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), scenerys, result);
    }
}
