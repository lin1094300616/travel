package com.examp.travel.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.examp.travel.system.model.Food;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msi
 * @since 2020-03-19
 */
@Mapper
public interface FoodMapper extends BaseMapper<Food> {

    /**
     * 条件构造器查询
     * @param wrapper
     * @return
     */
    @Select("select * from food ${ew.customSqlSegment}")
    List<Food> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    /**
     * 查询
     * @param userId
     * @return
     */
    @Select("SELECT * " +
            "from specialty " +
            "where user_id = #{userId} ")
    List<Food> findAllByUserId(@Param("userId")Integer userId);

    @Select("select * " +
            "from food " +
            "where name like '%${value}%'  ")
    List<Food> findAllByName(String name);

    @Select("select * " +
            "from food " +
            "where name like '%${value}%' " +
            "and location = #{location}")
    List<Food> findAllByNameAndLocation(@Param(value = "value") String name,@Param(value = "location")String location);

    /**2020-02-11**/
    @Select("select * " +
            "from food " +
            "where name = #{name}")
    Food findByName(@Param("name") String name);

    /**
     * @param food
     * @return
     */
    @Insert("INSERT INTO `food` VALUES (#{foodId}, #{name}, #{type}, #{area},  #{introduce}, " +
            "#{address}, #{phone}, #{consumption}, #{traffic}, #{openingTime}, #{userId}, " +
            "#{stock}, #{commentStock})")
    @Options(useGeneratedKeys = true, keyColumn = "foodId", keyProperty = "foodId")
    int add(Food food);

    @Update("update food set name = #{name}, type = #{type}, area = #{area}, " +
            "introduce = #{introduce}, address = #{address}, phone = #{phone}, " +
            "consumption = #{consumption}, traffic = #{traffic}, " +
            "opening_time = #{openingTime}, user_id = #{userId}, stock = #{stock} " +
            "where food_id = #{foodId}")
    int update(Food food);

    @Delete("delete from food where food_id = #{foodId}")
    int delete(Integer foodId);

    @Select("select * " +
            "from food " +
            "where food_id = #{foodId}")
    Food findFood(Integer foodId);

    @Select("select * " +
            "from food")
    List<Food> findFoodList();
}
