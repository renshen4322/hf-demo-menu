package com.hf.menu;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.hf.menu.utils.ExtBeansUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.*;

public class testInfo {
    public static void main(String[] args) {
//        List<ActionInfo> newList = new ArrayList<>();
//        ActionInfo info = ActionInfo.builder()
//                .index(1).name("测试").build();
//
//        ActionInfo info2 = ActionInfo.builder()
//                .index(8).name("发发发").build();
//
//        ActionInfo info12 = ActionInfo.builder()
//                .index(12).name("爱的力量").build();
//        newList.add(info);
//        newList.add(info2);
//        newList.add(info12);
//        HashMap<Integer, String> HashMap = addHashMap(newList);
//        loopHashMap(HashMap);

        Map<Integer, AttrInfo> map = getInitObjectHashMap();
        AttrInfo stu1 = new AttrInfo(1, "10001", "莎拉波娃");
        AttrInfo stu2 = new AttrInfo(2, "10002", "布沙尔");
        AttrInfo stu3 = new AttrInfo(5, "10003", "哈勒普");

        map.put(stu1.getIndex(), stu1);
        map.put(stu2.getIndex(), stu2);
        map.put(stu3.getIndex(), stu3);


        String attrStr = objectToStr(map);
        System.out.println("attStr=======" + attrStr);
        String str = "{'00011':{'id':'1','name':'silei'},'00012':{'id':'2','name':'布沙尔'},'00013':{'id':'3','name':'哈勒普'}}";
        strToObject(attrStr);


    }

    private static HashMap<Integer, AttrInfo> getInitObjectHashMap() {
        HashMap<Integer, AttrInfo> HashMap =
                new HashMap<Integer, AttrInfo>(20, 0.75f);
        HashMap.put(1, new AttrInfo(1, "", ""));
        HashMap.put(2, new AttrInfo(2, "", ""));
        HashMap.put(3, new AttrInfo(3, "", ""));
        HashMap.put(4, new AttrInfo(4, "", ""));
        HashMap.put(5, new AttrInfo(5, "", ""));
        HashMap.put(6, new AttrInfo(6, "", ""));
        HashMap.put(7, new AttrInfo(7, "", ""));
        HashMap.put(8, new AttrInfo(8, "", ""));
        HashMap.put(9, new AttrInfo(9, "", ""));
        HashMap.put(10, new AttrInfo(10, "", ""));
        HashMap.put(11, new AttrInfo(11, "", ""));
        HashMap.put(12, new AttrInfo(12, "", ""));
        HashMap.put(13, new AttrInfo(13, "", ""));
        HashMap.put(14, new AttrInfo(14, "", ""));
        HashMap.put(15, new AttrInfo(15, "", ""));
        HashMap.put(16, new AttrInfo(16, "", ""));
        HashMap.put(17, new AttrInfo(17, "", ""));
        HashMap.put(18, new AttrInfo(18, "", ""));
        HashMap.put(19, new AttrInfo(19, "", ""));
        HashMap.put(20, new AttrInfo(20, "", ""));
        return HashMap;
    }

    private static HashMap<Integer, String> getInitHashMap() {
        HashMap<Integer, String> HashMap =
                new HashMap<Integer, String>(20, 0.75f);
        HashMap.put(1, "111");
        HashMap.put(2, "222");
        HashMap.put(3, "333");
        HashMap.put(4, "444");
        HashMap.put(5, "");
        HashMap.put(6, "");
        HashMap.put(7, "");
        HashMap.put(8, "");
        HashMap.put(9, "");
        HashMap.put(10, "");
        HashMap.put(11, "");
        HashMap.put(12, "");
        HashMap.put(13, "");
        HashMap.put(14, "");
        HashMap.put(15, "");
        HashMap.put(16, "");
        HashMap.put(17, "");
        HashMap.put(18, "");
        HashMap.put(19, "");
        HashMap.put(20, "");
        return HashMap;
    }

    public static HashMap<Integer, AttrInfo> addListObjectHashMap(List<AttrInfo> infoList) {
        HashMap<Integer, AttrInfo> hashMap = getInitObjectHashMap();
        if (infoList != null) {
            infoList.stream().forEach(c -> {
                AttrInfo attrModel = ExtBeansUtils.map(c, AttrInfo.class);
                hashMap.put(c.getIndex(), attrModel);
            });
        }
        return hashMap;
    }

    public static HashMap<Integer, String> addHashMap(List<ActionInfo> infoList) {
        HashMap<Integer, String> HashMap = getInitHashMap();
        if (infoList != null) {
            infoList.stream().forEach(c -> {
                HashMap.put(c.index, c.name);
            });
        }
        return HashMap;
    }

    public static void loopHashMap(HashMap<Integer, String> HashMap) {
        Set<Map.Entry<Integer, String>> set = HashMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = set.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActionInfo {
        private Integer index;
        private String name;
    }

    public static void strToObject(String str) {
        Map<Integer, AttrInfo> map = JSON.parseObject(str, new TypeReference<Map<Integer, AttrInfo>>() {
        });
        Set<Map.Entry<Integer, AttrInfo>> m = map.entrySet();
        Iterator<Map.Entry<Integer, AttrInfo>> it = m.iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, AttrInfo> en = it.next();
            Integer id = en.getKey();
            AttrInfo stu = en.getValue();
            System.out.println("id:" + id + "==" + stu.getAttrId() + "===" + stu.getAttrName());
        }
        System.out.println(map.size());
    }


    // 把对象转换成JSON字符串
    public static String objectToStr(Map map) {
        String str = JSON.toJSONString(map);
        return str;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    public static class AttrInfo {
        private Integer index;
        private String attrId;
        private String attrName;

        public AttrInfo(Integer index, String attrId, String attrName) {
            this.index = index;
            this.attrId = attrId;
            this.attrName = attrName;
        }
    }


}
