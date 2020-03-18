package com.examp.travel.system.service;


import com.examp.travel.framework.entity.Response;
import com.examp.travel.system.model.Picture;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-01-05
 */
public interface IPictureService  {

    List<Picture> findAllByEntityId(String type, Integer entityId);

    Response deleteByEntityId(Integer entityId);

    Response add(Picture picture);

    Response delete(Integer pictureId);

    Picture findById(Integer pictureId);

    List<Picture> findPictureList();
}
