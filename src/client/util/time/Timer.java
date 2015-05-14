package client.util.time;

import client.util.Listener;

public class Timer extends Thread
{
    private Listener receiver;
    private long delay;
    private String event;

    public Timer(int delay)
    {
        this.delay = delay;
        this.start();
    }

    public Timer(int delay, Listener receiver)
    {
        this.delay = delay;
        this.receiver = receiver;
        this.start();
    }

    public Timer(int delay, String event, Listener receiver)
    {
        this.delay = delay;
        this.receiver = receiver;
        this.event = event;
        this.start();
    }


    @Override
    public void run()
    {
        while(true)
        {

            try
            {
                Thread.sleep(delay);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            receiver.onTick(event);
        }
    }

}

//