package client.render.GUI;

/**
 * Created by Caelum on 1/9/15.
 */
public class DIX
{
    private static DIX ourInstance = new DIX();

    public static DIX getInstance()
    {
        return ourInstance;
    }

    private int dix;
    private String memes;

    private DIX()
    {
    }

    public DIX(int dix)
    {
        this.dix = dix;
    }

    public DIX(int dix, String memes)
    {
        this.dix = dix;
        this.memes = memes;
    }
}
























