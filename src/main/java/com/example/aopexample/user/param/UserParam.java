package com.example.aopexample.user.param;


import java.io.Serializable;

/**
 * @author youxuehu
 * @version v1.0
 * @className UserParam
 * @date 2022/12/31 下午3:01
 * @desrription 这是类的描述信息
 */
public class UserParam implements Serializable {

    private Long id;
    private String name;
    private String innMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInnMap() {
        return innMap;
    }

    public void setInnMap(String innMap) {
        this.innMap = innMap;
    }
}
