package client.render.core;

import client.util.Debug;
import com.jogamp.common.nio.Buffers;

import java.nio.FloatBuffer;

/**
 * Created by Caelum on 3/16/15.
 *
 * Like a entity but only for rendering purposes.
 */
public class VBOO
{
    int vaoIndex[];
    int vboIndex[];

    private int index;

    FloatBuffer interleavedBuffer;

    float translateX = -0.4f;
    float translateY = 0.4f;
    float translateZ = 2.5f;

    float rotation = 0;
    float rotationX = 1;
    float rotationY  = 1;
    float rotationZ  = 0;

    float scaleX  = 1;
    float scaleY = 1;
    float scaleZ = 1;

    private VBOO()
    {
        interleavedBuffer = Buffers.newDirectFloatBuffer(0);
    }

    public VBOO(float[] vertices, float[] colours)
    {
        interleavedBuffer = Buffers.newDirectFloatBuffer(vertices.length + colours.length);
        //Ohhhh the vertex array is only 1 dimensional
        //                                                           3 groups of 3 locations + 4 colours ((3 x 3) + 4)
//        for(int triangleIndex = 0; triangleIndex < vertices.length / 9; triangleIndex++)
//        {
//            Debug.info("adding triangle");
            //            for(int vertexIndex = 0; vertexIndex < 3; vertexIndex ++)
            //            {
            //                for(int coord = 0; coord < 3; coord++)
            //                {
            //                    interleavedBuffer.put(vertices[(triangleIndex * 13) + vertexIndex + coord]);
            //
            //                }
            ////                for(int color = 0; color < 4; color++)
            ////                {
            ////                    Debug.info(colours[(triangleIndex * 13) + (vertexIndex * 3) + color]);
            ////                    interleavedBuffer.put(colours[(triangleIndex * 13) + (vertexIndex * 3) + color]);
            ////                }
            //                interleavedBuffer.put(0.0f);
            //                interleavedBuffer.put(0.0f);
            //                interleavedBuffer.put(0.0f);
            //                interleavedBuffer.put(0.0f);
            //            }
        //(VVVCCCC)
                    for(int i = 0; i < vertices.length / 3; i++)
                    {
                        Debug.info("added one");
                        for(int j = 0; j < 3; j++)
                        {
                            interleavedBuffer.put(vertices[i * 3 + j]);
                        }
                        for(int j = 0; j < 4; j++)
                        {
                            interleavedBuffer.put(colours[i * 4 + j]);
                        }
                    }


//        }
        interleavedBuffer.flip();


    }

    public VBOO(FloatBuffer interleavedBuffer)
    {

        this.interleavedBuffer = interleavedBuffer.duplicate();
    }
}