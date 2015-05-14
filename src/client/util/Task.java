package client.util;

/**
 * Created by Caelum on 6/18/14.
 */
public interface Task
{
    Object[] store = new Object[10];

    public void init(Object... args);

    public void run();
}
