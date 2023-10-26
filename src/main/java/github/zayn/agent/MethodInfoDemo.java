package github.zayn.agent;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import ch.qos.logback.classic.spi.CallerData;

/**
 * @ClassName MethodInfoDemo
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2023/10/17 7:02 PM
 **/
public class MethodInfoDemo {
    public static void main(String[] args) {
        try {
            // 获取类信息
            Class<?> clazz = CallerData.class;
//            System.out.println("类名: " + clazz.getName());
//            System.out.println("包名: " + clazz.getPackage().getName());
//            System.out.println("修饰符: " + Modifier.toString(clazz.getModifiers()));
            // 获取方法信息
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                Class<?> declaringClass = method.getDeclaringClass();
                System.out.println("类名: " + declaringClass.getName());
                System.out.println("包名: " + declaringClass.getPackage().getName());
                System.out.println("修饰符: " + Modifier.toString(declaringClass.getModifiers()));
                System.out.println("方法名: " + method.getName());
                System.out.println("修饰符: " + Modifier.toString(method.getModifiers()));
                System.out.println("返回类型: " + method.getReturnType().getName());

                // 获取参数信息
                Class<?>[] parameterTypes = method.getParameterTypes();
                System.out.print("参数类型: ");
                for (Class<?> parameterType : parameterTypes) {
                    System.out.print(parameterType.getName() + " ");
                }
                System.out.println();
            }

            // 获取JAR信息
            ProtectionDomain protectionDomain = clazz.getProtectionDomain();
            CodeSource codeSource = protectionDomain.getCodeSource();
            URL jarUrl = codeSource.getLocation();
            System.out.println("JAR路径: " + jarUrl);

            // 获取JAR中的其他类信息
            URLClassLoader classLoader = (URLClassLoader) clazz.getClassLoader();
            URL[] urls = classLoader.getURLs();
            System.out.println("JAR中的其他类信息:");
            for (URL url : urls) {
                System.out.println(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
