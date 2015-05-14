//package client;
//
//import client.render.Camera;
//import client.render.core.AWTWindowProgram;
//import client.render.core.GeneralRenderer;
//import client.render.core.model.Triangle;
//
//import javax.media.opengl.*;
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Scene extends AWTWindowProgram
//{
//
//    private GeneralRenderer renderer;
//    private Triangle[] triangles;
//    private double cameraTheta = 0;
//    private int framesRendered = 0;
//    private int selectedRenderer = 0;
//    private Camera camera = new Camera();
//
//    public static Scene instance;
//
//    public Scene(String title, int w, int h)
//    {
//
//        super(title, w, h, new GLCapabilities(GLProfile.getDefault()));
//
//        Timer fpsTimer = new Timer(1000, new ActionListener()
//        {
//            public void actionPerformed(ActionEvent e)
//            {
//                updateFPS();
//            }
//        });
//        fpsTimer.setRepeats(true);
//        fpsTimer.start();
//
//        instance = this;
//    }
//
//
//    public void update(GL gl){}
//
//    public void dispose(GLAutoDrawable drawable)
//    {
//        renderer.dispose(drawable.getGL().getGL2());
//    }
//
//    public void init(GLAutoDrawable drawable)
//    {
//        GL2 gl = drawable.getGL().getGL2();
//
//        renderer = new GeneralRenderer();
//        renderer.init(gl, triangles);
//
//        gl.glEnable(GL.GL_DEPTH_TEST);
//        gl.glEnable(GL.GL_CULL_FACE);
//
//        new Client();
//    }
//
//    public void update(GL gl, double elapsedMS)
//    {
//        cameraTheta += 0.01;
//    }
//
//    public void render(GL glObj)
//    {
//        GL2 gl = glObj.getGL2();
//        renderer.render(gl);
//
//        framesRendered++;
//    }
//
//    public void updateFPS()
//    {
//        getFrame().setTitle(" | FPS: " + framesRendered + " | Vertexes: " + renderer.getTotalVertexes());
//        framesRendered = 0;
//    }
//}
