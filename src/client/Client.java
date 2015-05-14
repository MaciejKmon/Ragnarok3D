package client;

import client.render.Camera;
import client.render.core.Window;
import client.util.Control;

/**
 * Created by Caelum on 3/16/14.
 */
public class Client
{
    public Client()
    {
    }

    public static void main(String[] args)
    {
        //                new Scene("Updating...", 1650, 1200 );

        //        Debug.info(Maths.randomKey(3));
//        ModelLoader.instance.load("test.vim", VIMReader.class);

        new Control(Settings.Integers.defaultWindowWidth.getValue(), Settings.Integers.defaultWindowHeight.getValue());
        new Camera();


        Window window = Window.self;
        //        ClientNetwork.getInstance().connect("localhost", 9876);
        //        System.out.println(Integer.MAX.0_VALUE);
    }

    private void initSingletons(int i, int ii, int iii)
    {

    }
}

