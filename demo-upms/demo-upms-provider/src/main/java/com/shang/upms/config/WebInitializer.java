package com.shang.upms.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 异步支持配置
 * @author NICK
 * @create 2018-01-10
 **/
public class WebInitializer  implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

            AnnotationConfigWebApplicationContext ctx =
                    new AnnotationConfigWebApplicationContext();
            // 注册WebMvcConfigurerAdapter
            ctx.register(WebConfiguration.class);

            // 注册spring mvc的DispatcherServlet
            ctx.setServletContext(servletContext);
            ServletRegistration.Dynamic servlet =
                    servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
            servlet.addMapping("/");
            servlet.setLoadOnStartup(1);
            servlet.setAsyncSupported(true);//此句开启

    }
}
