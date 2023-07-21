package com.hf.menu;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.hf.menu.dto.CategoryVo;
import com.hf.menu.utils.ActionTypeEnum;
import com.hf.menu.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class HfDemoMenuApplicationTests {

    public static final String MENU_NAME_ALL_PREFIX = "FH:MENU:ALL:%s";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String ENUM_PREFIX = "column:enum:";

    @Test
    void contextLoads() {
        String key = String.format("%s%s",ENUM_PREFIX,"0000000002");
        String str = redisTemplate.opsForValue().get(key);
        System.out.println(str);
        redisTemplate.delete(key);

//        JSONObject jsonObject = JSON.parseObject(str);
//        JSONArray error = jsonObject.getJSONArray("data");
//        List<CategoryVo> datas = JSON.parseObject(error.toJSONString(), new TypeReference<List<CategoryVo>>() {
//        });

   //     System.out.println(JSON.toJSONString(datas));
    }

    @Test
    public void Test1() {

        String v = "1.0.1";
        String[] vs = v.split("[.]");

        int len = vs.length;

        for (int i = 0; i < len; i++) {
            System.out.println(vs[i]);
        }
        UUID uid = UUID.randomUUID();
        System.out.println(uid);

        Date date = new Date();
        long times = date.getTime();
        System.out.println(DateUtil.dateToStamp(date));


        String str = DateUtil.stampToDate(String.valueOf(times));
        System.out.println(str);
    }

    @Test
    public void Test2() {
        List<String> list = new ArrayList<>();
        list.add("ab");
        list.add("23");
        list.add("te");
        String idStrings = String.join(",", list);
        System.out.println(idStrings);
        System.out.println(ActionTypeEnum.getName(2));

        try {
            String time = "2011/07/29 14:50:11";
            Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(time);
            long unixTimestamp = date.getTime()/1000;
            System.out.println(unixTimestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String switchTypeToStr(Integer tp) {
        String typeStr = "";
        switch (tp) {
            case 0:
                typeStr = "查看";
                break;
            case 1:
                typeStr = "创建";
                break;
            case 2:
                typeStr = "更新";
                break;
            case 3:
                typeStr = "删除";
                break;
            default:
                typeStr = "查看";

        }
        return typeStr;
    }

    @Test
    public void Test3() {
        List<String> list = new ArrayList<>();
        list.add("ab");
        list.add("23");
        list.add("te");
        
    }
}
