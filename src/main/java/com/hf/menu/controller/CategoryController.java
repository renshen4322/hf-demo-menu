package com.hf.menu.controller;

import com.hf.menu.service.ICategoryService;
import com.hf.menu.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/list/tree", method = RequestMethod.GET)
    public ResponseVo list() throws Exception {
        return categoryService.selectCategoryAll();
    }
}
