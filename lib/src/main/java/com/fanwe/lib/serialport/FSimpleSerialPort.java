package com.fanwe.lib.serialport;

/**
 * 串口
 */
public abstract class FSimpleSerialPort implements FISerialPortSwitch
{
    private FSerialPort mSerialPort = new FSerialPort();
    private FInputStreamReadThread mReadThread;

    private FInputStreamReadThread getReadThread()
    {
        if (mReadThread == null)
        {
            mReadThread = new FInputStreamReadThread(provideReadConfig())
            {
                @Override
                protected void onReadData(byte[] data, int readSize)
                {
                    FSimpleSerialPort.this.onReadData(data, readSize);
                }

                @Override
                protected void onReadError(Exception e)
                {
                    FSimpleSerialPort.this.onReadError(e);
                }
            };
        }
        return mReadThread;
    }

    @Override
    public void open(String path, int baudrate, int flags) throws Exception
    {
        mSerialPort.open(path, baudrate, flags);
        getReadThread().setInputStream(mSerialPort.getInputStream());
        getReadThread().start();
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
        if (mReadThread != null)
        {
            mReadThread.interrupt();
            mReadThread = null;
        }
    }

    /**
     * 返回一个读取配置
     *
     * @return
     */
    protected abstract FInputStreamReadThread.ReadConfig provideReadConfig();

    /**
     * 读取到数据
     *
     * @param data
     * @param readSize
     */
    protected abstract void onReadData(byte[] data, int readSize);

    /**
     * 读取异常
     *
     * @param e
     */
    protected abstract void onReadError(Exception e);
}
