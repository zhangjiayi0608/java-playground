package github.zayn.others;

import java.util.List;

import lombok.Data;

/**
 * @ClassName Test
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/5/25 下午2:49
 **/
@Data
public class DfsModel {
    private Integer id;
    private String desc;
    private List<DfsModel> dfsModels;

}

