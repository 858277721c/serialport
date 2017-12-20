package com.fanwe.lib.serialport;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 串口
 */
public class FSerialPort implements FISerialPort
{
    private SerialPort mSerialPort;

    @Override
    public synchronized void open(String path, int baudrate, int flags) throws Exception
    {
        try
        {
            closeInternal();
            mSerialPort = new SerialPort(new File(path), baudrate, flags);
        } catch (Exception e)
        {
            closeInternal();
            throw e;
        }
    }

    @Override
    public synchronized boolean isOpened()
    {
        return mSerialPort != null;
    }

    @Override
    public synchronized InputStream getInputStream()
    {
        if (isOpened())
        {
            return mSerialPort.getInputStream();
        } else
        {
            return null;
        }
    }

    @Override
    public synchronized OutputStream getOutputStream()
    {
        if (isOpened())
        {
            return mSerialPort.getOutputStream();
        } else
        {
            return null;
        }
    }

    private void closeInternal()
    {
        if (mSerialPort != null)
        {
            mSerialPort.close();
            mSerialPort = null;
        }
    }

    @Override
    public synchronized void close()
    {
        closeInternal();
    }
}
