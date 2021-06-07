package github.zayn.vm;

import java.util.ArrayList;
import java.util.List;

import github.zayn.reflect.Age;

/**
 * @ClassName Test
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/5/25 下午2:49
 **/
public class Test {
    @SuppressWarnings("checkstyle:MagicNumber")
    public static void main(String[] args) {
        Age age = new Age();
        List<Age> list1 = new ArrayList<>();
        List<Age> list2 = new ArrayList<>();
        List<Age> list3 = new ArrayList<>();
        list3.add(age);
        list1.add(list3.get(0));
        list2.add(list3.get(0));
        age.setAge(12);
        System.out.println(list1);
    }


}

