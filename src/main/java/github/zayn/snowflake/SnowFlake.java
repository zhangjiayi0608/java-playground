package github.zayn.snowflake;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SnowFlake {
    /**
     * 开始时间截 (1970-01-01)
     */
    private final long twepoch = 0L;
    /**
     * 机器id，范围是1到15
     */
    private final long workerId;
    /**
     * 机器id所占的位数，占4位
     */
    private final long workerIdBits = 4L;
    /**
     * 支持的最大机器id，结果是15
     */
    private final long maxWorkerId = ~(-1L << workerIdBits);
    /**
     * 生成序列占的位数
     */
    private final long sequenceBits = 15L;
    /**
     * 机器ID向左移15位
     */
    private final long workerIdShift = sequenceBits;
    /**
     * 生成序列的掩码，这里为最大是32767 (111111111111111=32767)
     */
    private final long sequenceMask = ~(-1L << sequenceBits);
    /**
     * 时间截向左移19位(4+15)
     */
    private final long timestampLeftShift = 19L;
    /**
     * 秒内序列(0~32767)
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    public SnowFlake(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        //蓝色代码注释开始
        long timestamp = timeGen();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //蓝色代码注释结束
        //如果是同一时间生成的，则进行秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个秒,获得新的秒值
                timestamp = tilNextMillis();
            }
            //时间戳改变，秒内序列重置
        } else {
            sequence = 0L;
        }
        //绿色代码注释结束
        //上次生成ID的时间截
        lastTimestamp = timestamp;
        //黄色代码注释开始
        //移位并通过或运算拼到一起组成53 位的ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (workerId << workerIdShift)
                | sequence;
        //黄色代码注释结束
    }

    /**
     * 阻塞到下一个秒，直到获得新的时间戳
     *
     * @return 当前时间戳
     */
    protected long tilNextMillis() {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以秒为单位的当前时间
     *
     * @return 当前时间(秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis() / 1000L;
    }

    public static void parseId(long id) {
        long second = id >>> 19;
        System.err.println("分布式id-" + id + "生成的时间是：" + new SimpleDateFormat("yyyy-MM-dd").format(new Date(second * 1000)));
    }

    public static void main(String[] args) {
        SnowFlake idWorker = new SnowFlake(0);
        for (int i = 0; i < 10; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
            parseId(id);
        }
    }
}

