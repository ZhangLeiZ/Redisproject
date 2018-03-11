package com.zl.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/4.
 */
public class Redis implements Serializable {
    private static final long serialVersionUID = 5878309872428133662L;
    private Integer id;
    private String name;

    public Redis(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
