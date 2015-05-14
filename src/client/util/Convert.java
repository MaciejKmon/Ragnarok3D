package client.util;

/**
 * Created by Caelum on 2/2/14.
 */
@Deprecated
public class Convert
{
    //Todo: Remove this crap
    public static byte[][] toByte(String[] strings)
    {
        byte[][] bytes = new byte[strings.length][1024];

        for(int i = 0; i < strings.length; i++)
        {
            String s = strings[i];
            bytes[i] = s.getBytes();
        }

        return bytes;
    }
}
