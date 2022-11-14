package com.hf.menu.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.hf.menu.dto.CategoryVo;
import com.hf.menu.mapper.CategoryMapper;
import com.hf.menu.model.CategoryEntity;
import com.hf.menu.service.ICategoryService;
import com.hf.menu.utils.ExtBeansUtils;
import com.hf.menu.utils.ResponseVo;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ICategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public static final String MENU_NAME_ALL_PREFIX = "FH:MENU:ALL:%s";

    //定义了日志类
    private Logger logger = LoggerFactory.getLogger(ICategoryServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public ResponseVo selectCategoryAll() throws Exception {
        String strJson = redisTemplate.opsForValue().get(MENU_NAME_ALL_PREFIX);
        List<CategoryVo> level1Menus = new ArrayList<>();
        try {
            if (!StringUtil.isNullOrEmpty(strJson)) {
                JSONObject jsonObject = JSON.parseObject(strJson);
                JSONArray dataInfo = jsonObject.getJSONArray("data");
                level1Menus = JSON.parseObject(dataInfo.toJSONString(), new TypeReference<List<CategoryVo>>() {
                });
            } else {
                // 先查询出全部类目
                List<CategoryEntity> categories = categoryMapper.selectCategoryAll();
                // 获取一级菜单 , 0 代表一级目录
                List<CategoryVo> entities = ExtBeansUtils.map(categories, CategoryVo.class);

                // 找到所有的一级分类
                for (CategoryVo entity : entities) {
                    if (entity.getParentCid() == 0) {
                        level1Menus.add(entity);
                    }
                }
                //查找子分类
                for (CategoryVo level1Menu : level1Menus) {
                    level1Menu.setChildren(getChildrens(level1Menu, entities));
                }

                //排序
                level1Menus.sort(new Comparator<CategoryVo>() {
                    @Override
                    public int compare(CategoryVo o1, CategoryVo o2) {
                        return (o1.getSort() == null ? 0 : o1.getSort()) - (o2.getSort() == null ? 0 : o2.getSort());
                    }
                });
                String json = JSON.toJSON(level1Menus).toString();
                //缓存30分钟
                redisTemplate.opsForValue().set(MENU_NAME_ALL_PREFIX, String.format("{\"data\":%s%s", json, "}"), 30, TimeUnit.MINUTES);
            }
        }catch (Exception ex){
          logger.error("selectCategoryAll error{}",ex);
          throw new Exception(String.format("selectCategoryAll error %s"),ex);
        }
        return ResponseVo.success(level1Menus);
    }

    /**
     * 根据一级递归调用子级
     *
     * @param categoryList
     * @param rootCategory
     * @return
     */
    /**
     * 递归查找所有的下级分类
     * 在这一级别的分类中找下级分类
     *
     * @param root 当前级别的分类
     * @param all  全部分类
     * @return 下一级分类
     */
    private List<CategoryVo> getChildrens(CategoryVo root, List<CategoryVo> all) {
        List<CategoryVo> children = new ArrayList<>();
        for (CategoryVo a : all) {
            if (a.getParentCid().equals(root.getCatId())) {
                a.setChildren(getChildrens(a, all));
                children.add(a);
            }
        }
        children.sort(new Comparator<CategoryVo>() {
            @Override
            public int compare(CategoryVo o1, CategoryVo o2) {
                return (o1.getSort() == null ? 0 : o1.getSort()) - (o2.getSort() == null ? 0 : o2.getSort());
            }
        });
        return children;
    }

}
