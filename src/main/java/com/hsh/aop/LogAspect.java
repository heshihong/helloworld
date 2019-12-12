package com.hsh.aop;

import com.hsh.aop.entity.RequestLogPO;
import com.hsh.aop.mapper.RequestLogMapper;
import com.hsh.utils.CommonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class LogAspect {



    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //  ThreadLocal是什么
    //	早在JDK 1.2的版本中就提供Java.lang.ThreadLocal，ThreadLocal为解决多线程程序的并发问题提供了一种新的思路。使用这个工具类可以很简洁地编写出优美的多线程程序。
    //	当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
    //	从线程的角度看，目标变量就象是线程的本地变量，这也是类名中“Local”所要表达的意思。
    //	所以，在Java中编写线程局部变量的代码相对来说要笨拙一些，因此造成线程局部变量没有在Java开发者中得到很好的普及。
    //	ThreadLocal的接口方法
    //	ThreadLocal类接口很简单，只有4个方法，我们先来了解一下：
    //	void set(Object value)设置当前线程的线程局部变量的值。
    //	public Object get()该方法返回当前线程所对应的线程局部变量。
    //	public void remove()将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK 5.0新增的方法。需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。
    //	protected Object initialValue()返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的。这个方法是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。
    //	值得一提的是，在JDK5.0中，ThreadLocal已经支持泛型，该类的类名已经变为ThreadLocal<T>。API方法也相应进行了调整，新版本的API方法分别是void set(T value)、T get()以及T initialValue()。
    ThreadLocal<RequestLogPO> logPOThreadLocal = new ThreadLocal<RequestLogPO>();
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    /**
     * 切点
     */
    @Pointcut("execution(public * com.hsh..controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        logger.info("前置通知");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        RequestLogPO requestLogPO = new RequestLogPO();
        startTime.set(System.currentTimeMillis());
        requestLogPO.setCallTime(CommonUtil.getCurrentDate());
        requestLogPO.setUrl(request.getRequestURI());
        requestLogPO.setType(request.getMethod());
        requestLogPO.setMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        requestLogPO.setRemoteIP(request.getRemoteAddr());
        logPOThreadLocal.set(requestLogPO);
//        int res = requestLogMapper.insertRequstLog(requestLogPO);
//        if (res <= 0) {
//            logger.error("插入接口日志失败！");
//        }
    }

    /**
     * 后置返回通知
     * @param joinPoint
     * @param returnData
     */
    @AfterReturning(value = "webLog()", returning = "returnData")
    public void doAfterReturning(JoinPoint joinPoint, Object returnData) {
        // 处理完请求，返回内容
        logger.info("返回通知");
        // 获取响应体
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();

        // 请求错误码
        int status = response.getStatus();
        // 获取本次请求的日志实体
        RequestLogPO requestLogPO = logPOThreadLocal.get();
        // 设置响应结果
        requestLogPO.setStatus(status);
        // 设置响应时间
        requestLogPO.setRespTime(System.currentTimeMillis() - startTime.get());
        logger.info(requestLogPO.toString());
    }

    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *      对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "webLog()", throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        //目标方法名：
        logger.info("异常通知：" + exception.toString());
    }
}
