package com.examp.travel.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.CommUtil;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.model.Specialty;
import com.examp.travel.system.service.ISpecialtyService;
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
@RequestMapping("/specialty")
@CrossOrigin
public class SpecialtyController {

    @Autowired
    ISpecialtyService specialtyService;

    @GetMapping("/userId/{userId}")
    public Response findAllByUserId(@PathVariable("userId") Integer userId) {
        List<Specialty> specialtyList = specialtyService.findAllByUserId(userId);
        if (specialtyList == null || specialtyList.isEmpty()) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getCode());
        }
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), specialtyList);
    }

    @PostMapping
    public Response postSpecialty(@RequestBody Specialty specialty) {
        if (CommUtil.isNullString(specialty.getName(), specialty.getType())) {
            return Response.factoryResponse(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }
        return specialtyService.add(specialty);
    }

    @PutMapping
    public Response updateSpecialty(@RequestBody Specialty specialty) {
        return specialtyService.update(specialty);
    }

    @DeleteMapping(value = "/{specialtyId}")
    public Response delete(@PathVariable(value = "specialtyId")Integer specialtyId) {
        return specialtyService.delete(specialtyId);
    }

    @GetMapping(value = "/{specialtyId}")
    public Response getSpecialtyById(@PathVariable("specialtyId") Integer specialtyId) {
        Specialty specialty = specialtyService.findById(specialtyId);
        if (specialty != null) {
            return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), specialty);
        }
        return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @GetMapping(value = "/list")
    public Response findAll() {
        List<Specialty> specialtyList = specialtyService.findAll();
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

        Page<Specialty> pageInfo = PageHelper.startPage(page,size);
        List<Specialty> userList = specialtyService.findWrapper(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), userList, result);
    }
}
