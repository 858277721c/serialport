package com.fanwe.lib.serialport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 串口
 */
public interface FISerialPort
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
     * 返回输入流
     *
     * @return
     */
    InputStream getInputStream();

    /**
     * 返回输出流
     *
     * @return
     */
    OutputStream getOutputStream();

    /**
     * 关闭串口
     */
    void close();
}
