package com.hf.menu.controller;

import com.hf.menu.client.SendMsgClient;
import com.hf.menu.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg/")
public class SendMsgController {

    @Autowired
    private SendMsgClient client;

    @RequestMapping(value = "send/{phone}", method = RequestMethod.POST)
    public ResponseVo sendInfo(@PathVariable String phone)  {
        return client.sendMsgInfo(phone);
    }
}
