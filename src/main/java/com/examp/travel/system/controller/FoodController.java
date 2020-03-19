package com.examp.travel.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.CommUtil;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.model.Food;
import com.examp.travel.system.service.IFoodService;
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
 * @since 2020-03-19
 */
@RestController
@RequestMapping("/food")
@CrossOrigin
public class FoodController {

    @Autowired
    IFoodService foodService;

    @GetMapping("/userId/{userId}")
    public Response findAllByUserId(@PathVariable("userId") Integer userId) {
        List<Food> foodList = foodService.findAllByUserId(userId);
        if (foodList == null || foodList.isEmpty()) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getCode());
        }
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), foodList);
    }

    @PostMapping
    public Response postFood(@RequestBody Food food) {
        if (CommUtil.isNullString(food.getName(), food.getType())) {
            return Response.factoryResponse(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }
        return foodService.add(food);
    }

    @PutMapping
    public Response updateFood(@RequestBody Food food) {
        return foodService.update(food);
    }

    @DeleteMapping(value = "/{foodId}")
    public Response delete(@PathVariable(value = "foodId")Integer foodId) {
        return foodService.delete(foodId);
    }

    @GetMapping(value = "/{foodId}")
    public Response getFoodById(@PathVariable("foodId") Integer foodId) {
        Food food = foodService.findById(foodId);
        if (food != null) {
            return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), food);
        }
        return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @GetMapping(value = "/list")
    public Response findAll() {
        List<Food> foodList = foodService.findAll();
        if (foodList == null || foodList.isEmpty()) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getCode());
        }
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), foodList);
    }

    @PostMapping("/page")
    public Response findByPage(@RequestBody Map<String,String> queryMap) {
        //获取分页信息，并从查询条件中去除
        Integer page = Integer.valueOf(queryMap.get("page"));
        Integer size = Integer.valueOf(queryMap.get("size"));
        queryMap.remove("page");
        queryMap.remove("size");

        Page<Food> pageInfo = PageHelper.startPage(page,size);
        List<Food> userList = foodService.findWrapper(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), userList, result);
    }
}
