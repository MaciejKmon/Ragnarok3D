package client.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Caelum on 12/29/14.
 */
public class Network
{
    private  boolean connected = false;
    private  InetAddress server;
    private  int serverPort;
    private  DatagramSocket socket = null;

    public  void connect(InetAddress address, int port)
    {
        server = address;
        serverPort = port;
        connected = true;

        try
        {
            socket = new DatagramSocket();
        }
        catch(SocketException e)
        {
            Debug.error(e);
        }
    }

    public static void sendData(byte[] message, InetAddress too, int port)
    {
        try
        {
            new DatagramSocket(port, too).send(new DatagramPacket(message, message.length, too, port));
        }
        catch(IOException e)
        {
            Debug.error(e);
        }
    }


    public void sendData(byte[] message)
    {
        try
        {
            Debug.info(new String(message));
            socket.send(new DatagramPacket(message, message.length, server, serverPort));
        }
        catch(NullPointerException e)
        {

            if(message != null)
                Debug.error("Must be connected to a server before sending a message without a destination");
             Debug.error(e);
        }
        catch(IOException e)
        {
            Debug.error(e);
        }
    }
}

