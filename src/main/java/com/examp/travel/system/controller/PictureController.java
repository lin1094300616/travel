package com.examp.travel.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.FileUtil;
import com.examp.travel.framework.util.PageUtil;
import com.examp.travel.system.model.Picture;
import com.examp.travel.system.model.User;
import com.examp.travel.system.service.IPictureService;
import com.examp.travel.system.service.imp.PictureServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author msi
 * @since 2020-01-05
 */
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    IPictureService pictureService;

    /**按实体查询**/

    /**按实体删除**/

    /**新增**/
    /**
     *
     * @param fileList  文件集合
     * @param entityId  关联实体ID
     * @param type      关联实体类型（模块）
     * @return
     */
    @PostMapping(value = "/picture")
    public Response add2(@RequestParam("fileList") MultipartFile[] fileList,
                         @RequestParam("entityId") Integer entityId,
                         @RequestParam("type") String type){

        if (Objects.isNull(fileList) || fileList.length == 0) {
            return Response.factoryResponse(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }

        for (MultipartFile file: fileList
        ) {
            if (!FileUtil.isImageFile(file.getOriginalFilename())) {
                return Response.factoryResponse(StatusEnum.SYSTEM_ERROR_9004.getCode(),StatusEnum.SYSTEM_ERROR_9004.getData());
            }
            /**转换成base64 保存字符串**/
            String base64String = FileUtil.getImageString(file);

            /**直接保存文件**/
            String uploadPath = FileUtil.fileUpload(type,entityId,file);
            if (uploadPath == null) {
                System.out.println("直接保存文件失败");
            }
            //实例化 picture 写入数据，保存
            Picture picture = new Picture();
            picture.setType(type);
            picture.setEntityId(entityId);
            picture.setPath(uploadPath);
            picture.setPictureBase64(base64String);
            pictureService.add(picture);

        }
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), StatusEnum.RESPONSE_OK.getData());
    }


    @PostMapping(value = "/picture2")
    public Response add3(@RequestParam("file") MultipartFile file,
                         @RequestParam("entityId") Integer entityId,
                         @RequestParam("type") String type){

        if (Objects.isNull(file)) {
            return Response.factoryResponse(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }

        if (!FileUtil.isImageFile(file.getOriginalFilename())) {
            return Response.factoryResponse(StatusEnum.SYSTEM_ERROR_9004.getCode(),StatusEnum.SYSTEM_ERROR_9004.getData());
        }

        /**转换成base64 保存字符串**/
        String base64String = FileUtil.getImageString(file);
        /**直接保存文件**/
        String uploadPath = FileUtil.fileUpload(type,entityId,file);
        if (uploadPath == null) {
            System.out.println("直接保存文件失败");
        }

        //实例化 picture 写入数据，保存
        Picture picture = new Picture();
        picture.setType(type);
        picture.setEntityId(entityId);
        picture.setPath(uploadPath);
        picture.setPictureBase64(base64String);
        pictureService.add(picture);

        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), StatusEnum.RESPONSE_OK.getData());
    }

    /**删除**/
    @DeleteMapping(value = "/{pictureId}")
    public Response delete(@PathVariable(value = "pictureId")Integer pictureId) {
        return pictureService.delete(pictureId);
    }

    /**查询全部**/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Response findList() {
        List<Picture> pictureList = pictureService.findPictureList();
        if ((pictureList == null) || pictureList.isEmpty()) {
            return Response.factoryResponse(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), pictureList);
    }

    /**分页查询全部**/
    @GetMapping("/page/{page}/{size}")
    public Response findByPage(@PathVariable(value = "page")Integer page, @PathVariable(value = "size")Integer size) {
        //分页并查询
        Page<User> pageInfo = PageHelper.startPage(page, size);
        List<Picture> pictureList = pictureService.findPictureList();
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), pictureList, result);
    }
}
