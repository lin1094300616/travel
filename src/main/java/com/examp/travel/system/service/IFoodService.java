package com.examp.travel.system.service;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.system.model.Food;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-03-19
 */
public interface IFoodService extends IService<Food> {
    
    List<Food> findAllByUserId(Integer userId);

    List<Food> findAllByName(String name);

    Response add(Food food);

    Response update(Food food);

    Response delete(Integer foodId);

    Food findById(Integer foodId);

    List<Food> findAll();

    List<Food> findWrapper(Map<String, String> queryMap);
}
