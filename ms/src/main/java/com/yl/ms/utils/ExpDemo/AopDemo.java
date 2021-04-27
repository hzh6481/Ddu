package com.yl.ms.utils.ExpDemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopDemo {
    public final static Logger logger = LoggerFactory.getLogger(AopDemo.class);

    //声明切点
    @Pointcut("@annotation(com.yl.ms.utils.ExpDemo.AopDemoTe)")
    public void pointcut(){}

    //定义切面要执行的方法
    @Before("pointcut()")
    public Object BeforeInsertUser(JoinPoint pjp){
        try {
            logger.info("BeforeInsertUser执行==");
            //校验用户是否有权限执行相应操作
            String Method = pjp.getTarget().getClass().getName();//获取调用者的方法名
            logger.info("调用者为："+Method);
            if("insertUser".equals(Method)){
                Object[] args = pjp.getArgs();
                String ObjectCode = (String)args[0];//获取操作员的操作权限
                if(!"admin".equals(ObjectCode)){
                    throw new RuntimeException("该用户没有新增用户的权限");
                }
                return null;
            }
            return null;
        }catch (Throwable e){
            logger.error("切面执行异常",e);
            return null;
        }
    }
}
