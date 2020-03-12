package com.examp.travel.system.service.imp;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.system.model.Collect;
import com.examp.travel.system.dao.CollectMapper;
import com.examp.travel.system.service.ICollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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


//        if(collectMapper.update(tableName, keyId, stock, collect.getObjectId()) > 0) {
//            if (stock >= 1 && collect.getCollectId() > 0){
//                collectMapper.add(collect);
//            }else {
//                collectMapper.delete(collect.getCollectId());
//            }
//            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
//        }
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
}