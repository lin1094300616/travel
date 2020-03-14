package com.examp.travel;

import com.examp.travel.system.model.Collect;
import com.examp.travel.system.model.Scenery;
import com.examp.travel.system.service.ICollectService;
import com.examp.travel.system.service.imp.CollectServiceImpl;
import com.examp.travel.system.service.imp.SceneryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelApplicationTests {

    @Autowired
    SceneryServiceImpl service;

    @Autowired
    ICollectService collectService;

    @Test
    public void contextLoads() {

//        Scenery a = service.findScenery(10001L);
//        List<Scenery> testList = service.findAllByNameAndLocation("三", "");
//
//        System.out.println("testList = " + testList.get(0));

        //update scenery set stock = stock + -1 where scenery_id = 10003
        Collect collect = new Collect();
        collect.setType("scenery");
        collect.setObjectId(10003);
        collectService.stock(collect);
    }

}
