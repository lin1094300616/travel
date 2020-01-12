package com.examp.travel.framework.util;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * @ClassName: PageUtil
 * @Description: 分页工具类
 * @Author: MSI
 * @Date: 2019/1/7 15:31
 * @Vresion: 1.0.0
 **/
public class PageUtil {

    /**
     * @Author MSI
     * @Description 封装分页信息
     * @Date 2019/1/7 15:51
     * @Param [page]
     * @return com.alibaba.fastjson.JSONObject 
     **/       
    public static JSONObject pageInfo(Page page) {
        JSONObject result = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        //封装分页信息

        jsonObject.put("total",page.getTotal()); //总条目数
        jsonObject.put("pageNum",page.getPageSize()); //本页条数
        jsonObject.put("currentPage",page.getPageNum()); //页码

        result.put("total",jsonObject); //写入分页信息
        result.put("data",page.getResult()); //写入内容
        return result;
    }

    public static JSONObject pageBaseInfo(Page page) {

        JSONObject info = new JSONObject();
        //封装分页信息

        info.put("total",page.getTotal()); //总条目数
        info.put("pageNum",page.getPageSize()); //本页条数
        info.put("currentPage",page.getPageNum()); //页码
        return info;
    }

}
