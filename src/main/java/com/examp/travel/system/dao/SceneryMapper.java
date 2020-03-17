package com.examp.travel.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.examp.travel.system.model.Scenery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msi
 * @since 2020-01-03
 */
@Mapper
public interface SceneryMapper extends BaseMapper<Scenery> {

    /**
     * 条件构造器查询
     * @param wrapper
     * @return
     */
    @Select("select * from scenery ${ew.customSqlSegment}")
    List<Scenery> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    @Select("select scenery_id, name, location, level, type, introduce, ticket_price, opening_time, phone, address, traffic, ticket " +
            "from scenery " +
            "where name like '%${value}%'  ")
    List<Scenery> findAllByName(String name);

    @Select("select scenery_id, name, location, level, type, introduce, ticket_price, opening_time, phone, address, traffic, ticket " +
            "from scenery " +
            "where name like '%${value}%' " +
            "and location = #{location}")
    List<Scenery> findAllByNameAndLocation(@Param(value = "value") String name,@Param(value = "location")String location);

    /**2020-02-11**/
    @Select("select scenery_id, name, location, level, type, introduce, ticket_price, opening_time, phone, address, traffic, ticket " +
            "from scenery " +
            "where name = #{name}")
    Scenery findByName(@Param("name") String name);

    @Insert("INSERT INTO `scenery` VALUES (#{sceneryId}, #{name}, #{location}, #{level}, #{type}, #{introduce}, #{ticketPrice}, #{openingTime}, #{phone}, #{address}, #{traffic}, #{ticket}, #{stock})")
    @Options(useGeneratedKeys = true, keyColumn = "sceneryId", keyProperty = "sceneryId")
    int add(Scenery scenery);

    @Update("update scenery set name = #{name},location = #{location},level = #{level}, " +
            "type = #{type}, introduce = #{introduce} , ticket_price = #{ticketPrice}, " +
            "opening_time = #{openingTime},phone = #{phone},address = #{address}, " +
            "traffic = #{traffic},ticket = #{ticket} " +
            "where scenery_id = #{sceneryId}")
    int update(Scenery scenery);

    @Delete("delete from scenery where scenery_id = #{scenery_id}")
    int delete(Integer sceneryId);

    @Select("select scenery_id, name, location, level, type, introduce, ticket_price, opening_time, phone, address, traffic, ticket " +
            "from scenery " +
            "where scenery_id = #{scenery_id}")
    Scenery findScenery(@Param("scenery_id") Integer sceneryId);

    @Select("select scenery_id, name, location, level, type, introduce, ticket_price, opening_time, phone, address, traffic, ticket " +
            "from scenery")
    List<Scenery> findSceneryList();

}
