package com.examp.travel;

import com.examp.travel.system.model.Scenery;
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

    @Test
    public void contextLoads() {

        Scenery a = service.findScenery(10001L);
        List<Scenery> testList = service.findAllByNameAndLocation("三", "");

        System.out.println("testList = " + testList.get(0));

    }

}
