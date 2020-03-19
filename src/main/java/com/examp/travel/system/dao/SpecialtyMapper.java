package com.examp.travel.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.examp.travel.system.model.Specialty;
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
public interface SpecialtyMapper extends BaseMapper<Specialty> {
    @Select("select * from specialty ${ew.customSqlSegment}")
    List<Specialty> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    /**
     * 查询
     * @param userId
     * @return
     */
    @Select("SELECT * " +
            "from specialty " +
            "where user_id = #{userId} ")
    List<Specialty> findAllByUserId(@Param("userId")Integer userId);

    /**
     * 按名称模糊查询
     * @param name
     * @return
     */
    @Select("SELECT * " +
            "from specialty " +
            "where name like '%${value}%'  ")
    List<Specialty> findAllByName(String name);

    /**
     * 按名称查询
     * @param name
     * @return
     */
    @Select("SELECT * " +
            "from specialty " +
            "where name = #{name}")
    Specialty findByName(@Param("name") String name);

    /**
     *
     * 新增
     * @param specialty
     * @return
     */
    @Insert("INSERT INTO `specialty` VALUES (#{specialtyId}, #{name}, #{type}, #{introduce}, " +
            "#{address}, #{phone}, #{consumption}, #{openingTime}, #{userId}, #{stock})")
    @Options(useGeneratedKeys = true, keyColumn = "specialtyId", keyProperty = "specialtyId")
    int add(Specialty specialty);

    /**
     * 按ID修改
     * @param specialty
     * @return
     */
    @Update("update specialty set name = #{name}, type = #{type}, introduce = #{introduce}, " +
            "address = #{address}, phone = #{phone}, consumption = #{consumption}, " +
            "opening_time = #{openingTime}, user_id = #{userId}, stock = #{stock} " +
            "where specialty_id = #{specialtyId}")
    int update(Specialty specialty);

    /**
     * 按ID删除
     * @param specialtyId
     * @return
     */
    @Delete("delete from specialty where specialty_id = #{specialtyId}")
    int deleteById(Integer specialtyId);

    /**
     * 按ID查询
     * @param specialtyId
     * @return
     */
    @Select("SELECT * " +
            "from specialty " +
            "where specialty_id = #{specialtyId}")
    Specialty findById(@Param("specialtyId") Integer specialtyId);

    /**
     * 查询全部
     * @return
     */
    @Select("SELECT * " +
            "FROM specialty")
    List<Specialty> findAll();
}
