package github.zayn.advisor;

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @ClassName Main
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2023/9/19 11:36 AM
 **/
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        String aspectName = "adCrm.SyncParamToEsAspectj"; // 指定切面的名称
        Class<?> aspectClass = advisor.getClass().getClassLoader().loadClass(aspectName);
        Class<?> clazz = advisor.getClass();
        ProtectionDomain protectionDomain = clazz.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URL location = codeSource.getLocation();
        String jarPath = location.getPath();
        System.out.println("Class is located in the jar: " + jarPath);
    }
}
