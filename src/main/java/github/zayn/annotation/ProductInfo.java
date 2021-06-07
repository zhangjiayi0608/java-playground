package github.zayn.annotation;

import lombok.Data;

/**
 * @ClassName AnnotationTest
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/5/30 11:36 上午
 **/
@Data
public class ProductInfo {
    private int id;

    @TestAnnotation
    private int price;
    private String name;

}
