package com.example.aopexample.plugins.result;

import java.io.Serializable;
import java.util.List;

/**
 * @author youxuehu
 * @version v1.0
 * @className PluginsConfigResult
 * @date 2022/1/3 9:09 下午
 * @desrription 这是类的描述信息
 */
public class PluginsConfigResult implements Serializable {

    private List<PluginsConfig> configs;
    private String name;

    public PluginsConfigResult() {
    }

    public PluginsConfigResult(List<PluginsConfig> configs, String name) {
        this.configs = configs;
        this.name = name;
    }

    public List<PluginsConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<PluginsConfig> configs) {
        this.configs = configs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PluginsConfigResult{" +
                "configs=" + configs +
                ", name='" + name + '\'' +
                '}';
    }
}
