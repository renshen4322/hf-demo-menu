package com.hf.menu.mapper;

import com.hf.menu.model.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CategoryMapper")
@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Long catId);

    int insert(CategoryEntity record);

    int insertSelective(CategoryEntity record);

    CategoryEntity selectByPrimaryKey(Long catId);

    int updateByPrimaryKeySelective(CategoryEntity record);

    int updateByPrimaryKey(CategoryEntity record);

    List<CategoryEntity> selectCategoryAll();
}