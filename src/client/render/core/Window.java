package client.render.core;

import client.Settings;
import client.util.Control;
import client.util.Debug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Caelum on 11/30/14.
 */
public class Window
{

    //                         shelf?
    public static final Window self = create();


    private SceneRenderer mainRenderer;

    public static int width;
    public static int height;
    public static int x;
    public static int y;
    public static Frame frame;

    private static Window create()
    {
        final Window[] window = new Window[1];
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                window[0] = new Window();
            }
        });
        Debug.info("Created window: " + window[0]);

        return window[0];
    }

    private Window()
    {
        frame = new JFrame("Sample Text");
        frame.setLayout(new BorderLayout());

        Dimension dimensions = new Dimension(Settings.Integers.defaultWindowWidth.getValue(), Settings.Integers.defaultWindowHeight.getValue());

        mainRenderer = new SceneRenderer(dimensions);

        frame.addWindowListener(new WindowAdapter()
        {
            @Override public void windowClosing(WindowEvent e)
            {
                mainRenderer.animator.stop();
                Debug.info("Exiting...");
                System.exit(0);
            }
        });

        frame.add(mainRenderer.panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        frame.addMouseMotionListener(Control.thisOne);
    }

}

