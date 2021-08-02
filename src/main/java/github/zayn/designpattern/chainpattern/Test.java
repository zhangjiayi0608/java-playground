package github.zayn.designpattern.chainpattern;

/**
 * @ClassName Test
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 4:00 下午
 **/
public class Test {

    public static void main(String[] args) {
        ChainEngine chainEngine = new ChainEngine();
        InputDTO inputDTO = new InputDTO();
        inputDTO.setCount(100);
        long l = chainEngine.execute(inputDTO);
        System.out.println(l);
    }
}
