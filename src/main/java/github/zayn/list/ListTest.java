package github.zayn.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/5/25 下午2:49
 **/
public class ListTest {
    @SuppressWarnings("checkstyle:MagicNumber")
    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal(1, "猪"));
        animalList.add(new Animal(2, "狗"));
        animalList.add(new Animal(3, "鼠"));
        animalList.add(new Animal(4, "牛"));
        for (Animal animal : animalList) {
            if (animal.getId() == 1) {
                animal.setDesc("小猪");
            }
        }
        System.out.println(animalList);
    }


}

