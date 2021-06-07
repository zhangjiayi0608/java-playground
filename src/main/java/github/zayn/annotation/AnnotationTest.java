package github.zayn.annotation;

import java.lang.reflect.Field;

/**
 * @ClassName AnnotationTest
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/5/30 11:41 上午
 **/
public class AnnotationTest {
    public static void main(String[] args) {
        ProductInfo productInfo = new ProductInfo();
        Field[] declaredFields = productInfo.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            TestAnnotation annotation = field.getAnnotation(TestAnnotation.class);
            if (annotation != null) {
                System.out.println(annotation.isPlus());
            }
        }
    }
}
