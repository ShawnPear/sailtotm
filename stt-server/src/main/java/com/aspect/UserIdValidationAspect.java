package com.aspect;

import com.annotation.CheckUserId;
import com.context.BaseContext;
import com.enumeration.UserIdIntoType;
import com.exception.user.IdNotSameException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.constant.MessageConstant.OPE_ID_NOT_SAME_ERROR;
import static com.enumeration.UserIdIntoType.CLASS;
import static com.enumeration.UserIdIntoType.STRING;

@Aspect
@Component
@Slf4j
public class UserIdValidationAspect {

    public UserIdValidationAspect() {
        System.out.println("UserIdValidationAspect");
    }

    @Pointcut("@annotation(com.annotation.CheckUserId)") // 定义切点，拦截带有 @CheckUserId 注解的方法
    public void checkUserIdAnnotation() {
    }

    @Around("checkUserIdAnnotation()")
    public Object checkId(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取到当前被拦截的方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法签名对象
        CheckUserId autoFill = signature.getMethod().getAnnotation(CheckUserId.class);//获得方法上的注解对象
        UserIdIntoType operationType = autoFill.value();//获得数据库操作类型

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < parameterNames.length; i++) {
            String paraName = parameterNames[i];
            Object val = args[i];
            if (operationType == STRING && (paraName.equals("userId") || paraName.equals("user_id"))) {
                String userId = (String) val;
                if (!isValidUserId((String) userId)) {
                    throw new IdNotSameException(OPE_ID_NOT_SAME_ERROR);
                }
            } else if (operationType == CLASS && (paraName.endsWith("dto") || paraName.endsWith("Dto"))) {
                if (val != null) {
                    Class<?> clazz = val.getClass();
                    try {
                        Method getUserIdMethod = clazz.getMethod("getUserId");
                        Object userId = getUserIdMethod.invoke(val);
                        if (!isValidUserId((String) userId)) {
                            throw new IdNotSameException(OPE_ID_NOT_SAME_ERROR);
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
                    }
                }
            }
        }
        return joinPoint.proceed();
    }

    private boolean isValidUserId(String userId) {
        return BaseContext.getCurrentId().equals(Long.valueOf(userId));
    }
}