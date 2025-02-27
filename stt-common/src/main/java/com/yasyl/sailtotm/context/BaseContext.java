package com.yasyl.sailtotm.context;

public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static ThreadLocal<Long> threadLocalStuffRole = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

    public static void setCurrentStuffRole(Long role){threadLocalStuffRole.set(role);}

    public static Long getCurrentStuffRole() {
        return threadLocalStuffRole.get();
    }

    public static void removeCurrentStuffRole() {
        threadLocalStuffRole.remove();
    }
}
