package github.zayn.vm;

import java.util.ArrayList;

import com.google.common.collect.Lists;

/**
 * @ClassName Test
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/5/25 下午2:49
 **/
public class Test {
    @SuppressWarnings("checkstyle:MagicNumber")
    public static void main(String[] args) {
        ArrayList<Integer> integers = Lists.newArrayList(259823569, 259606698, 260002045, 262430830, 262678121, 256547195,
                256548471, 251970368, 259253347);
        for (Integer i : integers) {
            long shard = i.hashCode() % 2;
        }
    }


}

