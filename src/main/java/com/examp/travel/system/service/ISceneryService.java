package com.examp.travel.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.system.model.Scenery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-01-03
 */
public interface ISceneryService extends IService<Scenery> {

    List<Scenery> findAllByNameAndLocation(String name, String location);

    Response add(Scenery scenery);

    Response update(Scenery scenery);

    Response delete(Integer sceneryId);

    Scenery findScenery(Integer sceneryId);

    List<Scenery> findSceneryList();

    List<Scenery> findWrapper(Map<String, String> queryMap);
}
