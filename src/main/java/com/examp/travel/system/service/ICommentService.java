package com.examp.travel.system.service;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.system.model.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-03-21
 */
public interface ICommentService extends IService<Comment> {

    Response findByUserId(Integer userId);

    Response add(Comment collect);

    Response delete(Integer collectId);

    Comment findComment(Integer collectId);

    List<Comment> findCommentList();

    List<Comment> findWrapper(Map<String, String> queryMap);
}
