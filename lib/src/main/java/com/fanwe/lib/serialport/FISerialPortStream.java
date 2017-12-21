package com.fanwe.lib.serialport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 串口流操作
 */
public interface FISerialPortStream
{
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
}
