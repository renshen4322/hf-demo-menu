package com.hf.menu.dto;

import lombok.Data;

import java.util.List;

@Data
public class QueryEnumGroupResp {

    /**
     * //内部名称
     */
    private String inner_name;

    /**
     * //显示名称
     */
    private String display_name;

    /**
     * //说明
     */
    private String description;

    /**
     * 0枚举组织器 1枚举
     */
    private String enum_data_type;

    private String type_id;

    private String group_id;

    private String group_name;

    private String domain_id;

    private String domain_name;

    private String item_id;

    private Integer is_enum_levelone;

    private String parent_item_id;

    /**
     * 图标URL
     */
    private String icon;


    private TextLanguages display_name_json;

    private TextLanguages descripton_json;

    private List<ItemJson> available_item_list;

    private List<ItemJson> selected_item_list;

    private String corp_id;


    @Data
    public static class TextLanguages {
        private String host_text;
        private List<LanguageModels> languageModels;
    }

    @Data
    public static class LanguageModels {
        private String language_id;
        private String language_name;
        private String language_value;
    }

    @Data
    public static class ItemJson {
        private String innerName;
        private String displayName;
        private TextLanguages display_name_json;
        private String alias;
        private String description;
        private TextLanguages description_json;
        private TextLanguages highlightcolor_json;
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
