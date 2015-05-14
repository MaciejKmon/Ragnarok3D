package client.render.GUI;

import client.util.Debug;

import java.awt.*;

/**
 * Created by Caelum on 10/30/14.
 */
public class GUI implements Runnable
{
    private static GUI instance;

    public static GUI getInstance()
    {
        return instance;
    }

    public static Enviroment screen = new Enviroment();

    public GUI(Frame frame)
    {
        if(instance == null)
            instance = this;


    }

    @Override public void run()
    {
        Debug.info("test");
    }
}
