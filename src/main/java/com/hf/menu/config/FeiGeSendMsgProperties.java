package com.hf.menu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("feige.msg")
@Data
public class FeiGeSendMsgProperties {

    private String url;

    private String apikey;

    private String secret;

    private String sign_id;
}
