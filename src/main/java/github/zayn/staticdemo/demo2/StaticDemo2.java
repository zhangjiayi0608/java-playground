package github.zayn.staticdemo.demo2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * @EnumName EntranceMachineState
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/16 下午8:37
 **/
public class StaticDemo2 {

    public static void main(String[] args) throws Exception {
//        BaseThree baseThree = new BaseThree();
//        System.out.println("-----");
//        BaseThree baseThree2 = new BaseThree();

        List<LittleDemo> reqList = Lists.newArrayList();
        List<Long> littleList = new ArrayList<>();
        littleList.add(1L);
        littleList.add(2L);


        LittleDemo demo2 = new LittleDemo();
        demo2.setRank(2);
        reqList.add(demo2);

        LittleDemo demo3 = new LittleDemo();
        demo3.setRank(3);
        demo3.setLittleList(littleList);
        reqList.add(demo3);

        LittleDemo demo1 = new LittleDemo();
        demo1.setRank(1);
        demo1.setLittleList(littleList);
        reqList.add(demo1);

        final LittleDemo demo = reqList.get(0);
        demo.setRank(4);


        System.out.println(reqList);
        Comparator<LittleDemo> comparator1 = Comparator.comparing(x -> x.getLittleList() != null);
        comparator1 = comparator1.thenComparing(x -> x.getRank());
        List<LittleDemo> resList = reqList.stream().sorted(comparator1).collect(Collectors.toList());
        System.out.println(resList);

    }
}
