package com.hf.menu.client;

import com.hf.menu.config.FeiGeSendMsgProperties;
import com.hf.menu.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SendMsgClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeiGeSendMsgProperties msgProperties;

    public ResponseVo sendMsgInfo(String phone) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        String url = msgProperties.getUrl();
        headers.add("Content-Type", "application/json");
        Map<String, String> map = new HashMap<>();
        map.put("apikey", msgProperties.getApikey());
        map.put("secret", msgProperties.getSecret());
        map.put("sign_id", msgProperties.getSign_id());
        map.put("mobile", phone);
        map.put("content", String.format("你的短信验证码是：%s",smsCode()));
        String body = "";
        HttpEntity entity = new HttpEntity(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        body = responseEntity.getBody();
        return ResponseVo.success(body);
    }

    public static void main(String[] args) {

       // System.out.println(smsCode());

    }
    /**
     * @Title: smsCode
     * @Description: TODO( 产生验证码)
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public  String smsCode(){
        String random = new Random().nextInt(1000000)+"";
        if(random.length()!=6){
            return smsCode();
        }else{
            return random;
        }
    }


}
