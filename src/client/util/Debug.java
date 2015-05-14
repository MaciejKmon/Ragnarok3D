package client.util;


public class Debug
{
    //This way we can keep our debugs organised, and change the way debug messages are handled without having to edit millions of lines.
    public static void error(Object message, Class origin)
    {
        System.out.println("ERROR: " + String.valueOf(message));
    }

    public static void error(Object msg)
    {
        System.out.println("ERROR: " + msg);
    }

    public static void error(int msg)
    {
        System.out.println("ERROR: " + msg);
    }

    public static void info(Object msg)
    {
        System.out.println("info: " + msg);
    }

    public static void error(Exception e)
    {
        e.printStackTrace();
        System.exit(0);
    }

    public static void info(String msg, Class origin)
    {
        System.out.println("info: " + msg);
    }

    public static void spam(Object message)
    {
        System.out.println(message);
    }

    public static void exit(int status)
    {
        System.exit(status);
    }

}
//