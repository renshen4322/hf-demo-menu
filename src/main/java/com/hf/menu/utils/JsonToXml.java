package com.hf.menu.utils;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

public class JsonToXml {


    /**
     * Json to xml string.
     *
     * @param json the json
     * @return the string
     */
    public static String jsonToXml(String json) {
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<xml>");
            JSONObject jObj = JSON.parseObject(json);
            jsonToXmlstr(jObj, buffer);
            return buffer.toString() + "</xml>";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * Json to xmlstr string.
     *
     * @param jObj   the j obj
     * @param buffer the buffer
     * @return the string
     */
    public static String jsonToXmlstr(JSONObject jObj, StringBuffer buffer) {
        Set<Map.Entry<String, Object>> se = jObj.entrySet();
        for (Iterator<Map.Entry<String, Object>> it = se.iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> en = it.next();
            if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONObject")) {
                buffer.append("<" + en.getKey() + ">");
                JSONObject jo = jObj.getJSONObject(en.getKey());
                jsonToXmlstr(jo, buffer);
                buffer.append("</" + en.getKey() + ">");
            } else if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONArray")) {
                JSONArray jarray = jObj.getJSONArray(en.getKey());
                for (int i = 0; i < jarray.size(); i++) {
                    buffer.append("<" + en.getKey() + ">");
                    JSONObject jsonobject = jarray.getJSONObject(i);
                    jsonToXmlstr(jsonobject, buffer);
                    buffer.append("</" + en.getKey() + ">");
                }
            } else if (en.getValue().getClass().getName().equals("java.lang.String")) {
                buffer.append("<" + en.getKey() + ">" + en.getValue());
                buffer.append("</" + en.getKey() + ">");
            }
        }
        return buffer.toString();
    }


    public static JSONObject getJsonFromXml(String xml) throws Exception {
        return getJsonFromXml(xml, "");
    }

    public static JSONObject getJsonFromXml(String xml, String parseArrayNames) throws DocumentException {
        JSONObject jsonObject = new JSONObject();
        Document document = DocumentHelper.parseText(xml);
        //获取根节点元素对象
        Element root = document.getRootElement();
        iterateNodes(root, jsonObject, parseArrayNames);
        return jsonObject;
    }


    /**
     * 遍历元素
     *
     * @param node 元素
     * @param json 将元素遍历完成之后放进JSON对象
     */
    private static void iterateNodes(Element node, JSONObject json, String parseArrayNames) {
        //获取当前元素的名称
        String nodeName = node.getName();
        List<String> parseArrayNamesList = Arrays.asList(parseArrayNames.split(","));
        //判断已遍历的JSON中是否已经有了该元素的名称
        if (json.containsKey(nodeName) || parseArrayNamesList.contains(nodeName)) {
            //该元素在同级下有多个
            Object Object = json.get(nodeName);
            JSONArray array = new JSONArray();
            if (Object instanceof JSONArray) {
                array = (JSONArray) Object;
            } else if (Object != null) {
                array = new JSONArray();
                array.add(Object);
            }
            //获取该元素下所有子元素
            List<Element> listElement = node.elements();
            if (listElement.isEmpty()) {
                //该元素无子元素，获取元素的值
                String nodeValue = node.getTextTrim();
                array.add(nodeValue);
                json.put(nodeName, array);
                return;
            }
            //有子元素
            JSONObject newJson = new JSONObject();
            //遍历所有子元素
            for (Element e : listElement) {
                //递归
                iterateNodes(e, newJson, parseArrayNames);
            }
            array.add(newJson);
            json.put(nodeName, array);
            return;
        }
        //该元素同级下第一次遍历
        //获取该元素下所有子元素
        List<Element> listElement = node.elements();
        if (listElement.isEmpty()) {
            //该元素无子元素，获取元素的值
            String nodeValue = node.getTextTrim();
            json.put(nodeName, nodeValue);
            return;
        }
        //有子节点，新建一个JSONObject来存储该节点下子节点的值
        JSONObject object = new JSONObject();
        //遍历所有一级子节点
        for (Element e : listElement) {
            //递归
            iterateNodes(e, object, parseArrayNames);
        }
        json.put(nodeName, object);
        return;
    }



}
