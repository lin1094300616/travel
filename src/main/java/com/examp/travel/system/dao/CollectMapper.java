package com.examp.travel.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.examp.travel.system.model.Collect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msi
 * @since 2020-03-11
 */
@Mapper
public interface CollectMapper extends BaseMapper<Collect> {

    @Select("select * from collect ${ew.customSqlSegment}")
    List<Collect> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    /**
     * 根据表名和收藏数，对被收藏对象进行收藏数更新
     * @param tableName 表名
     * @param keyId 主键名
     * @param stock 当前收藏数
     * @param objectId 收藏对象ID
     * @return
     */
    @Update("update ${tableName} set stock = stock + #{stock} " +
            "where ${keyId}= #{objectId}")
    int update(@Param("tableName")String tableName,
               @Param("keyId")String keyId,
               @Param("stock")Integer stock,
               @Param("objectId")Integer objectId);

    @Select("select * " +
            "from collect " +
            "where type = #{type} " +
            "and user_id = #{userId} " +
            "and object_id = #{ObjectId}")
    Collect getOneCollect(@Param("type")String type, @Param("userId") Integer userId, @Param("ObjectId") Integer ObjectId);
    
    @Select("select * " +
            "from collect " +
            "where user_id = #{userId}")
    List<Collect> findByUserId(@Param("userId") Integer userId);

    @Insert("INSERT INTO `collect` VALUES (#{collectId}, #{type}, #{userId}, #{objectId} )")
    @Options(useGeneratedKeys = true, keyColumn = "collectId", keyProperty = "collectId")
    int add(Collect collect);

    @Delete("delete from collect where collect_id = #{collectId}")
    int delete(Integer collectId);

    @Select("select * " +
            "from collect " +
            "where collect_id = #{collectId}")
    Collect findCollect(Integer collectId);

    @Select("select * " +
            "from collect")
    List<Collect> findCollectList();
}
