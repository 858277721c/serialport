package com.sd.lib.serialport;

/**
 * 串口
 */
public abstract class FSimpleSerialPort extends FSerialPort
{
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
    public synchronized void open() throws Exception
    {
        super.open();
        getReadThread().setInputStream(getInputStream());
        getReadThread().start();
    }

    @Override
    public void close()
    {
        super.close();
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
