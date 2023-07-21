package com.hf.menu.model;

import java.io.Serializable;
import lombok.Data;

/**
 * category
 * @author 
 */
@Data
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = 7108644812882765124L;
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
     * url地址
     */
    private String url;

    /**
     * 图标地址
     */
    private String icon;


}