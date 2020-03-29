package com.examp.travel.system.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.dao.*;
import com.examp.travel.system.model.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.examp.travel.system.service.ICommentService;
import com.examp.travel.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msi
 * @since 2020-03-21
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    SceneryMapper sceneryMapper;

    @Resource
    SpecialtyMapper specialtyMapper;

    @Resource
    FoodMapper foodMapper;

    @Resource
    GuideMapper guideMapper;

    @Resource
    UserService userService;


    /**
     * 校验评论对象是否存在
     *
     * @param type
     * @param objectId
     * @return
     */
    private boolean check(String type, Integer objectId) {
        switch (type){
            case "scenery":{
                if (sceneryMapper.findScenery(objectId) == null) {
                    return false;
                }
                break;
            }
            case "specialty":{
                if (specialtyMapper.findById(objectId) == null) {
                    return false;
                }
                break;
            }
            case "food":{
                if (foodMapper.findFood(objectId) == null) {
                    return false;
                }
                break;
            }
            case "guide":{
                if (guideMapper.findGuide(objectId) == null) {
                    return false;
                }
                break;
            }
            default: { return false; }
        }
        return true;
    }

    @Override
    public Response findByUserId(Integer userId) {
        List<Comment> collectList = commentMapper.findByUserId(userId);
        if (collectList.isEmpty()) {
            return  Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),collectList);
    }

    @Override
    public Response add(Comment comment) {
        //验证评论目标是否存在
        if (!check(comment.getType(),comment.getObjectId())) {
            return  Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),"评论类型错误或目标不存在");
        }
        User user = userService.findUser(comment.getUserId());
        if (user == null) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), "用户类型错误，请重新输入");
        }
        comment.setUserName(user.getUserName());
        if (commentMapper.add(comment) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
    }

    @Override
    public Response delete(Integer commentId) {
        if (commentMapper.findComment(commentId) == null) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if (commentMapper.delete(commentId) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
    }

    @Override
    public Comment findComment(Integer commentId) {
        return commentMapper.findComment(commentId);
    }

    @Override
    public List<Comment> findCommentList() {
        return commentMapper.findCommentList();
    }

    @Override
    public List<Comment> findWrapper(Map<String, String> queryMap) {
        QueryWrapper<Comment> queryWrapper = PageUtil.getQueryWrapper(queryMap);
        return commentMapper.getAll(queryWrapper);
    }
}
