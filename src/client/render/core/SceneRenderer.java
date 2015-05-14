package client.render.core;

import client.Settings;
import client.render.Camera;
import client.render.core.model.loading.TagReader;
import client.render.core.model.loading.TextModelLoader;
import client.render.core.model.loading.builders.VIMBuilder;
import client.util.Debug;
import client.util.PerformanceTest;
import client.util.files.FileIO;
import client.world.entities.Entity;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.PMVMatrix;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
* Created by  on 12/4/14.
*/

public class SceneRenderer implements GLEventListener
{
    public final FPSAnimator animator;
    public final GLJPanel panel;
    private final Dimension dimensions;

    private static final int locPos = 1;
    private static final int locCol = 2;

    private int transformMatrixLocation;
    private float theta = 0;

    private int shaderProgram;
    public ShaderManager shaderManager = new ShaderManager();
    int frames = 0;
    private PerformanceTest pt = new PerformanceTest();
    private PMVMatrix pmv;
    private float[] instanceTransform0 = new float[16];

    float c = 0.5f;
//    private FloatBuffer interleavedBuffer;

//    private ArrayList
//    private Viewport viewport = new Viewport(0, 0, Settings.Integers.defaultWindowWidth.getValue(), Settings.Integers.defaultWindowHeight.getValue());


     float[] vertices = {
              1,0,0,

             -0.980785f,
             0.0f,
             0.19509f,

             -0.96194f,
             0.191342f,
             0.19509f,

             0.980785f,
             0.19509f,
             0.0f




            -0.980785f,
             0.0f,
             0.19509f,


             0.980785f,
             0.19509f,
             0.0f,

             1,0,0
     };

    private final float[] colors = {
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f, 0.5f,

    };
//Potential
    private double lastTime = System.currentTimeMillis();
//
//    private int[] vbo;
//    private int[] vao;
    private ArrayList<VBOO> vboos = new ArrayList<VBOO>();
//
    private GL4 gl;


//
//
    public SceneRenderer(Dimension dimensions)
    {
        GLProfile profile = GLProfile.get(GLProfile.GL4);
        panel = new GLJPanel(new GLCapabilities(profile));
        panel.addGLEventListener(this);
        panel.setPreferredSize(dimensions);

        animator = new FPSAnimator(panel, Settings.Integers.maxFPS.getValue(), true);
        animator.start();

        this.dimensions = dimensions;
    }

    @Override public void init(GLAutoDrawable glAutoDrawable)
    {
        gl = glAutoDrawable.getGL().getGL4();
        glAutoDrawable.setGL(new DebugGL4(gl));

        gl.glClearColor(1, 1, 1, 1);
        gl.glClearDepth(1.0);

        Debug.spam("GLCapabilities:  " + glAutoDrawable.getChosenGLCapabilities());
        Debug.spam("Init GL: " + gl.getClass().getName());
        Debug.spam("GL4.GL_VENDOR: " + gl.glGetString(GL4.GL_VENDOR));
        Debug.spam("GL4.GL_RENDERER: " + gl.glGetString(GL4.GL_RENDERER));
        Debug.spam("GL4.GL_VERSION: " + gl.glGetString(GL4.GL_VERSION));
        Debug.spam("GL4.GL_SHADING_LANGUAGE_VERSION" + gl.glGetString(GL4.GL_SHADING_LANGUAGE_VERSION));

        gl.glEnable(GL.GL_CULL_FACE);
        gl.glEnable(GL.GL_MULTISAMPLE);
        gl.glEnable(GL.GL_LINE_SMOOTH);

        gl.glLineWidth(10f);

        int vertexShader = loadShader(FileIO.readText(Settings.Strings.vertexShaderPath.getValue()), GL4.GL_VERTEX_SHADER);
        int fragmentShader = loadShader(FileIO.readText(Settings.Strings.fragmentShaderPath.getValue()), GL4.GL_FRAGMENT_SHADER);

        shaderProgram = gl.glCreateProgram();
        gl.glAttachShader(shaderProgram, vertexShader);
        gl.glAttachShader(shaderProgram, fragmentShader);

        gl.glBindAttribLocation(shaderProgram, locPos, "VertexPosition");
        gl.glBindAttribLocation(shaderProgram, locCol, "VertexColor");

        gl.glLinkProgram(shaderProgram);

        int modelViewProjectionMatrixLocation = gl.glGetUniformLocation(shaderProgram, "uniform_Projection");
        System.out.println("modelViewProjectionMatrixLocation:" + modelViewProjectionMatrixLocation);
        transformMatrixLocation = gl.glGetUniformLocation(shaderProgram, "uniform_Transform");
//        gl.getLocation
        System.out.println("transformMatrixLocation:" + transformMatrixLocation);


//

        pmv = new PMVMatrix();
        pmv.gluPerspective(70, dimensions.width / (float) dimensions.height, 0.01f, 40);


//        loadEntity(new Entity(-1, 0, -80, ModelLoader.instance.load("test.vim", VIMReader.class)));

        for(TagReader.SubModel subModel : TextModelLoader.instance.load("test.vim", VIMBuilder.class).subModels)
            addVBOO(new VBOO(subModel.floatBuffer));

//        addVBOO(new VBOO(vertices, colors));
    }

    private void createVertexArray()
    {

    }

    private int loadShader(String text, int shaderSpec)
    {
        int vertexShader = gl.glCreateShader(shaderSpec);
        String[] lines = new String[]{text};
        int[] shaderLineCount = new int[]{lines[0].length()};

        gl.glShaderSource(vertexShader, lines.length, lines, shaderLineCount, 0);
        gl.glCompileShader(vertexShader);

        int[] compiled = new int[1];
        gl.glGetShaderiv(vertexShader, GL4.GL_COMPILE_STATUS, compiled, 0);

        if(compiled[0] != 0)
            Debug.spam("Vertex shader iz gud");
        else
        {
            int[] logLength = new int[1];
            gl.glGetShaderiv(vertexShader, GL4.GL_INFO_LOG_LENGTH, logLength, 0);
            byte[] log = new byte[logLength[0]];
            gl.glGetShaderInfoLog(vertexShader, logLength[0], null, 0, log, 0);
            Debug.error("Oi, that vertex shader you showed me ain't proper, innit ya damn pleb:\n" + new String(log));

            System.exit(0);
        }

        return vertexShader;
    }

//

    private void loadEntity(Entity entity)
    {
        for(VBOO vboo : entity.vboos)
        {
            addVBOO(vboo);

//            for(int i = 0; i < vboo.interleavedBuffer.capacity(); i += 7)
//                Debug.info("VBOO " + "," + vboo.interleavedBuffer.get( i + 1) + "," + vboo.interleavedBuffer.get( i + 2) + "," + vboo.interleavedBuffer.get( i + 3)
//                        + "," + vboo.interleavedBuffer.get( i + 4) + "," + vboo.interleavedBuffer.get( i + 5) + "," + vboo.interleavedBuffer.get( i + 6)  + "," + vboo.interleavedBuffer.get( i + 7));
        }

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        System.out.println("Window resized to width=" + width + " height=" + height);
        GL4 gl = drawable.getGL().getGL4();
        gl.glViewport(0, 0, width, height);


//        viewport = new Viewport(0, 0, width, height);

        Window.x = Window.frame.getX();
        Window.y = Window.frame.getY();
        Window.width = width;
        Window.height = height;

    }
    //TODO replace vboo arraylist with entity araraylist
    @Override
    public void display(GLAutoDrawable drawable)
    {
//        try(BufferedInputStream is = new BufferedInputStream(new FileInputStream(filePath))){
//            //Create the PNGDecoder object and decode the texture to a buffer
//            PNGDecoder decoder = new PNGDecoder(is);
//            int width = decoder.getWidth(), height = decoder.getHeight();
//            ByteBuffer pixelData = BufferUtils.createByteBuffer(4*width*height);
//            decoder.decode(pixelData, 4*width, PNGDecoder.Format.RGBA);
//            pixelData.flip();
//            //Generate and bind the texture
//            int id = GL11.glGenTextures();
//            GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
//            //Upload the buffer's content to the VRAM
//            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixelData);
//            //Apply filters
//            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
//            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
//            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
//            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
//            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
//        }catch(IOException e){
//            e.printStackTrace();

            double currentTime = System.currentTimeMillis();

        theta += (currentTime - lastTime) * 0.05f;
        lastTime = currentTime;

        GL4 gl = drawable.getGL().getGL4();
        gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);
        gl.glUseProgram(shaderProgram);

        for(VBOO v : vboos)
        {
            gl.glBindVertexArray(v.vboIndex[0]);

            pmv.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
            pmv.glPushMatrix();

                pmv.gluLookAt(Camera.eyeX, Camera.eyeY, Camera.eyeZ, Camera.centerX, Camera.centerY, Camera.centerZ, Camera.upX, Camera.upY, Camera.eyeZ);

                pmv.glTranslatef(v.translateX, v.translateY, v.translateZ);

                pmv.glRotatef(v.rotationX + (theta / 10), 1, 0, 0);
                pmv.glRotatef(v.rotationY + (theta / 10), 0, 1, 0);
                pmv.glRotatef(v.rotationZ + (theta / 10), 0, 0, 1);

                pmv.glScalef(0.5f, 0.5f, 0.5f);

                pmv.glGetFloatv(GLMatrixFunc.GL_MODELVIEW, instanceTransform0, 0);
                gl.glUniformMatrix4fv(transformMatrixLocation, instanceTransform0.length, false, instanceTransform0, 0);

                gl.glDrawArraysInstanced(GL4.GL_TRIANGLES, 0, v.interleavedBuffer.capacity() / 7, 1);
            pmv.glPopMatrix();

        }
        //cleanup
        gl.glBindVertexArray(0);
        gl.glUseProgram(0);
//
        frames++;
    }

    @Override
    public void dispose(GLAutoDrawable drawable)
    {
        GL4 gl = drawable.getGL().getGL4();
        gl.glUseProgram(0);
        //        gl.glDeleteBuffers(2, vboos.get(0, 0);
        //        gl.glDetachShader(shaderProgram, vertShader);
        //        gl.glDeleteShader(vertShader);
        //        gl.glDetachShader(shaderProgram, fragShader);
        //        gl.glDeleteShader(fragShader);
        gl.glDeleteProgram(shaderProgram);
    }

    private float[] concatenateArrays(float[] arg0, float[] arg1)
    {
        float[] result = new float[arg0.length + arg1.length];
        System.arraycopy(arg0, 0, result, 0, arg0.length);
        System.arraycopy(arg1, 0, result, arg0.length, arg1.length);

        return result;
    }
    private class ShaderManager
    {
        //                                closet
        private HashMap<String, Integer> uniforms = new HashMap<>();


        public int addUniform(String uniformName)
        {
            int location = gl.glGetUniformLocation(shaderProgram, uniformName);
            uniforms.put(uniformName, location);
            return location;
        }
        public int getUniform(String uniformName)
        {
            // HAAAAA GAYYYYYYY
            return uniforms.get(uniformName) == null ? addUniform(uniformName) : uniforms.get(uniformName);
        }
    }


    private void addVBOO(VBOO vboo)
    {
        vboo.vaoIndex = new int[1];
        gl.glGenVertexArrays(1, vboo.vaoIndex, 0);
        gl.glBindVertexArray(vboo.vaoIndex[0]);

        vboo.vboIndex = new int[1];

        gl.glGenBuffers(1, vboo.vboIndex, 0);
        Debug.info(vboo.vboIndex[0] + " ");
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vboo.vboIndex[0]);

        gl.glBufferData(GL4.GL_ARRAY_BUFFER, vboo.interleavedBuffer.limit() * Buffers.SIZEOF_FLOAT, vboo.interleavedBuffer, GL4.GL_STATIC_DRAW);

        gl.glEnableVertexAttribArray(locPos);
        gl.glEnableVertexAttribArray(locCol);

        int stride = Buffers.SIZEOF_FLOAT * (3 + 4);

        long arg0 = Buffers.SIZEOF_FLOAT * 3;

        gl.glVertexAttribPointer(locPos, 3, GL4.GL_FLOAT, false, stride, 0);
        gl.glVertexAttribPointer(locCol, 4, GL4.GL_FLOAT, false, stride, arg0);
        vboos.add(vboo);
    }

    public void updateVBOO(VBOO vboo)
    {
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vboo.vboIndex[0]);
        gl.glBufferSubData(GL4.GL_ARRAY_BUFFER, 0, vboo.interleavedBuffer.limit() * Buffers.SIZEOF_FLOAT, vboo.interleavedBuffer);
    }
}
