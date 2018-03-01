package com.shang.upms.config;

import com.shang.app.apiVersion.ApiVersionInterceptor;
import com.shang.upms.support.ReaderHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

  /**
   * 路径适配
   * @param configurer
   */
  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    configurer.setUseSuffixPatternMatch(false).
            setUseTrailingSlashMatch(true);
  }

  /**
   * 拦截器
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor( new ApiVersionInterceptor() );
    super.addInterceptors(registry);
  }

  /**
   * 视图控制器
   * @param registry
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
    registry.addViewController( "/" ).setViewName( "forward:/index.html" );
   // registry.addViewController("/async.html").setViewName("async");

    registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
  }


  /**
   * 自定义解析器实现请求参数绑定方法参数
   * @param argumentResolvers
   */
  @Override
  public void addArgumentResolvers(
      List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(new ReaderHandlerMethodArgumentResolver());
  }

  /**
   * 增加跨域访问，全局配置
   * @param registry
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api").allowedOrigins("http://localhost:9000");
  }

  @Override
  public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    configurer.setDefaultTimeout(30*1000L); //tomcat默认10秒
    configurer.setTaskExecutor(mvcTaskExecutor());//所借助的TaskExecutor
  }

  @Bean
  public ThreadPoolTaskExecutor mvcTaskExecutor(){
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setQueueCapacity(100);
    executor.setMaxPoolSize(25);
    return executor;

  }


}
