package com.examp.travel.system.service;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.system.model.Collect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-03-11
 */
public interface ICollectService extends IService<Collect> {

    Response check(Collect collect);

    Response findByUserId(Integer userId);

    Response stock(Collect collect);

    Response add(Collect collect);

    Response delete(Integer collectId);

    Collect findCollect(Integer collectId);

    List<Collect> findCollectList();

    List<Collect> findWrapper(Map<String, String> queryMap);
}
