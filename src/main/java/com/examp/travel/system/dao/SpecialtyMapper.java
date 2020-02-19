package com.examp.travel.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    /**
     * 查询
     * @param userId
     * @return
     */
    @Select("SELECT specialty_id, name, type, introduce, address, phone, price, opening_time, user_id " +
            "from specialty " +
            "where user_id = #{userId} ")
    List<Specialty> findAllByUserId(@Param("userId")Long userId);

    /**
     * 按名称模糊查询
     * @param name
     * @return
     */
    @Select("SELECT specialty_id, name, type, introduce, address, phone, price, opening_time, user_id " +
            "from specialty " +
            "where name like '%${value}%'  ")
    List<Specialty> findAllByName(String name);

    /**
     * 按名称查询
     * @param name
     * @return
     */
    @Select("SELECT specialty_id, name, type, introduce, address, phone, price, opening_time, user_id " +
            "from specialty " +
            "where name = #{name}")
    Specialty findByName(@Param("name") String name);

    /**
     * 新增
     * @param specialty
     * @return
     */
    @Insert("INSERT INTO `specialty` VALUES (#{specialtyId}, #{name}, #{type}, #{introduce}, #{address}, #{phone}, #{price}, #{openingTime}, #{userId})")
    @Options(useGeneratedKeys = true, keyColumn = "specialtyId", keyProperty = "specialtyId")
    int add(Specialty specialty);

    /**
     * 按ID修改
     * @param specialty
     * @return
     */
    @Update("update specialty set name = #{name}, type = #{type}, introduce = #{introduce},  address = #{address}, " +
            "phone = #{phone}, price = #{price}, opening_time = #{openingTime}, user_id = #{userId} " +
            "where specialty_id = #{specialtyId}")
    int update(Specialty specialty);

    /**
     * 按ID删除
     * @param specialtyId
     * @return
     */
    @Delete("delete from specialty where specialty_id = #{specialtyId}")
    int deleteById(Long specialtyId);

    /**
     * 按ID查询
     * @param specialtyId
     * @return
     */
    @Select("SELECT specialty_id, name, type, introduce, address, phone, price, opening_time, user_id " +
            "from specialty " +
            "where specialty_id = #{specialtyId}")
    Specialty findById(@Param("specialtyId") Long specialtyId);

    /**
     * 查询全部
     * @return
     */
    @Select("SELECT specialty_id, name, type, introduce, address, phone, price, opening_time, user_id " +
            "FROM specialty")
    List<Specialty> findAll();
}
