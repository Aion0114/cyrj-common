package com.cyrj.common.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * 高度地图API - 地理编码 公共类
 *
 * @author cw
 * @remark 地理地址转经纬度
 * @date 2022/10/7 11:05
 * @return
 */
public class GdMapUtils {

    public static final String GD_KEY = "5100550edbc9162208aa48e7df4734bd";

    public static String getGeographyInfoByAddress(String address) {
        String result = "";
        try {
            // 把字符串转换为URL请求地址
            URL url = new URL("https://restapi.amap.com/v3/geocode/geo?key=" + GD_KEY + "&address=" + address);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 连接会话
            connection.connect();
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();

            // 循环读取流
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            HashMap map = JSON.parseObject(sb.toString(), HashMap.class);

            if(map.get("status") != null && "1".equals(map.get("status").toString())){
                JSONArray jsonArray = JSONUtil.parseArray(map.get("geocodes").toString());
                for (int i = 0; i< jsonArray.size();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    result += (i == 0?"":"&") + jsonObject.getStr("location");
                }

            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败!");
        }
        return result;

    }


    public static void main(String[] args) {
        System.out.println(getGeographyInfoByAddress("广东省广州市珠江新城"));
    }

}
