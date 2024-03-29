package cn.com.jerry.utils.core;

import java.util.Date;

/**
 * @author ：Jerry
 * @date ：Created in 2019/9/3 10:56
 */
public class SnowflakeIdHelper {

    private static long workerId = 1;

    private static long datacenterId = 1;

    //构造方法设置机器码：第1个机房的第1台机器
    private static SnowflakeIdWorker idWorker = new SnowflakeIdWorker(workerId, datacenterId);

    private SnowflakeIdHelper() {
    }

    public static SnowflakeIdWorker getSnowflakeIdWorker() {
        return idWorker;
    }

    public static long generateId() {
        return idWorker.nextId();
    }

    public static String generateStrId() {
        return idWorker.nextId() + "";
    }

    public static long getTimestampValue(long id) {
        long twepoch = SnowflakeIdWorker.twepoch;
        long time = id >> 22;
        return time + twepoch;
    }

    public static Date getTimestamp(long id) {
        return new Date(getTimestampValue(id));
    }

    public static long getGenerateIdStart(Date date) {
        long sequence = 0L;
        long workerIdBits = 5L;
        long datacenterIdBits = 5L;
        long sequenceBits = 12L;

        long workerIdShift = sequenceBits;
        long datacenterIdShift = sequenceBits + workerIdBits;
        long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

        long twepoch = SnowflakeIdWorker.twepoch;
        long timestamp = date.getTime();
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    public static long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        SnowflakeIdHelper.datacenterId = datacenterId;
    }

    public static long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        SnowflakeIdHelper.workerId = workerId;
    }

    /**
     * 初始化方法
     */
    public static void init(long workerId, long datacenterId) {
        SnowflakeIdHelper.workerId = workerId;
        SnowflakeIdHelper.datacenterId = datacenterId;
        idWorker = new SnowflakeIdWorker(workerId, datacenterId);
    }

    /**
     * 初始化方法
     */
    public void init() {
        idWorker = new SnowflakeIdWorker(workerId, datacenterId);
    }

    public static void main(String[] args){
        for (int i=0;i<1000;i++){
            System.out.println(SnowflakeIdHelper.generateId());
        }
    }
}
