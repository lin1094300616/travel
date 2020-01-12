package com.examp.travel.system.service;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.system.model.Scenery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-01-03
 */
public interface ISceneryService {

    Response add(Scenery scenery);

    Response update(Scenery scenery);

    Response delete(Long sceneryId);

    Scenery findScenery(Long sceneryId);

    List<Scenery> findSceneryList();
}
