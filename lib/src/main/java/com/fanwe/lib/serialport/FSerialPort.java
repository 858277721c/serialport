package com.fanwe.lib.serialport;

import android.text.TextUtils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

/**
 * 串口
 */
public class FSerialPort implements FISerialPort
{
    private SerialPort mSerialPort;
    private Config mConfig;

    @Override
    public synchronized void setConfig(Config config)
    {
        mConfig = config;
    }

    @Override
    public synchronized Config getConfig()
    {
        return mConfig;
    }

    @Override
    public synchronized void open() throws Exception
    {
        if (mConfig == null)
        {
            throw new NullPointerException("you must invoke setConfig(config) method set a config before this");
        }
        if (TextUtils.isEmpty(mConfig.path))
        {
            throw new IllegalArgumentException("Config.path must not be null or empty");
        }

        if (isOpened())
        {
            return;
        }
        try
        {
            mSerialPort = new SerialPort(new File(mConfig.path), mConfig.baudrate, mConfig.flags);
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
