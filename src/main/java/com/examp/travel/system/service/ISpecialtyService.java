package com.examp.travel.system.service;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.system.model.Specialty;
import com.baomidou.mybatisplus.extension.service.IService;

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
public interface ISpecialtyService {

    List<Specialty> findAllByUserId(Integer userId);

    List<Specialty> findAllByName(String name);

    Response add(Specialty specialty);

    Response update(Specialty specialty);

    Response delete(Integer specialtyId);

    Specialty findById(Integer specialtyId);

    List<Specialty> findAll();

    List<Specialty> findWrapper(Map<String, String> queryMap);
}
