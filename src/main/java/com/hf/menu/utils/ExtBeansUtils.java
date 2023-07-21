package com.hf.menu.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ProjectName:
 * @Description: bean的工具类
 * @Author: mark
 * @CreateDate: 2022/10/13
 * @Version: 1.0
 */
public class ExtBeansUtils {


    /**
     * deal bean copy
     *
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T map(Object source, Class<T> targetClass) {
        if (Objects.isNull(source)) {
            return null;
        }
        T target = BeanUtils.instantiateClass(targetClass);
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * deal arrayList copy
     *
     * @param source
     * @param targetClass
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<T> map(List<V> source, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>();
        if (CollectionUtils.isEmpty(source)) {
            return targetList;
        }
        source.forEach(e -> targetList.add(map(e, targetClass)));
        return targetList;
    }



}
