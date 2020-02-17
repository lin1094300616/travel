package com.examp.travel.system.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.system.dao.PictureMapper;
import com.examp.travel.system.dao.SceneryMapper;
import com.examp.travel.system.model.Picture;
import com.examp.travel.system.model.Scenery;
import com.examp.travel.system.service.ISceneryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msi
 * @since 2020-01-03
 */
@Service
public class SceneryServiceImpl extends ServiceImpl<SceneryMapper, Scenery> implements ISceneryService {

    @Resource
    SceneryMapper sceneryMapper;

    @Resource
    PictureMapper pictureMapper;

    @Override
    public List<Scenery> findAllByNameAndLocation(String name, String location) {
        List<Scenery> sceneryList;
        if ((location == null) || location.trim().equals("")) {
            sceneryList = sceneryMapper.findSceneryByName(name);
        }else {
            sceneryList = sceneryMapper.findSceneryByNameAndLocation(name,location);
        }
        if ((sceneryList == null) || (sceneryList.isEmpty())) {
            return null;
        }
        for (Scenery scenery : sceneryList
             ) {
            List<Picture> pictureList = pictureMapper.findAllByEntityId(scenery.getSceneryId().intValue());
            scenery.setPictureList(pictureList);
        }
        return sceneryList;
    }

    @Override
    public Response add(Scenery scenery) {
        if (sceneryMapper.findByName(scenery.getName()) != null) {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_EXIST.getCode(),StatusEnum.RET_INSERT_EXIST.getData());
        }
        if(sceneryMapper.add(scenery) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Response update(Scenery scenery) {
        Scenery sceneryByName = sceneryMapper.findByName(scenery.getName());
        if ((sceneryByName != null) && (!sceneryByName.getSceneryId().equals(scenery.getSceneryId()))) {
            return  Response.factoryResponse(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
        }
        if(sceneryMapper.update(scenery) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
        }
    }

    @Override
    public Response delete(Long sceneryId) {
        if (sceneryMapper.findScenery(sceneryId) == null) {
            return  Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if(sceneryMapper.delete(sceneryId) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Scenery findScenery(Long sceneryId) {
        return sceneryMapper.findScenery(sceneryId);
    }

    @Override
    public List<Scenery> findSceneryList() {
        return sceneryMapper.findSceneryList();
    }
}
