package com.fanwe.lib.serialport.read;

import java.io.InputStream;

/**
 * 输入流读取线程
 */
abstract class FInputStreamReadThread extends Thread
{
    private InputStream mInputStream;

    private byte[] mBuffer;
    private long mSleepMillis;

    public FInputStreamReadThread(byte[] buffer, long sleepMillis)
    {
        super("FInputStreamReadThread");
        if (buffer == null)
        {
            throw new NullPointerException("buffer must not be null");
        }
        if (buffer.length <= 0)
        {
            throw new IllegalArgumentException("bufferSize must > 0");
        }
        mBuffer = buffer;
        mSleepMillis = sleepMillis;
    }

    /**
     * 设置输入流
     *
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream)
    {
        mInputStream = inputStream;
    }

    private void trySleep() throws InterruptedException
    {
        if (mSleepMillis > 0)
        {
            sleep(mSleepMillis);
        }
    }

    @Override
    public void run()
    {
        super.run();

        while (!isInterrupted())
        {
            try
            {
                if (mInputStream == null)
                {
                    trySleep();
                } else
                {
                    int readSize = mInputStream.read(mBuffer);
                    if (readSize > 0)
                    {
                        onReadData(mBuffer, readSize);
                    } else
                    {
                        trySleep();
                    }
                }
            } catch (Exception e)
            {
                if (e instanceof InterruptedException)
                {
                    return;
                } else
                {
                    onReadError(e);
                }
            }
        }
    }

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
