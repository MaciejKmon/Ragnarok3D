package client.render;

import client.util.Control;
import client.util.Listener;
import client.util.event.EventHandler;
import client.util.event.EventSettings;
import client.util.event.events.control.MouseMoveEvent;
import client.util.time.Timer;

import java.awt.event.KeyEvent;

/**
 * Created by UR MOM on 3/13/14.
 */
public class Camera implements Listener, Control.keyPressEvent
{

    public static float eyeX, eyeY, eyeZ;
    public static float centerX, centerY, centerZ;
    public static float upX, upY, upZ;
    private static float zoom;
    private static Camera single;


    public Camera()
    {
        if(single == null)
        {
            single = this;

        }else
            return;

        new Timer(10, this);


        Control.addListener(Control.Key.ESC, this);
        EventHandler.addEventListener(this, this);

        Camera.setCenterX(0f);
        Camera.setCenterY(0);
        Camera.setCenterZ(2.5f);
        Camera.setUpY(1);

    }
    //Todo: Despite what those liars at Stackoverflow would have you believe getters/setters should only be used to do more than get/set.
    public static void setCenterX(float centerX)
    {
        Camera.centerX = centerX;

    }

    public static void setCenterY(float centerY)
    {
        Camera.centerY = centerY;

    }

    public static void setCenterZ(float centerZ)
    {
        Camera.centerZ = centerZ;
    }

    public static void setEyeX(float eyeX)
    {
        Camera.eyeX = eyeX;
    }

    public static void setEyeY(float eyeY)
    {
        Camera.eyeY = eyeY;
    }

    public static void setEyeZ(float eyeZ)
    {
        Camera.eyeZ = eyeZ;
    }

    public static void setUpX(float upX)
    {
        Camera.upX = upX;
    }

    public static void setUpY(float upY)
    {
        Camera.upY = upY;
    }

    public static void setUpZ(float upZ)
    {
        Camera.upZ = upZ;
    }

    public static void setZoom(float zoom)
    {
        Camera.zoom = zoom;
    }

    //Get
    public static float getCenterX()
    {
        return centerX;
    }

    public static float getCenterY()
    {
        return centerY;
    }

    public static float getCenterZ()
    {
        return centerZ;
    }

    public static float getEyeX()
    {
        return eyeX;
    }

    public static float getEyeY()
    {
        return eyeY;
    }

    public static float getEyeZ()
    {
        return eyeZ;
    }

    public static float getUpX()
    {
        return upX;
    }

    public static float getUpY()
    {
        return upY;
    }

    public static float getUpZ()
    {
        return upZ;
    }

    public static float getZoom()
    {
        return zoom;
    }


    @Override public void onTick(String event)
    {


    }

    @Override public void sendKeyPressEvent(KeyEvent e)
    {

    }
    //My math slaves should figure this one out any day now.
    @EventSettings public void mouseEvent(MouseMoveEvent e)
    {
        setEyeX((float) (- ( (Math.cos(Math.toRadians(e.getAimX()))) * 1) /*+ Camera.getEyeX()*/));
        setEyeY((float) (- (  (Math.tan(Math.toRadians(e.getAimY()))) * 1) /*+ Camera.getEyeY()*/));
        setEyeZ((float) (- (Math.sin(Math.toRadians(e.getAimY() + e.getAimX())) * 1) /*+ Camera.getEyeZ()*/));
//        centerX = (float)e.getAimX();
//        centerY = (float)e.getAimY();
    }
}
