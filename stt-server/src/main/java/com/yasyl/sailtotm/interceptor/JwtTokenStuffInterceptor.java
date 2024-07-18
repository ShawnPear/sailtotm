package com.yasyl.sailtotm.interceptor;

import com.yasyl.sailtotm.constant.JwtClaimsConstant;
import com.yasyl.sailtotm.context.BaseContext;
import com.yasyl.sailtotm.enumeration.RoleType;
import com.yasyl.sailtotm.exception.user.MissingTokenException;
import com.yasyl.sailtotm.properties.JwtProperties;
import com.yasyl.sailtotm.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.yasyl.sailtotm.constant.MessageConstant.MISS_OR_ERROR_OF_TOKEN;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenStuffInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.ADMIN_ID).toString());
            Long roleId = Long.valueOf(claims.get(JwtClaimsConstant.ADMIN_ROLE_ID).toString());
            BaseContext.setCurrentId(empId);
            BaseContext.setCurrentStuffRole(roleId);
            log.info("当前员工id：{}", empId);
            log.info("当前员工的角色:{}", roleId);
            //3、通过，放行
            return roleId != RoleType.DISABLE;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            throw new MissingTokenException(MISS_OR_ERROR_OF_TOKEN);
        }
    }

    /**
     * 防止本地线程内存泄漏
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.removeCurrentId();
        if (BaseContext.getCurrentStuffRole() != null)
            BaseContext.removeCurrentStuffRole();
    }
}
