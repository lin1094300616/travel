package com.examp.travel.system.controller;

import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;
import com.examp.travel.framework.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class {@code Object} is .
 *
 * @author MSI
 * @version 1.0
 */
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

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
            /**直接保存文件**/
            FileUtil.fileUpload(type,entityId,file);
            /**转换成base64 保存字符串**/
            String base64String = FileUtil.getImageString(file);
            //实例化 picture 写入数据，保存

        }

        return Response.factoryResponse(StatusEnum.RESPONSE_OK.getCode(), StatusEnum.RESPONSE_OK.getData());
    }



    @PostMapping("/singlefile")
    public Object singleFileUpload(MultipartFile file) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return "文件为空，请重新上传";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }


}
