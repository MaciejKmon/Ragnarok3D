package client.util;

import java.awt.*;

/**
 * Created by Caelum on 11/1/14.
 */
public interface GLJPanelListener extends java.util.EventListener
{
    public void preRender(Graphics2D g2);

    public void postRender(Graphics2D g2);
}
