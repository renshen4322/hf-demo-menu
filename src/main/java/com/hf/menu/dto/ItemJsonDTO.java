package com.hf.menu.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemJsonDTO {

    private List<Item> itemList;

    @Data
    public static class Item{
        private String inner_name;
        private String display_name;
        private QueryEnumGroupResp.TextLanguages display_name_json;
        private String alias;
        private String description;
        private QueryEnumGroupResp.TextLanguages description_json;
        private QueryEnumGroupResp.TextLanguages highlightcolor_json;
        private String color_value;
        private String day;
        private String prominent_color;
        private String prefix;
        private String icon;
        private Integer sort_no;
        private String default_item_id;
        private String itemdata_version;
    }
}
