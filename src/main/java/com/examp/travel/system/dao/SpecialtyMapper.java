package com.examp.travel.system.dao;

import com.examp.travel.system.model.Specialty;
import com.examp.travel.system.model.Specialty;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.examp.travel.system.model.User;
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
public interface SpecialtyMapper {

    @Select("select specialty_id, name, type, introduce, pictureId  " +
            "from specialty" +
            "where name = #{name}")
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO `specialty` VALUES (#{specialtyId}, #{name}, #{location}, #{level}, #{type}, #{introduce}, #{pictureId})")
    @Options(useGeneratedKeys = true, keyColumn = "specialtyId", keyProperty = "specialtyId")
    int add(Specialty specialty);

    @Update("update specialty set name = #{name},location = #{location}," +
            "level = #{level}, type = #{type}, introduce = #{introduce} ,pictureId = #{pictureId} " +
            "where specialty_id = #{specialtyId}")
    int update(Specialty specialty);

    @Delete("delete from specialty where specialty_id = #{specialty_id}")
    int delete(Long specialtyId);

    @Select("select specialty_id, name, location, level, type, introduce, pictureId " +
            "from specialty " +
            "where specialty_id = #{specialty_id}")
    Specialty findSpecialty(@Param("specialty_id") Long specialtyId);

    @Select("select specialty_id, name, location, level, type, introduce, pictureId  " +
            "from specialty")
    List<Specialty> findSpecialtyList();
}
