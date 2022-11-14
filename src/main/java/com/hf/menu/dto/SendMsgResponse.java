package com.hf.menu.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SendMsgResponse implements Serializable {
    private static final long serialVersionUID = -7368828592141168311L;

    private Integer code;

    private String msg;

    private String msg_no;

    private int count;
}
