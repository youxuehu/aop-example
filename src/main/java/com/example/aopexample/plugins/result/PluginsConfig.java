package com.example.aopexample.plugins.result;

import java.io.Serializable;

/**
 * @author youxuehu
 * @version v1.0
 * @className PluginsConfig
 * @date 2022/1/3 9:07 下午
 * @desrription 这是类的描述信息
 */
public class PluginsConfig implements Serializable {

    private String id;
    private String name;
    private String className;
    private String jarRemoteUrl;
    private Boolean active;

    public PluginsConfig() {
    }

    public PluginsConfig(String id, String name, String className, String jarRemoteUrl, Boolean active) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.jarRemoteUrl = jarRemoteUrl;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJarRemoteUrl() {
        return jarRemoteUrl;
    }

    public void setJarRemoteUrl(String jarRemoteUrl) {
        this.jarRemoteUrl = jarRemoteUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "PluginsConfig{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", jarRemoteUrl='" + jarRemoteUrl + '\'' +
                ", active=" + active +
                '}';
    }
}
