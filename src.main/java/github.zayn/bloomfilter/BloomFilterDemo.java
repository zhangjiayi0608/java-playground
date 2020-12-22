package github.zayn.bloomfilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @ClassName BloomFilter
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/22 下午3:12
 **/
public class BloomFilterDemo {
    private static final int TOTAL_NUM = 1000000;
    private static final double FPP = 0.0003;

    public static void main(String[] args) {
        BloomFilter<CharSequence> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), TOTAL_NUM, FPP);
        //初始化布隆过滤器
        for (int i = 0; i < TOTAL_NUM; i++) {
            bf.put(String.valueOf(i));
        }
        int count = 0;
        for (int i = 0; i < TOTAL_NUM + TOTAL_NUM; i++) {
            if (bf.mightContain(String.valueOf(i))) {
                count++;
            }
        }
        System.out.println("已匹配数量：" + count);

    }
}
