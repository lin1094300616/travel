package com.examp.travel.system.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.dao.PictureMapper;
import com.examp.travel.system.model.Food;
import com.examp.travel.system.dao.FoodMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.examp.travel.system.model.Picture;
import com.examp.travel.system.service.IFoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msi
 * @since 2020-03-19
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements IFoodService {

    @Resource
    FoodMapper foodMapper;

    @Resource
    PictureMapper pictureMapper;


    @Override
    public List<Food> findAllByUserId(Integer userId) {
        return foodMapper.findAllByUserId(userId);
    }

    @Override
    public List<Food> findAllByName(String name) {
        List<Food> foodList = foodMapper.findAllByName(name);
        if ((foodList == null) || (foodList.isEmpty())) {
            return null;
        }
        for (Food food : foodList
        ) {
            List<Picture> pictureList = pictureMapper.findAllByEntityId("food", food.getFoodId().intValue());
            food.setPictureList(pictureList);
        }
        return foodList;
    }

    @Override
    public Response add(Food food) {
        if (foodMapper.findByName(food.getName()) != null) {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_EXIST.getCode(),StatusEnum.RET_INSERT_EXIST.getData());
        }
        if(foodMapper.add(food) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),food.getFoodId());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Response update(Food food) {
        Food foodByName = foodMapper.findByName(food.getName());
        if ((foodByName != null) && (!foodByName.getFoodId().equals(food.getFoodId()))) {
            return  Response.factoryResponse(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
        }
        if(foodMapper.update(food) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),food);
        }else {
            return  Response.factoryResponse(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
        }
    }

    @Override
    public Response delete(Integer foodId) {
        if (foodMapper.findFood(foodId) == null) {
            return  Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if(foodMapper.delete(foodId) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Food findById(Integer foodId) {

        Food food = foodMapper.findFood(foodId);
        food.setPictureList(pictureMapper.findAllByEntityId(
                "food",
                food.getFoodId()));
        return food;
    }

    @Override
    public List<Food> findAll() {
        return foodMapper.findFoodList();
    }

    @Override
    public List<Food> findWrapper(Map<String, String> queryMap) {
        QueryWrapper<Food> queryWrapper = PageUtil.getQueryWrapper(queryMap);
        List<Food> foodList = foodMapper.getAll(queryWrapper);
        if (foodList.isEmpty()) {
            return foodList;
        }
        for (Food food : foodList) {
            List<Picture> pictureList = pictureMapper.findAllByEntityId("food",food.getFoodId());
            food.setPictureList(pictureList);
        }
        return foodList;
    }
}
