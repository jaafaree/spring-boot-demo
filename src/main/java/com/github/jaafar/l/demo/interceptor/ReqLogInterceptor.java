package com.github.jaafar.l.demo.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.demo.biz.ReqLogBiz;
import com.github.jaafar.l.demo.entity.ReqLog;
import com.github.jaafar.l.demo.utils.ReqLogUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求日志记录
 *
 * @author jaafaree
 * @create 2018/1/2 14:32
 */
public class ReqLogInterceptor extends HandlerInterceptorAdapter {
    @Value("${authorize.token-header}")
    private String hearder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        ReqLog reqLog = new ReqLog();
        reqLog.setUri(request.getRequestURI());
        String paramData = JSON.toJSONString(request.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
        reqLog.setParamData(paramData);
        reqLog.setMethod(request.getMethod());
        reqLog.setSessionId(request.getHeader(hearder));
        reqLog.setClientIp(ReqLogUtils.getCliectIp(request));
        reqLog.setType(ReqLogUtils.getRequestType(request));
        reqLog.setTime(System.currentTimeMillis() + "");
        //request.setAttribute(CommonConstants.LOG_SEND_TIME, System.currentTimeMillis());
        request.setAttribute(CommonConstants.LOG_SEND_ENTITY, reqLog);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //获取请求错误码
        int status = response.getStatus();
        //当前时间
        long currentTime = System.currentTimeMillis();
        ReqLog reqLog = (ReqLog) request.getAttribute(CommonConstants.LOG_SEND_ENTITY);
        long time = Long.valueOf(reqLog.getTime());
        reqLog.setTimeConsuming(Integer.valueOf(currentTime - time + ""));
        //设置返回时间
        reqLog.setReturnTime(currentTime + "");
        //设置返回错误码
        reqLog.setStatusCode(status+"");
        //设置返回值
//        reqLog.setReturnData(JSON.toJSONString(response.getOutputStream(),
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue));

        ReqLogBiz reqLogBiz = getBiz(ReqLogBiz.class, request);
        reqLogBiz.insert(reqLog);
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 获取bean公共方法
     * 解释：interceptor拦截器中无法通过SpringBean的方式注入reqLogBiz，我只能通过另外一种形式。
     * WebApplicationContextUtils
     * 这个工具类可以通过HttpServletRequest请求对象的上下文（ServletCotext）获取Spring管理的Bean
     * @param clazz
     * @param request
     * @param <T>
     * @return
     */
    private <T> T getBiz(Class<T> clazz,HttpServletRequest request) {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }
}
