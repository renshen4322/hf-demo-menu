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
        //???????????????????????????
        Element root = document.getRootElement();
        iterateNodes(root, jsonObject, parseArrayNames);
        return jsonObject;
    }


    /**
     * ????????????
     *
     * @param node ??????
     * @param json ?????????????????????????????????JSON??????
     */
    private static void iterateNodes(Element node, JSONObject json, String parseArrayNames) {
        //???????????????????????????
        String nodeName = node.getName();
        List<String> parseArrayNamesList = Arrays.asList(parseArrayNames.split(","));
        //??????????????????JSON???????????????????????????????????????
        if (json.containsKey(nodeName) || parseArrayNamesList.contains(nodeName)) {
            //??????????????????????????????
            Object Object = json.get(nodeName);
            JSONArray array = new JSONArray();
            if (Object instanceof JSONArray) {
                array = (JSONArray) Object;
            } else if (Object != null) {
                array = new JSONArray();
                array.add(Object);
            }
            //?????????????????????????????????
            List<Element> listElement = node.elements();
            if (listElement.isEmpty()) {
                //??????????????????????????????????????????
                String nodeValue = node.getTextTrim();
                array.add(nodeValue);
                json.put(nodeName, array);
                return;
            }
            //????????????
            JSONObject newJson = new JSONObject();
            //?????????????????????
            for (Element e : listElement) {
                //??????
                iterateNodes(e, newJson, parseArrayNames);
            }
            array.add(newJson);
            json.put(nodeName, array);
            return;
        }
        //?????????????????????????????????
        //?????????????????????????????????
        List<Element> listElement = node.elements();
        if (listElement.isEmpty()) {
            //??????????????????????????????????????????
            String nodeValue = node.getTextTrim();
            json.put(nodeName, nodeValue);
            return;
        }
        //???????????????????????????JSONObject????????????????????????????????????
        JSONObject object = new JSONObject();
        //???????????????????????????
        for (Element e : listElement) {
            //??????
            iterateNodes(e, object, parseArrayNames);
        }
        json.put(nodeName, object);
        return;
    }



}
