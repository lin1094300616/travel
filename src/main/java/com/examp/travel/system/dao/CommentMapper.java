package com.examp.travel.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.examp.travel.system.model.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msi
 * @since 2020-03-21
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select * from comment ${ew.customSqlSegment}")
    List<Comment> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    /**
     * 添加/删除 评论时关联修改对象表中评论数
     * @param tableName
     * @param keyId
     * @param stock
     * @param objectId
     * @return
     */
    @Update("update ${tableName} set commentStock = commentStock + #{stock} " +
            "where ${keyId}= #{objectId}")
    int update(@Param("tableName")String tableName,
               @Param("keyId")String keyId,
               @Param("stock")Integer stock,
               @Param("objectId")Integer objectId);

    /**
     * 查询单条评论信息
     * @param type
     * @param userId
     * @param ObjectId
     * @return
     */
    @Select("select * " +
            "from comment " +
            "where type = #{type} " +
            "and user_id = #{userId} " +
            "and object_id = #{ObjectId}")
    Comment getOneComment(@Param("type")String type, @Param("userId") Integer userId, @Param("ObjectId") Integer ObjectId);

    /**
     * 根据用户ID查询所有评论
     * @param userId
     * @return
     */
    @Select("select * " +
            "from comment " +
            "where user_id = #{userId}")
    List<Comment> findByUserId(@Param("userId") Integer userId);

    @Insert("INSERT INTO `comment` VALUES (#{commentId}, #{commentName}, " +
            "#{commentContent}, #{score}, #{type}, #{userId}, #{userName}, #{objectId} )")
    @Options(useGeneratedKeys = true, keyColumn = "commentId", keyProperty = "commentId")
    int add(Comment comment);

    @Delete("delete from comment where comment_id = #{commentId}")
    int delete(Integer commentId);

    @Select("select * " +
            "from comment " +
            "where comment_id = #{commentId}")
    Comment findComment(Integer commentId);

    @Select("select * " +
            "from comment")
    List<Comment> findCommentList();
}
