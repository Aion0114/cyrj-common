package com.cyrj.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

@Component
public class FileUploadUtil {
	COSClient cosClient;

    long appId = 1251289569;

    String secretId = "AKID0uc8TcjO2Ulrpb4aqusfGersjOr8lRYT";

    String secretKey = "ra8UltsmHcliT2Zu3INGyZ3P1UlR2Rgj";

    String region = "";


    // 设置bucket所在的区域，比如华南园区：szbucket； 新加坡 xjp
    String bucketName = "szbucket";

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public FileUploadUtil(){
        // 初始化秘钥信息
        Credentials cred = new Credentials(appId, secretId, secretKey);
        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh 新加坡 sgp；
        clientConfig.setRegion("gz");
        // 初始化cosClient
        cosClient = new COSClient(clientConfig, cred);

    }

    /**
     * 上传文件
     * @param file
     * @param fileType
     * @return
     */
    public JSONObject upload(byte[] file, String fileType, String path){

        Date date = new Date();
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,path+date.getTime()+"."+fileType, file);

        String uploadResult =cosClient.uploadFile(uploadFileRequest);
        JSONObject resultJson = JSON.parseObject(uploadResult);

        return resultJson;
    }

    /**
     * 上传文件
     * @param file
     * @param path
     * @return
     * @throws IOException
     */
    public JSONObject upload(File file, String path) throws IOException {

        Date date = new Date();
        String fileName = file.getName();
        path += date.getTime()+"/"+fileName;

        byte[] bytes = new byte[Integer.valueOf(String.valueOf(file.length()))];
        InputStream inputStream = new FileInputStream(file);
        inputStream.read(bytes);
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, path, bytes);
        String uploadResult =cosClient.uploadFile(uploadFileRequest);
        JSONObject resultJson = JSON.parseObject(uploadResult);
        // 关闭流
        if (inputStream != null) {
            inputStream.close();
        }
        return resultJson;

    }
    /**
     * 上传文件
     * @param file
     * @param path
     * @return
     * @throws IOException
     */
    public JSONObject upload(String bucketName,File file, String path) throws IOException {
        String tempPath = path + bucketName;
        File pathFile = new File(tempPath);
        if (!pathFile.exists()) {
            boolean m = pathFile.mkdirs();
        }
        Date date = new Date();
        String fileName = file.getName();
        path += date.getTime()+"/"+fileName;

        byte[] bytes = new byte[Integer.valueOf(String.valueOf(file.length()))];
        InputStream inputStream = new FileInputStream(file);
        inputStream.read(bytes);

        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, path, bytes);
        String uploadResult = cosClient.uploadFile(uploadFileRequest);
        JSONObject resultJson = JSON.parseObject(uploadResult);
        System.out.println(resultJson);
        // 关闭流
        if (inputStream != null) {
            inputStream.close();
        }
        return resultJson;

    }
}

