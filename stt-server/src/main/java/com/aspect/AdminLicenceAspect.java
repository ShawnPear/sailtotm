package com.aspect;

import com.context.BaseContext;
import com.enumeration.RoleType;
import com.exception.admin.AccessRestrictionException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static com.constant.MessageConstant.ACCESS_RESTRICT;

@Aspect
@Component
@Slf4j
public class AdminLicenceAspect {
    public AdminLicenceAspect() {

    }

    @Pointcut("@annotation(com.annotation.AdminLicence)") // 定义切点，拦截带有 @CheckUserId 注解的方法
    public void adminLicenceAnnotation() {
    }

    
    @Before("adminLicenceAnnotation()")
    public void preCheck() {
        if (BaseContext.getCurrentStuffRole() != RoleType.ADMIN) {
            throw new AccessRestrictionException(ACCESS_RESTRICT);
        }
    }

}
