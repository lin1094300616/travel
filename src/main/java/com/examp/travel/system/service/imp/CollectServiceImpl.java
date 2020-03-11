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
    public Response stock(Collect collect) {
        String tableName = collect.getType();
        Integer stock = collect.getStock();
        String keyId = tableName + "_id";
        if(collectMapper.update(tableName, keyId, stock, collect.getObjectId()) > 0) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  Response.factoryResponse(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response add(Collect collect) {
        if (collectMapper.add(collect) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
    }

    @Override
    public Response delete(Long collectId) {
        if (collectMapper.findCollect(collectId) == null) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if (collectMapper.delete(collectId) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
    }

    @Override
    public Collect findCollect(Long collectId) {
        return collectMapper.findCollect(collectId);
    }

    @Override
    public List<Collect> findCollectList() {
        return collectMapper.findCollectList();
    }
}
