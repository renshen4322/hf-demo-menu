package com.hf.menu.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategoryVo implements Serializable {
    private static final long serialVersionUID = -5257356313082792174L;

    /**
     * 分类id
     */
    private Long catId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父分类id
     */
    private Long parentCid;

    /**
     * 层级
     */
    private Integer catLevel;

    /**
     * 是否显示[0-不显示，1显示]
     */
    private Byte showStatus;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标地址
     */
    private String icon;

    /**
     * url地址
     */
    private String url;


    /**
     * 存放类目子级目录
     */
    private List<CategoryVo> children;

}
