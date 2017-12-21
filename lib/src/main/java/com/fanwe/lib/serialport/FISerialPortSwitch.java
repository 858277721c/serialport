package com.fanwe.lib.serialport;

/**
 * 串口开关操作
 */
public interface FISerialPortSwitch
{
    /**
     * 打开串口
     *
     * @param path
     * @param baudrate
     * @param flags
     */
    void open(String path, int baudrate, int flags) throws Exception;

    /**
     * 串口是否已经打开
     *
     * @return
     */
    boolean isOpened();

    /**
     * 关闭串口
     */
    void close();
}
