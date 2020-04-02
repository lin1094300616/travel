package com.examp.travel.system.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.dao.*;
import com.examp.travel.system.model.*;
import com.examp.travel.system.service.ICollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msi
 * @since 2020-03-11
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements ICollectService {

    @Resource
    CollectMapper collectMapper;

    @Resource
    SceneryMapper sceneryMapper;

    @Resource
    SpecialtyMapper specialtyMapper;

    @Resource
    FoodMapper foodMapper;

    @Resource
    GuideMapper guideMapper;

    @Resource
    PictureMapper pictureMapper;

    @Override
    public Response check(Collect collect) {
        Collect oneCollect = collectMapper.getOneCollect(collect.getType(), collect.getUserId(), collect.getObjectId());
        if (oneCollect != null) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),oneCollect);
        }
        return  Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    /**
     * 根据用户Id 获得用户的收藏列表（对不同模块进行分类）
     * @param userId
     * @return
     */
    @Override
    public Response findByUserId(Integer userId) {
        /**查询用户是否有收藏数据**/
        List<Collect> collectList = collectMapper.findByUserId(userId);
        if (collectList.isEmpty()) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }

        /**定义收藏类别**/
        List<Scenery> sceneryList = new ArrayList<>();
        List<Specialty> specialtyList = new ArrayList<>();
        List<Food> foodList = new ArrayList<>();
        List<Guide> guideList = new ArrayList<>();

        /**遍历收藏信息，根据收藏类型，查询收藏数据**/
        for (Collect collect : collectList) {
            /**收藏数据为景点**/
            if (collect.getType().equals("scenery")) {
                /**查询景点信息**/
                Scenery scenery = sceneryMapper.findScenery(collect.getObjectId());
                /**景点不为空**/
                if (scenery != null) {
                    /**查询景点关联的图片信息**/
                    List<Picture> pictureList = pictureMapper.findAllByEntityId(collect.getType(), scenery.getSceneryId());
                    scenery.setPictureList(pictureList);
                    sceneryList.add(scenery);
                }
            }else if (collect.getType().equals("specialty")) {/**收藏数据为特产**/
                /**查询特产信息**/
                Specialty specialty = specialtyMapper.findById(collect.getObjectId());
                /**特产不为空**/
                if (specialty != null) {
                    /**查询特产关联的图片信息**/
                    List<Picture> pictureList = pictureMapper.findAllByEntityId(collect.getType(), specialty.getSpecialtyId());
                    specialty.setPictureList(pictureList);
                    specialtyList.add(specialty);
                }
            }else if(collect.getType().equals("food")) {
                Food food = foodMapper.findFood(collect.getObjectId());
                if (food != null) {
                    List<Picture> pictureList = pictureMapper.findAllByEntityId(collect.getType(), food.getFoodId());
                    food.setPictureList(pictureList);
                    foodList.add(food);
                }
            }else if(collect.getType().equals("guide")) {
                Guide guide = guideMapper.findGuide(collect.getObjectId());
                if (guide != null) {
                    List<Picture> pictureList = pictureMapper.findAllByEntityId(collect.getType(), guide.getGuideId());
                    guide.setPictureList(pictureList);
                    guideList.add(guide);
                }
            }
            //攻略模块查询时附带图片

        }

        /**数据整合，将所有结果集整合进JSON对象中，并返回**/
        JSONObject jsonArray = new JSONObject();
        jsonArray.put("collectList",collectList);
        jsonArray.put("sceneryList",sceneryList);
        jsonArray.put("specialtyList",specialtyList);
        jsonArray.put("guideList",guideList);
        jsonArray.put("foodList",foodList);
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),jsonArray);
    }

    /**
     * @param collect
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class) /**开启事务，更新操作失败时事务回滚**/
    public Response stock(Collect collect) {
        /**根据收藏类型，获得表名**/
        String tableName = collect.getType();
        /**使用表名，拼接主键字段名**/
        String keyId = tableName + "_id";
        /**查询收藏记录是否存在**/
        Collect stockCollect = collectMapper.getOneCollect(tableName,collect.getUserId(), collect.getObjectId());

        /**收藏记录不存在，进行新增操作**/
        if (stockCollect == null) {
            collectMapper.update(tableName, keyId, 1, collect.getObjectId());
            if (collectMapper.add(collect) <= 0) {
                return Response.factoryResponse(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
            }
        }else {/**收藏记录存在，则进行删除操作**/
            collectMapper.update(tableName, keyId, -1, collect.getObjectId());
            if (collectMapper.delete(stockCollect.getCollectId()) <= 0) {
                return Response.factoryResponse(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
            }
        }
        return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());

    }

    @Override
    public Response add(Collect collect) {
        if (collectMapper.add(collect) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
    }

    @Override
    public Response delete(Integer collectId) {
        if (collectMapper.findCollect(collectId) == null) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if (collectMapper.delete(collectId) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
    }

    @Override
    public Collect findCollect(Integer collectId) {
        return collectMapper.findCollect(collectId);
    }

    @Override
    public List<Collect> findCollectList() {
        return collectMapper.findCollectList();
    }

    @Override
    public List<Collect> findWrapper(Map<String, String> queryMap) {
        QueryWrapper<Collect> queryWrapper = PageUtil.getQueryWrapper(queryMap);
        return collectMapper.getAll(queryWrapper);
    }
}
