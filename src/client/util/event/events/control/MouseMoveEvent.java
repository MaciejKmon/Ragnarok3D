package client.util.event.events.control;

import client.util.event.Event;

import java.awt.event.MouseEvent;

/**
 * Created by Caelum on 2/13/15.
 */
 public class MouseMoveEvent extends Event
{
    MouseEvent e;
    private double aimX, aimY;

    // here is explaination to long story
    // i do not know how to hack
    public MouseMoveEvent(MouseEvent e, double aimX, double aimY)
    {
        this.e = e;
        this.aimX = aimX;
        this.aimY = aimY;
    }

    public double getAimX()
    {
        return aimX;
    }

    public double getAimY()
    {
        return aimY;
    }
}
