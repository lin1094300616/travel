package com.examp.travel.system.service;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.system.model.Specialty;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-01-03
 */
public interface ISpecialtyService {

    List<Specialty> findAllByName(String name);

    Response add(Specialty specialty);

    Response update(Specialty specialty);

    Response delete(Long specialtyId);

    Specialty findById(Long specialtyId);

    List<Specialty> findAll();
}
