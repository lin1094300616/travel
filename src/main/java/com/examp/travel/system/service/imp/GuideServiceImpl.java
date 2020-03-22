package com.examp.travel.system.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.dao.PictureMapper;
import com.examp.travel.system.model.Guide;
import com.examp.travel.system.dao.GuideMapper;
import com.examp.travel.system.model.Picture;
import com.examp.travel.system.service.IGuideService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2020-03-22
 */
@Service
public class GuideServiceImpl extends ServiceImpl<GuideMapper, Guide> implements IGuideService {

    @Resource
    GuideMapper guideMapper;

    @Resource
    PictureMapper pictureMapper;
    
    @Override
    public Response add(Guide guide) {
        if(guideMapper.add(guide) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),guide.getGuideId());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Response update(Guide guide) {
        if(guideMapper.update(guide) == 1) {
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),guide);
        }else {
            return  Response.factoryResponse(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
        }
    }

    @Override
    public Response delete(Integer guideId) {
        if (guideMapper.findGuide(guideId) == null) {
            return  Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if(guideMapper.delete(guideId) == 1) {
            pictureMapper.deleteByEntityId("guide", guideId);
            return  Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  Response.factoryResponse(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Guide findGuide(Integer guideId) {
        Guide guide = guideMapper.findGuide(guideId);
        if (guide != null && guide.getGuideId() > 0) {
            guide.setPictureList(pictureMapper.findAllByEntityId(
                    "guide", guide.getGuideId()));
        }
        return guide;
    }

    @Override
    public List<Guide> findGuideList() {
        return guideMapper.findGuideList();
    }

    @Override
    public List<Guide> findWrapper(Map<String, String> queryMap) {
        QueryWrapper<Guide> queryWrapper = PageUtil.getQueryWrapper(queryMap);
        List<Guide> guideList = guideMapper.getAll(queryWrapper);
        if (!guideList.isEmpty()) {
            for (Guide guide : guideList) {
                List<Picture> pictureList = pictureMapper.findAllByEntityId("guide",guide.getGuideId());
                guide.setPictureList(pictureList);
            }
        }
        return guideList;
    }
}
