package com.examp.travel.system.service;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.system.model.Guide;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-03-22
 */
public interface IGuideService extends IService<Guide> {

    Response add(Guide guide);

    Response update(Guide guide);

    Response delete(Integer guideId);

    Guide findGuide(Integer guideId);

    List<Guide> findGuideList();

    List<Guide> findWrapper(Map<String, String> queryMap);
}
