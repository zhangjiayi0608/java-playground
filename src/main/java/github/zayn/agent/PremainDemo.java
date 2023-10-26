package github.zayn.agent;

import java.lang.instrument.Instrumentation;

/**
 * @ClassName premain
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2023/9/6 11:42 AM
 **/
public class PremainDemo {
    public static void premain(String args, Instrumentation inst){
        for (int i=0;i<10;i++){
            System.out.println("hello I am premain agent!!!!");
        }
    }
}
