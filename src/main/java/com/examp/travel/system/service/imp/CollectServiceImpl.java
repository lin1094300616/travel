package com.examp.travel.system.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.dao.SceneryMapper;
import com.examp.travel.system.dao.SpecialtyMapper;
import com.examp.travel.system.model.Collect;
import com.examp.travel.system.dao.CollectMapper;
import com.examp.travel.system.model.Scenery;
import com.examp.travel.system.model.Specialty;
import com.examp.travel.system.service.ICollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    public List<Collect> findByCollectList(List<Collect> collectList) {


        return collectList;
    }

    @Override
    public Response findByUserId(Integer collectId) {
        List<Collect> collectList = collectMapper.findByUserId(collectId);
        if (collectList.isEmpty()) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        for (Collect collect : collectList) {
            if (collect.getType().equals("scenery")) {
                Scenery scenery = sceneryMapper.findScenery(collect.getObjectId());
                if (scenery != null) {
                    collect.setScenery(scenery);
                }
            }else if (collect.getType().equals("specialty")) {
                Specialty specialty = specialtyMapper.findById(collect.getObjectId());
                collect.setSpecialty(specialty);
            }
        }
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),collectList);
    }

    /**
     * @param collect
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response stock(Collect collect) {
        String tableName = collect.getType();
        //Integer stock = collect.getStock();
        String keyId = tableName + "_id";
        //查询收藏记录是否存在
        Collect stockCollect = collectMapper.findByUserIdAndObjectId(collect.getUserId(), collect.getObjectId());
        //不存在，则新增
        if (stockCollect == null) {
            collectMapper.update(tableName, keyId, 1, collect.getObjectId());
            if (collectMapper.add(collect) <= 0) {
                return Response.factoryResponse(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
            }
        }else {
            //存在，则删除
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
