package github.zayn.designpattern.chainpattern;

import java.util.Set;

import lombok.Data;

/**
 * @ClassName InputDTO
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 3:16 下午
 **/
@Data
public class InputDTO {
    private long count;
    private Set<String> promotionTypes;
}
