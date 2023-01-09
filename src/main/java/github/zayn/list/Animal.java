package github.zayn.list;

import lombok.Data;

/**
 * @ClassName Test
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/5/25 下午2:49
 **/
@Data
public class Animal {
    private Integer id;
    private String desc;

    Animal(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}

