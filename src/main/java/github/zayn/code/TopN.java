package github.zayn.code;

import java.util.Random;

/**
 * @ClassName HeapSort
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2022/3/29 3:33 下午
 **/
public class TopN {
    private static final int N = 100;           //Top10
    private static final int MAX_COUNT = 100000000; //1亿个整数
    private static final int[] SOURCE = new int[MAX_COUNT];
    private static int[] arr = new int[N];
    //数组长度
    private static final int LEN = arr.length;
    //堆中元素的有效元素 heapSize<=len
    private static final int HEAP_SIZE = LEN;

    @SuppressWarnings("checkstyle:MagicNumber")
    public static void main(String[] args) {
        //生成随机数组
        for (int i = 0; i < MAX_COUNT; i++) {
            SOURCE[i] = new Random().nextInt(999999999);
        }

        //构建初始堆
        for (int i = 0; i < N; i++) {
            arr[i] = SOURCE[i];
        }
        //构建小顶堆
        long start = System.currentTimeMillis();
        buildMinHeap();
        for (int i = N; i < MAX_COUNT; i++) {
            if (SOURCE[i] > arr[0]) {
                arr[0] = SOURCE[i];
                minHeap(0);
            }
        }
        System.out.println(MAX_COUNT + "个数，求Top" + N + "，耗时" + (System.currentTimeMillis() - start) + "毫秒");
        print();
    }


    /**
     * 自底向上构建小堆
     */
    public static void buildMinHeap() {
        int size = LEN / 2;
        for (int i = size; i >= 0; i--) {
            minHeap(i);
        }
    }

    /**
     * i节点为根及子树是一个小堆
     * @param i
     */
    public static void minHeap(int i) {
        int l = left(i);
        int r = right(i);
        int index = i;
        if (l < HEAP_SIZE && arr[l] < arr[index]) {
            index = l;
        }
        if (r < HEAP_SIZE && arr[r] < arr[index]) {
            index = r;
        }
        if (index != i) {
            swap(arr[index], arr[i]);
            //递归向下构建堆
            minHeap(index);
        }
    }

    /**
     * 返回i节点的左孩子
     * @param
     * @return
     */
    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    /**
     * 返回i节点的左孩子
     * @param i
     * @return
     */
    public static int left(int i) {
        return 2 * i;
    }

    /**
     * 返回i节点的右孩子
     * @param i
     * @return
     */
    public static int right(int i) {
        return 2 * i + 1;
    }

    /**
     * top n排序
     */
    public static void sort() {

    }

    /**
     * 打印
     */
    public static void print() {
        for (int a : arr) {
            System.out.print(a + ",");
        }
        System.out.println();
    }
}
