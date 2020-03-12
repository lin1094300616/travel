package com.examp.travel.system.controller;


import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.CommUtil;
import com.examp.travel.system.model.Collect;
import com.examp.travel.system.service.ICollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author msi
 * @since 2020-03-11
 */
@RestController
@RequestMapping("/collect/collect")
public class CollectController {

    @Autowired
    ICollectService collectService;

    @PostMapping
    public Response postScenery(@RequestBody Collect collect) {
        Response response;
        if (collect.getStock() > 0) {
             response = collectService.add(collect);
        }else {
            response = collectService.delete(collect.getCollectId());
        }
        return response;
    }
}
