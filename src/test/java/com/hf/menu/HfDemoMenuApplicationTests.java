package com.hf.menu;

import com.hf.menu.dto.QueryEnumGroupResp;
import com.hf.menu.utils.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class HfDemoMenuApplicationTests {

    public static final String MENU_NAME_ALL_PREFIX = "FH:MENU:ALL:%s";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String ENUM_PREFIX = "column:type:";
    @Test
    public void Test88(){
        String name = "a666";
        System.out.println(ChartUtil.isValidNaming(name));
    }

    @Test
    void contextLoads() {
        String key = String.format("%s%s",ENUM_PREFIX,"0000000001");
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
    public void TestSorted(){
        List<QueryEnumGroupResp.ItemJson> listItem = new ArrayList<>();
        QueryEnumGroupResp.ItemJson itemJson = new QueryEnumGroupResp.ItemJson();
        itemJson.setDisplayName("yux009535");
        itemJson.setInnerName("yux");
        QueryEnumGroupResp.ItemJson itemJson2 = new QueryEnumGroupResp.ItemJson();
        itemJson2.setDisplayName("ppp99");
        itemJson2.setInnerName("ppp");
        QueryEnumGroupResp.ItemJson itemJson3 = new QueryEnumGroupResp.ItemJson();
        itemJson3.setDisplayName("testone");
        itemJson3.setInnerName("tes");
        QueryEnumGroupResp.ItemJson itemJson4 = new QueryEnumGroupResp.ItemJson();
        itemJson4.setDisplayName("质量");
        itemJson4.setInnerName("qer");
        QueryEnumGroupResp.ItemJson itemJson5 = new QueryEnumGroupResp.ItemJson();
        itemJson5.setDisplayName("陈江");
        itemJson5.setInnerName("cjh");
        QueryEnumGroupResp.ItemJson itemJson6 = new QueryEnumGroupResp.ItemJson();
        itemJson6.setDisplayName("材料bom");
        itemJson6.setInnerName("ews");
        listItem.add(itemJson);
        listItem.add(itemJson2);
        listItem.add(itemJson3);
        listItem.add(itemJson4);
        listItem.add(itemJson5);
        listItem.add(itemJson6);

        List<QueryEnumGroupResp.ItemJson>listJson = new ArrayList<QueryEnumGroupResp.ItemJson>();

        listItem.forEach(c->{
            QueryEnumGroupResp.ItemJson entity = new QueryEnumGroupResp.ItemJson();
            entity.setAlias(PinyinUtils.converterToFirstSpell(c.getDisplayName().substring(0,1)).toUpperCase());
            entity.setInnerName(c.getInnerName());
            entity.setDisplayName(c.getDisplayName());
            listJson.add(entity);
        });
       // List<Map<String, Object>> list = testMapper.get();
        List<String> nameList =
                listJson.stream()
                        .map(element->element.getDisplayName().substring(0, 1))
                        .map(i-> PinyinUtils.converterToFirstSpell(i).toLowerCase())
                        .sorted()
                        .collect(Collectors.toList());
       // System.out.println(Arrays.toString(nameList.toArray()));
       // System.out.println(nameList.size());

        Comparator comparator = Collator.getInstance(Locale.CHINA);
        Collections.sort(listJson, (p1, p2) -> comparator.compare(PinyinUtils.converterToFirstSpell(p1.getDisplayName().substring(0,1)).toLowerCase(),
                PinyinUtils.converterToFirstSpell(p2.getDisplayName().substring(0,1)).toLowerCase()));
        listJson.stream().forEach(m->{
            System.out.println(String.format("别名%s------显示名称:::%s",m.getAlias(),m.getDisplayName()));
        });



//        for ( QueryEnumGroupResp.ItemJson m: listJson) {
//            System.out.println(String.format("别名%s------显示名称:::%s",m.getAlias(),m.getDisplayName()));
//        }

//        listItem.stream().peek(c->{
//
//            c.setAlias(ChangeToPinYinJP.changeToGetShortPinYin(c.getInnerName()));
//            c.setInnerName(ChangeToPinYinJP.changeToGetShortPinYin(c.getInnerName()));
//            c.setDisplayName(ChangeToPinYinJP.changeToGetShortPinYin(c.getDisplayName()));
//
//        }).map(m->m.getAlias()).collect(Collectors.toList());
//        listItem.stream().forEach(p->{
//            System.out.println(String.format("别名%s------显示名称:::%s",p.getAlias(),p.getDisplayName()));
//        });



        String tt = ChangeToPinYinJP.changeToGetShortPinYin("李明宇");
        System.out.println(tt);
    }


    private <T> List<AlphabetWrapper<T>> getAlphabetWrappers(Stream<T> items, Function<T, String> func) {
        List<AlphabetWrapper<T>> alphabets = items
                .collect(Collectors.groupingBy(c -> getFirstPinyinLetter(func.apply(c))))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> new AlphabetWrapper<>(Character.toString(entry.getKey()).toUpperCase(), entry.getValue()))
                .collect(Collectors.toList());
        return alphabets;
    }

    private char getFirstPinyinLetter(String chinese) {
        return ChangeToPinYinJP.changeToFirstPinYin(chinese);
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
