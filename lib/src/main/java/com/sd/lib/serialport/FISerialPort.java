package com.sd.lib.serialport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 串口
 */
public interface FISerialPort
{
    /**
     * 设置串口配置
     *
     * @param config
     */
    void setConfig(Config config);

    /**
     * 获得串口配置
     *
     * @return
     */
    Config getConfig();

    /**
     * 打开串口
     *
     * @throws Exception
     */
    void open() throws Exception;

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

    class Config
    {
        /**
         * 串口路径
         */
        public String path;
        /**
         * 波特率
         */
        public int baudrate;
        public int flags;
    }
}
