package com.examp.travel.system.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.dao.PictureMapper;
import com.examp.travel.system.dao.SpecialtyMapper;
import com.examp.travel.system.model.Picture;
import com.examp.travel.system.model.Specialty;
import com.examp.travel.system.service.ISpecialtyService;
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
 * @since 2020-01-03
 */
@Service
public class SpecialtyServiceImpl extends ServiceImpl<SpecialtyMapper, Specialty> implements ISpecialtyService {

    @Resource
    SpecialtyMapper specialtyMapper;

    @Resource
    PictureMapper pictureMapper;

    @Override
    public List<Specialty> findAllByUserId(Integer userId) {
        return specialtyMapper.findAllByUserId(userId);
    }

    @Override
    public List<Specialty> findAllByName(String name) {
        List<Specialty> specialtyList = specialtyMapper.findAllByName(name);
        if ((specialtyList == null) || (specialtyList.isEmpty())) {
            return null;
        }
        for (Specialty specialty : specialtyList
        ) {
            List<Picture> pictureList = pictureMapper.findAllByEntityId("specialty", specialty.getSpecialtyId().intValue());
            specialty.setPictureList(pictureList);
        }
        return specialtyList;
    }

    @Override
    public Response add(Specialty specialty) {
        if (specialtyMapper.findByName(specialty.getName()) != null) {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_EXIST.getCode(),StatusEnum.RET_INSERT_EXIST.getData());
        }
        if(specialtyMapper.add(specialty) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),specialty.getSpecialtyId());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Response update(Specialty specialty) {
        Specialty specialtyByName = specialtyMapper.findByName(specialty.getName());
        if ((specialtyByName != null) && (!specialtyByName.getSpecialtyId().equals(specialty.getSpecialtyId()))) {
            return  Response.factoryResponse(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
        }
        if(specialtyMapper.update(specialty) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),specialty);
        }else {
            return  Response.factoryResponse(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
        }
    }

    @Override
    public Response delete(Integer specialtyId) {
        if (specialtyMapper.findById(specialtyId) == null) {
            return  Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if(specialtyMapper.delete(specialtyId) == 1) {
            pictureMapper.deleteByEntityId("specialty", specialtyId);
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Specialty findById(Integer specialtyId) {

        Specialty specialty = specialtyMapper.findById(specialtyId);
        specialty.setPictureList(pictureMapper.findAllByEntityId(
                "specialty",
                specialty.getSpecialtyId()));
        return specialty;
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyMapper.findAll();
    }

    @Override
    public List<Specialty> findWrapper(Map<String, String> queryMap) {
        QueryWrapper<Specialty> queryWrapper = PageUtil.getQueryWrapper(queryMap);
        List<Specialty> specialtyList = specialtyMapper.getAll(queryWrapper);
        if (specialtyList.isEmpty()) {
            return specialtyList;
        }
        for (Specialty specialty : specialtyList
        ) {
            List<Picture> pictureList = pictureMapper.findAllByEntityId(
                    "specialty",
                    specialty.getSpecialtyId());
            specialty.setPictureList(pictureList);
        }
        return specialtyList;
    }
}
