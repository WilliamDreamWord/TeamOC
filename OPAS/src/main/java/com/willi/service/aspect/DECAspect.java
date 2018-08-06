package com.willi.service.aspect;

import com.willi.entity.UserBaseInformationEntity;
import com.willi.util.DECUtil;
import com.willi.util.impl.DECUitlImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @Autor William
 * @Date 2018/5/19
 * @Aspect 当前类为切面类
 */

@Component   //加入ioc容器
@Aspect   //指定当前类为切面类
public class DECAspect {

    DECUtil decUtil = new DECUitlImpl();

    //补齐用户密码(16位)，以满足DEC加密需要
    public String fixPwd(String userPwd) {

        if (userPwd.length() < 16) {
            for (int i = 0; i < 16 - userPwd.length(); i++) {
                userPwd += "a";
            }
            return userPwd;
        }
        return userPwd;
    }

    //声明切入点,注册方法
    @Pointcut(value = "execution(* com.willi.repository.impl.UserRepositoryImpl.add(..))")
    public void register() {
    }

    //切入点方法前对加密用户密码
    @Before(value = "register() && args(user)")
    public void eneryptPwd(UserBaseInformationEntity user) {

        user.setUserPwd(decUtil.encrypt(fixPwd(user.getUserPwd())));
    }

    @Pointcut(value = "execution(* com.willi.repository.impl.UserRepositoryImpl.updateByuserName(..))")
    public void updateByname() {
    }

    @Pointcut(value = "execution(* com.willi.repository.impl.UserRepositoryImpl.findUser(..))")
    public void login() {
    }

    //对更新/登录方法统一加密用户密码
    @Around(value = "updateByname() || login()")
    public Object eneryptPwd(ProceedingJoinPoint jpt) throws Throwable {

        //访问目标方法的参数
        Object[] args = jpt.getArgs();

        //用改变后的参数执行目标方法
        if (args != null && args.length > 0 && args[1].getClass() == String.class) {
            args[1] = decUtil.encrypt(fixPwd(args[1].toString()));
            System.out.println(args[1]);
        }

        Object retrunValue = jpt.proceed(args);

        return retrunValue;
    }

}
