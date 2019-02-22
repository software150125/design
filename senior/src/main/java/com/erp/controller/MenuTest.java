package com.erp.controller;

import com.erp.common.JSONUtil;
import com.erp.plugin.MenuWebVo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.net.InetAddress;

public class MenuTest {
    public final static String menuJsonFilePath = MainController.class.getClassLoader().getResource("erpMenu.json").getFile();


    @Autowired
    @Qualifier("redisTemplate")
    private static RedisTemplate<String,String> redisTemplate;


    public static void getMenu(){
        try{
            String str = FileUtils.readFileToString(new File(menuJsonFilePath),"UTF-8");
            str = str.replaceAll("|\r|\n|\t", "");
            System.out.println(str);
            System.out.println("-------------");
            MenuWebVo vo = JSONUtil.json2pojo(str, MenuWebVo.class);
            System.out.println(JSONUtil.obj2json(vo));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getMenuByRedis(){
        String str = redisTemplate.opsForValue().get("AES");
        System.out.println(str);
    }



    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {
        getMenu();
        //System.out.println("-------------");
        //getMenuByRedis();
    }
}


