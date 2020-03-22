package com.examp.travel.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.examp.travel.system.model.Guide;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msi
 * @since 2020-03-22
 */
@Mapper
public interface GuideMapper extends BaseMapper<Guide> {

    /**
     * 条件构造器查询
     * @param wrapper
     * @return
     */
    @Select("select * from guide ${ew.customSqlSegment}")
    List<Guide> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    @Select("select * " +
            "from guide " +
            "where name like '%${value}%'  ")
    List<Guide> findAllByName(String name);

    @Select("select * " +
            "from guide " +
            "where name = #{name}")
    Guide findByName(@Param("name") String name);

    @Delete("delete from guide where guide_id = #{guide_id}")
    int delete(Integer guideId);

    @Select("select * " +
            "from guide " +
            "where guide_id = #{guide_id}")
    Guide findGuide(@Param("guide_id") Integer guideId);

    @Select("select * " +
            "from guide")
    List<Guide> findGuideList();

    @Insert("INSERT INTO `guide` VALUES (#{guideId}, #{title}, #{content}, #{stock}, #{commentStock}, #{userId}, #{userName})")
    @Options(useGeneratedKeys = true, keyColumn = "guideId", keyProperty = "guideId")
    int add(Guide guide);

    @Update("update guide set title = #{title}, content = #{content}, stock = #{stock}, " +
            "commentStock = #{commentStock} " +
            "where guide_id = #{guideId}")
    int update(Guide guide);
}
