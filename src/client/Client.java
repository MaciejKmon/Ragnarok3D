package client;

import client.render.Camera;
import client.render.core.Window;
import client.util.Control;

/**
 * Created by Caelum on 3/16/14.
 */
public class Client
{

   static String uvs = "            {0.333333f, 0.333333f},\n" +
            "                {0.333333f, 0.0f},\n" +
            "                {0.666667f, 0.0f},\n" +
            "                {0.666667f, 0.0f},\n" +
            "                {0.666667f, 0.333333f},\n" +
            "                {0.333333f, 0.333333f},\n" +
            "                {0.333333f, 0.333333f},\n" +
            "                {0.0f, 0.666667f},\n" +
            "                {0.0f, 0.333333f},\n" +
            "                {0.333333f, 0.333333f},\n" +
            "                {0.333333f, 0.333333f},\n" +
            "                {0.333333f, 0.666667f},\n" +
            "                {0.333333f, 0.666667f},\n" +
            "                {0.0f, 0.666667f},\n" +
            "                {0.0f, 0.333333f},\n" +
            "                {0.0f, 0.0f},\n" +
            "                {0.333333f, 0.0f},\n" +
            "                {0.333333f, 0.0f},\n" +
            "                {0.333333f, 0.0f},\n" +
            "                {0.333333f, 0.333333f},\n" +
            "                {0.0f, 0.333333f},\n" +
            "                {0.333333f, 0.333333f},\n" +
            "                {0.666667f, 0.333333f},\n" +
            "                {0.666667f, 0.666667f},\n" +
            "                {0.666667f, 0.666667f},\n" +
            "                {0.666667f, 0.666667f},\n" +
            "                {0.333333f, 0.666667f},\n" +
            "                {0.333333f, 0.333333f},\n" +
            "                {1.0f, 0.0f},\n" +
            "                {1.0f, 0.333333f},\n" +
            "                {1.0f, 0.333333f},\n" +
            "                {0.666667f, 0.333333f},\n" +
            "                {0.666667f, 0.333333f},\n" +
            "                {0.666667f, 0.0f},\n" +
            "                {1.0f, 0.0f},\n" +
            "                {0.0f, 0.666667f}";
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
//        String reversed =  "";
//
//        String[] split = uvs.split("\n");
//
//        for(int i = split.length - 1; i > 0; i--)
//        {
//            reversed += split[i] + "\n";
//        }
//        Debug.info(reversed);

        Window window = Window.self;
        //        ClientNetwork.getInstance().connect("localhost", 9876);
        //        System.out.println(Integer.MAX.0_VALUE);
    }

    private void initSingletons(int i, int ii, int iii)
    {

    }
}

