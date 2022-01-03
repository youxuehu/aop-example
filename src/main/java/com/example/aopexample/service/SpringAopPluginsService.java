package com.example.aopexample.service;

import com.alibaba.fastjson.JSONObject;
import com.example.aopexample.plugins.result.PluginsConfig;
import com.example.aopexample.plugins.result.PluginsConfigResult;
import org.aopalliance.aop.Advice;
import org.apache.commons.io.FileUtils;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author youxuehu
 * @version v1.0
 * @className SpringAopPluginsService
 * @date 2022/1/3 9:06 下午
 * @desrription 这是类的描述信息
 */
@Service
public class SpringAopPluginsService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final Map<String, PluginsConfig> pluginsConfigMap = new HashMap<>();
    private final Map<String, Advice> adviceMap = new HashMap<>();

    public List<PluginsConfig> list() {
        File configFile = new File("/Users/youxuehu/plugins/plugins.conf");
        String config = null;
        try {
            config = FileUtils.readFileToString(configFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (config == null) {
            return new ArrayList<>();
        }
        PluginsConfigResult pluginsConfigResult = JSONObject.parseObject(config, PluginsConfigResult.class);
        if (pluginsConfigResult == null) {
            return new ArrayList<>();
        }
        List<PluginsConfig> configs = pluginsConfigResult.getConfigs();
        for (PluginsConfig pluginsConfig : configs) {
            if (!pluginsConfigMap.containsKey(pluginsConfig.getId())) {
                pluginsConfigMap.put(pluginsConfig.getId(), pluginsConfig);
            }
            BeanUtils.copyProperties(pluginsConfigMap.get(pluginsConfig.getId()), pluginsConfig);
        }
        return CollectionUtils.isEmpty(configs) ? new ArrayList<>() : configs;
    }

    public void active(String id) {
        if (!pluginsConfigMap.containsKey(id)) {
            throw new RuntimeException(String.format("Plugins not found id is %s", id));
        }
        PluginsConfig pluginsConfig = pluginsConfigMap.get(id);
        pluginsConfig.setActive(true);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(name);
            if (bean == this) {
                continue;
            }
            if (!(bean instanceof Advised)) {
                continue;
            }
            if (findAdvice((Advised) bean, pluginsConfig.getClassName()) != null) {
                continue;
            }
            Advice advice;
            try {
                advice = builderAdvice(pluginsConfig);
                ((Advised) bean).addAdvice(advice);
            } catch (Exception e) {
                throw new RuntimeException("Active plugins Error", e);
            }
        }
        pluginsConfigMap.put(id, pluginsConfig);
    }

    public void disabled(String id) {
        if (!pluginsConfigMap.containsKey(id)) {
            throw new RuntimeException(String.format("Plugins not found id is %s", id));
        }
        PluginsConfig pluginsConfig = pluginsConfigMap.get(id);
        pluginsConfig.setActive(false);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(name);
            if (bean == this) {
                continue;
            }
            if (!(bean instanceof Advised)) {
                continue;
            }
            if (findAdvice((Advised) bean, pluginsConfig.getClassName()) == null) {
                continue;
            }
            Advice advice;
            try {
                advice = builderAdvice(pluginsConfig);
                ((Advised) bean).removeAdvice(advice);
            } catch (Exception e) {
                throw new RuntimeException("Disabled plugins Error", e);
            }
        }
        pluginsConfigMap.put(id, pluginsConfig);
    }

    private Advice builderAdvice(PluginsConfig pluginsConfig) throws Exception {
        if (adviceMap.containsKey(pluginsConfig.getClassName())) {
            return adviceMap.get(pluginsConfig.getClassName());
        }
        URL targetURL = new URL(pluginsConfig.getJarRemoteUrl());
        URLClassLoader loader = (URLClassLoader) getClass().getClassLoader();
        boolean isLoader = false;
        for (URL url : loader.getURLs()) {
            if (url.equals(targetURL)) {
                isLoader = true;
                break;
            }
        }
        if (!isLoader) {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] {URL.class});
            add.setAccessible(true);
            add.invoke(loader, targetURL);
        }
        Class<?> adviceClass = loader.loadClass(pluginsConfig.getClassName());
        adviceMap.put(adviceClass.getName(), (Advice) adviceClass.newInstance());
        return adviceMap.get(adviceClass.getName());
    }

    private Advice findAdvice(Advised advised, String className) {
        for (Advisor a : advised.getAdvisors()) {
            if (a.getAdvice().getClass().getName().equals(className)) {
                return a.getAdvice();
            }
        }
        return null;
    }

    public URL[] loader() {
        URLClassLoader loader = (URLClassLoader) getClass().getClassLoader();
        return loader.getURLs();
    }

    public List<String> bean() {
        List<String> beans = new ArrayList<>();
        for (String name : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(name);
            beans.add(bean.toString());
        }
        return beans;
    }

    public Map<String, PluginsConfig> pluginsConfigMap() {
        return pluginsConfigMap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
