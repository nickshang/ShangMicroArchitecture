package com.shang.app.apiVersion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author NICK
 * @create 2017-12-25
 **/
@Component
public class ApiVersionInterceptor extends HandlerInterceptorAdapter {
    /**
     * 接口版本参数名
     */
    final String API_VERSION = "av";
    final String STRING_DEFAULT = "";
    final Integer INTEGER_DEFAULT = 0;
    final Long LONG_DEFAULT = 0L;
    final Boolean BOOLEAN_DEFAULT = false;

    @Autowired
    private ApplicationContext context;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        ApiVersion apiVersion = method.getMethodAnnotation(ApiVersion.class);
        //判断是否纳入接口版本控制
        if (apiVersion == null) {
            return true;
        }

        Class cls = apiVersion.targetClass();
        Object o;
        try {
            o = context.getBean(cls);
        } catch (Exception e) {
            throw new ApiVersionException("指定的处理类必须纳入spring的bean管理", e);
        }
        String preName = apiVersion.methodPreName();
        if (preName == null || preName.trim().isEmpty()) {
            preName = method.getMethod().getName();
        }

        //接口版本号
        String av = "1";
        //参数列表
        Map<String, String[]> requestParam = request.getParameterMap();
        if (requestParam.get(API_VERSION) != null) {
            av = requestParam.get(API_VERSION)[0];
        }
        Method[] methods = cls.getMethods();
        if (methods == null) {
            writeMsg(response, "未找到响应方法");
            return false;
        }
        Method targetMethod = null;
        //找到指定的处理方法
        for (Method me : methods) {
            if (me.getName().equals(preName + av)) {
                targetMethod = me;
                break;
            }
        }
        if (targetMethod == null) {
            writeMsg(response, "非法请求");
            return false;
        }
        if (!targetMethod.getReturnType().equals(String.class)) {
            throw new ApiVersionException("响应方法返回类型必须为String :" + targetMethod.getName());
        }
        //获得方法的参数
        Class<?>[] paramTypes = targetMethod.getParameterTypes();
        Integer paramLength = paramTypes.length;

        //调动方法的参数
        Object[] paramList = new Object[paramLength];
        Annotation[][] annotationss = targetMethod.getParameterAnnotations();
        //总注解参数个数
        for (int i = 0; i < annotationss.length; i++) {
            Annotation[] annotations = annotationss[i];
            if (annotations.length < 1)
                throw new ApiVersionException("存在未添加@ApiParam注解参数的方法 :" + targetMethod.getName());
            //是否存在ApiParam注解
            boolean hasAnn = false;
            for (int j = 0; j < annotations.length; j++) {
                Annotation annotation = annotations[j];
                if (annotation instanceof ApiParam) {
                    //为参数赋值
                    paramList[i] = getParam(requestParam, (ApiParam) annotation, paramTypes[i]);
                    hasAnn = true;
                    break;
                }
            }
            if (!hasAnn)
                throw new ApiVersionException("存在未添加@ApiParam注解参数的方法 :" + targetMethod.getName());
        }

        //反射方法调用
        String result = (String) targetMethod.invoke(o, paramList);
        writeMsg(response, result);
        return false;
    }

    /**
     * 输出内容
     *
     * @param response
     * @param msg
     * @throws Exception
     */
    private void writeMsg(HttpServletResponse response, String msg) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(msg);
        out.flush();
        out.close();
    }

    /**
     * 获得参数的值
     *
     * @param requestParam 请求参数
     * @param apiParam     参数上注解
     * @param paramType    参数类型
     * @return 参数值
     */
    private Object getParam(Map<String, String[]> requestParam, ApiParam apiParam, Class<?> paramType) {
        String reqName = apiParam.value();
        //如果有该参数
        if (requestParam.get(reqName) != null) {
            Object o = requestParam.get(reqName)[0];
            try {
                if (paramType.equals(String.class)) {
                    return String.valueOf(o);
                }
                if (paramType.equals(Boolean.class) || "boolean".equals(paramType.getName())) {
                    return Boolean.parseBoolean(o.toString());
                }
                if (paramType.equals(Integer.class) || "int".equals(paramType.getName())) {
                    return Integer.parseInt(o.toString());
                }
                if (paramType.equals(Long.class) || "long".equals(paramType.getName())) {
                    return Long.parseLong(o.toString());
                }
                if (paramType.equals(BigDecimal.class)) {
                    return new BigDecimal(o.toString());
                }
            } catch (Exception e) {
                throw new ApiVersionException("参数格式化失败 :" + reqName, e);
            }
            return o;
        }
        //如果参数必须
        if (apiParam.required()) {
            throw new ApiVersionException("缺少参数 :" + reqName);
        }
        //返回默认值
        DefaultValueEnum dfe = apiParam.defaultValue();
        if (DefaultValueEnum.DEFAULT.equals(dfe)) {
            if (paramType.equals(String.class)) {
                return STRING_DEFAULT;
            }
            if (paramType.equals(Boolean.class) || "boolean".equals(paramType.getName())) {
                return BOOLEAN_DEFAULT;
            }
            if (paramType.equals(Integer.class) || "int".equals(paramType.getName())) {
                return INTEGER_DEFAULT;
            }
            if (paramType.equals(Long.class) || "long".equals(paramType.getName())) {
                return LONG_DEFAULT;
            }
        } else if (DefaultValueEnum.NULL.equals(dfe)) {
            return null;
        } else if (DefaultValueEnum.STRING_EMPTY.equals(dfe)) {
            return "";
        } else if (DefaultValueEnum.ZERO.equals(dfe)) {
            return 0;
        } else if (DefaultValueEnum.FALSE.equals(dfe)) {
            return false;
        } else if (DefaultValueEnum.TRUE.equals(dfe)) {
            return true;
        }
        return null;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}