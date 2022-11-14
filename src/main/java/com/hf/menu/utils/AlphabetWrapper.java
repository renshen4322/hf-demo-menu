package com.hf.menu.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class AlphabetWrapper<T> implements Serializable {
    private static final long serialVersionUID = -8355365892694012570L;

    private String firstLetter;
    private List<T> items;
}
