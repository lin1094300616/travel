package com.examp.travel.system.dao;

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

    @Update("update ${tableName} set stock = stock + #{stock} " +
            "where ${keyId}= #{objectId}")
    int update(@Param("tableName")String tableName,
               @Param("keyId")String keyId,
               @Param("stock")Integer stock,
               @Param("objectId")Integer objectId);

    @Select("select * " +
            "from collect " +
            "where user_id = #{userId}")
    List<Collect> findByUserId(@Param("userId") Long userId);

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
