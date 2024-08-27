//package com.github.code.manage_web.aspect;
//
//import com.github.code.manage_common.resp.BaseApiResponse;
//import jakarta.annotation.Resource;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.builder.ToStringStyle;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Set;
//
///**
// * web日志实现类<br>
// *
// * @author cuipengfei
// * @date 2020-09-24 17:46
// */
//@Slf4j
//@Aspect
//@Component
//@Order(0)
//public class WebRequestAspect {
//
//    @Resource
//    private LogUtil logUtil;
//
//    private static final String ERROR_LOG_MSG = "Web Request Error, Ex: %s reason: %s";
//
//    /**
//     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果 '@Pointcut("execution(* com.bytedance.cg.openapi.thrift.aspect.annotation.*.*(..))")'
//     */
//    @Pointcut("@annotation(com.bytedance.cg.refund.web.aspect.annotation.WebRequest)")
//    public void WebRequest() {
//    }
//
//    @Around("WebRequest()")
//    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object response;
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        WebRequest webRequest = signature.getMethod().getAnnotation(WebRequest.class);
////        Set<String> sensitiveWords = StringUtil.splitByComma(webRequest.sensitiveWords());
//
//        Object[] args = joinPoint.getArgs();
//        Object request = null == args || args.length == 0 ? null : args[0];
//        String requestTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
//        String url = getCurrentRequestUrl();
//        long startTime = System.currentTimeMillis();
////        String reqLog = toDesensitizationString(request, sensitiveWords);
//        log.info("WEB API REQ URL: {} REQ param: {}", url, args);
//        try {
//            // 接口请求
//            response = joinPoint.proceed(args);
//
//            // 统一成功处理
//            long usedTime = System.currentTimeMillis() - startTime;
//            Object logObject = getResultLogObject(webRequest, response);
////            String resLog = toDesensitizationString(logObject, sensitiveWords);
//            log.info("WEB API RESP success, request time:{}, used time : {} ms, result: {}",
//                    requestTime, usedTime, response);
//        } catch (LoginException | ArgumentException | ConcurrentLockException | BusinessException e) {
//            response = buildFailResponse(e, url, reqLog);
//            logUtil.onlineLogError(false, buildSendErrorMsg(e), e);
//        } catch (RemoteBusinessException | RemoteException | ServiceException e) {
//            response = buildFailResponse(e, url, reqLog);
//            logUtil.onlineLogError(true, buildSendErrorMsg(e), e);
//        } catch (Exception e) {
//            response = buildFailResponse(new ServiceException(e), url, reqLog);
//            logUtil.onlineLogError(true, buildSendErrorMsg(e), e);
//        } finally {
//            ThreadLocalUtils.remove();
//            LoginUserContext.remove();
//            logUtil.removeSentryTagWithAdditional();
//        }
//        return response;
//    }
//
//    /**
//     * 构建需要打印的Sentry异常信息
//     * @param e 异常
//     * @return String
//     */
//    private String buildSendErrorMsg(Exception e) {
//        String exceptionType = e.getClass().getSimpleName();
//        String innerCauseMsg = e.getMessage();
//        if (e instanceof BaseRefundException) {
//            BaseRefundException refundException = (BaseRefundException) e;
//            innerCauseMsg = StringUtils.isBlank(refundException.getInnerCauseMsg())
//                    ? refundException.getDefaultErrorEnum().getCause()
//                    : refundException.getInnerCauseMsg();
//        }
//        return String.format(ERROR_LOG_MSG, exceptionType, innerCauseMsg);
//    }
//
//    /**
//     * 构建异常场景下返回前端的Response数据结构
//     *
//     * @param refundException 异常
//     * @param requestUrl      请求的URL
//     * @param reqParam        请求的参数
//     * @return BaseApiResponse
//     */
//    private BaseApiResponse<?> buildFailResponse(BaseRefundException refundException,
//            String requestUrl,
//            String reqParam) {
//        try {
//            logUtil.buildSentryTags(refundException, requestUrl);
//            logUtil.buildSentryAdditionalData(reqParam);
//            return BaseApiResponse.fail(refundException.getCode(), refundException.getMessage());
//        } catch (Exception ex) {
//            log.error("webRequestAspect build Web Api response fail", ex);
//            return null;
//        }
//    }
//
//    /**
//     * 获取Response对象日志
//     *
//     * @param webRequest 注解定义
//     * @param response   Response
//     * @return 返回结果对象
//     */
//    private Object getResultLogObject(WebRequest webRequest, Object response) {
//        try {
//            if (webRequest.isOutputRespBody()) {
//                return response;
//            } else {
//                return null;
//            }
//        } catch (Exception ex) {
//            log.error("WEB Request aspect get result log error", ex);
//            return "";
//        }
//    }
//
//    /**
//     * 获取当前请求的URL
//     *
//     * @return URL路径信息
//     */
//    private String getCurrentRequestUrl() {
//        try {
//            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//            ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
//            HttpServletRequest request = attributes.getRequest();
//            return request.getRequestURL().toString();
//        } catch (Exception e) {
//            log.error("WEB Request aspect get URL error", e);
//            return "";
//        }
//    }

//    /**
//     * 日志数据脱敏
//     *
//     * @param object         脱敏对象
//     * @param sensitiveWords 脱敏字段名称
//     * @return 脱敏后日志
//     */
//    private String toDesensitizationString(Object object, Set<String> sensitiveWords) {
//        if (null == object) {
//            return "";
//        }
//        try {
//            return DesensitizationToStringBuilder.toString(object, ToStringStyle.JSON_STYLE, sensitiveWords);
//        } catch (Exception e) {
//            log.error("to desensitization string error", e);
//            return object.toString();
//        }
//    }
//}
