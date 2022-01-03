package com.example.aopexample.plugins.install;

import com.alibaba.fastjson.JSON;
import com.example.aopexample.plugins.result.PluginsConfig;
import com.example.aopexample.plugins.result.PluginsConfigResult;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author youxuehu
 * @version v1.0
 * @className Install
 * @date 2022/1/3 9:42 下午
 * @desrription 这是类的描述信息
 */
public class Install {
    public static void main(String[] args) throws IOException {

        PluginsConfigResult pluginsConfigResult = new PluginsConfigResult(
                Arrays.asList(
                        new PluginsConfig("1",
                                "统计方法调用次数",
                                "com.example.CountTimesPlugins",
                                "file:/Users/youxuehu/plugins/aop-plugins-0.0.1-SNAPSHOT.jar",
                                false)
                ),
                "插件库"
        );


        FileUtils.writeStringToFile(
                new File("/Users/youxuehu/plugins/plugins.conf"),
                JSON.toJSONString(pluginsConfigResult, true),
                "UTF-8");
    }
}
