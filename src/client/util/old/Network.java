/**
 * Bleh! pesky TCP.
 */

//package client.util.old;
//
//import client.Settings;
//import client.util.Debug;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.ConnectException;
//import java.net.Socket;
//import java.nio.charset.Charset;
//
///**
// * Created by Caelum on 11/2/14.
// */
//public class Network
//{
//    public static Network instance;
//
//    public boolean connecting = false;
//    private OutputStream out = null;
//    private DataInputStream in = null;
//
//    private int attemptsLeft;
//
//    public Network()
//    {
//        if(instance == null)
//        {
//            attemptsLeft = Settings.Integers.connectionAttempts.getValue();
//
//            connect("localhost", 6969);
//
//            connecting = true;
//            if(out != null && in != null)
//            {
//
//                try
//                {
//                    System.out.println(in.readInt());
//                    out.write("test".getBytes());
//                }
//                catch(IOException e)
//                {
//                    e.printStackTrace();
//                    connect("localhost", 6969);
//                }
//
//            }
//        }
//    }
//
//
//    public void connect(String target, int port)
//    {
//        while(attemptsLeft > 0)
//        {
//            try
//            {
//                Socket socket = new Socket(target, port);
//
//                in = new DataInputStream(socket.getInputStream());
//                out = socket.getOutputStream();
//                DataOutputStream out = new DataOutputStream(this.out);
//                byte[] arbytes = "dic\nrijfi\nnfygfy".getBytes(Charset.forName("UTF-8"));
//                out.writeInt(arbytes.length);
//                out.flush();
//
//                this.out.write(arbytes);
//                this.out.flush();
//
//                Debug.info("Successfully connected to " + target + ":" + port);
//
//                break;
//            }
//            catch(IOException e)
//            {
//                if(e instanceof ConnectException)
//                    attemptsLeft--;
//
//                e.printStackTrace();
//            }
//            try
//            {
//                Thread.sleep(500);
//                Debug.info("Retrying connection... (" + attemptsLeft + ")");
//            }
//            catch(InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void setConnecting(boolean connecting)
//    {
//        this.connecting = connecting;
//    }
//}
//
