package com.yasyl.sailtotm.aspect;

import com.yasyl.sailtotm.context.BaseContext;
import com.yasyl.sailtotm.enumeration.RoleType;
import com.yasyl.sailtotm.exception.admin.AccessRestrictionException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static com.yasyl.sailtotm.constant.MessageConstant.ACCESS_RESTRICT;

@Aspect
@Component
@Slf4j
public class AdminLicenceAspect {
    public AdminLicenceAspect() {

    }

    @Pointcut("@annotation(com.yasyl.sailtotm.annotation.AdminLicence)") // 定义切点，拦截带有 @CheckUserId 注解的方法
    public void adminLicenceAnnotation() {
    }

    
    @Before("adminLicenceAnnotation()")
    public void preCheck() {
        if (BaseContext.getCurrentStuffRole() != RoleType.ADMIN) {
            throw new AccessRestrictionException(ACCESS_RESTRICT);
        }
    }

}
