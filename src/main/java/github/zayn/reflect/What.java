package github.zayn.reflect;

import java.util.List;

import lombok.Data;

/**
 * @ClassName What
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/24 下午3:16
 **/
@Data
public class What extends Age {
    private Integer height;
    private Integer weight;
    private List<Long> zhangBig;

    private Integer expoPv30d;
}
