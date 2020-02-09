package com.examp.travel.system.service.imp;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.FileUtil;
import com.examp.travel.system.dao.PictureMapper;
import com.examp.travel.system.model.Picture;
import com.examp.travel.system.service.IPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msi
 * @since 2020-01-05
 */
@Service
public class PictureServiceImpl  implements IPictureService {

    @Resource
    PictureMapper pictureMapper;
    
    @Override
    public List<Picture> findAllByEntityId(Integer entityId) {
        return pictureMapper.findAllByEntityId(entityId);
    }

    @Override
    public Response deleteByEntityId(Integer entityId) {
        if (pictureMapper.deleteByEntityId(entityId) > 0) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  Response.factoryResponse(StatusEnum.RET_DELETE_FAIL.getCode(),StatusEnum.RET_DELETE_FAIL.getData());
    }

    @Override
    public Response add(Picture picture) {
        if(pictureMapper.add(picture) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
    }


    @Override
    public Response delete(Integer pictureId) {
        Picture picture = pictureMapper.findById(pictureId);
        if (picture == null) {
            return  Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if(pictureMapper.delete(pictureId) == 1) {
            FileUtil.deleteDir(new File(picture.getPath()));
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  Response.factoryResponse(StatusEnum.RET_DELETE_FAIL.getCode(),StatusEnum.RET_DELETE_FAIL.getData());
    }

    @Override
    public Picture findById(Integer pictureId) {
        return pictureMapper.findById(pictureId);
    }

    @Override
    public List<Picture> findPictureList() {
        return pictureMapper.findList();
    }
}
