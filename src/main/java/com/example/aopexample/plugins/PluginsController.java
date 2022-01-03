package com.example.aopexample.plugins;

import com.example.aopexample.plugins.result.PluginsConfig;
import com.example.aopexample.service.SpringAopPluginsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author youxuehu
 * @version v1.0
 * @className PluginsController
 * @date 2022/1/3 9:05 下午
 * @desrription 这是类的描述信息
 */

@Controller
public class PluginsController {

    @Autowired
    SpringAopPluginsService springAopPluginsService;

    /**
     * http://localhost:8080/plugins/list
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/plugins/list")
    public String list(HttpServletRequest request, HttpServletResponse response) {
        List<PluginsConfig> pluginsConfigs = springAopPluginsService.list();
        request.setAttribute("list", pluginsConfigs);
        return "plugins";
    }

    /**
     * http://localhost:8080/plugins/active
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/plugins/active")
    @ResponseBody
    public String active(HttpServletRequest request, HttpServletResponse response, String id) {
        springAopPluginsService.active(id);
        return "Active success";
    }

    /**
     * http://localhost:8080/plugins/disabled
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/plugins/disabled")
    @ResponseBody
    public String disabled(HttpServletRequest request, HttpServletResponse response, String id) {
        springAopPluginsService.disabled(id);
        return "Disabled success";
    }

    /**
     * http://localhost:8080/plugins/loader
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/plugins/loader")
    @ResponseBody
    public URL[] loader(HttpServletRequest request, HttpServletResponse response) {
        URL[] urls = springAopPluginsService.loader();
        return urls;
    }

    /**
     * http://localhost:8080/plugins/config
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/plugins/config")
    @ResponseBody
    public Map<String, PluginsConfig> config(HttpServletRequest request, HttpServletResponse response) {
        return springAopPluginsService.pluginsConfigMap();
    }
}
