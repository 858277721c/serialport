package com.fanwe.lib.serialport.impl;

import com.fanwe.lib.serialport.FISerialPortSwitch;

/**
 * Created by Administrator on 2017/12/21.
 */
public class FSimpleSerialPort implements FISerialPortSwitch
{
    private FSerialPort mSerialPort = new FSerialPort();

    @Override
    public void open(String path, int baudrate, int flags) throws Exception
    {
        mSerialPort.open(path, baudrate, flags);
    }

    @Override
    public boolean isOpened()
    {
        return mSerialPort.isOpened();
    }

    @Override
    public void close()
    {
        mSerialPort.close();
    }
}
