//package client.render.core.model.loading;
//
//import client.render.core.model.Triangle;
//import client.util.Debug;
//import com.jogamp.common.nio.Buffers;
//
//import java.nio.FloatBuffer;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Random;
//
///**
//* Created by Caelum on 3/5/15.
//*/
//public abstract class TagReader
//{
//    //These should not be modified after being loaded as TagReaders should be used more than once.
//    public ArrayList<Triangle> triangles;
//    public SubModel[] subModels;
//
//
//
//    public class SubModel
//    {
//        //Second dimension 3 floats for X Y and Z of vertex.
//        float[][] vertices;
//        //Second dimension 3-4 floats for vertex index
//        int[][] faces;
//        public FloatBuffer floatBuffer;
//
//        int faceCount = 0;
//        int vertexCount = 0;
//
//        //                                      10 metres
//        public SubModel(int verticeLength, int faceLength)
//        {
//            vertices = new float[verticeLength][3];
//            faces = new int[faceLength][4];
//            //Needs room for 3 verts each with 3 floats, plus 4 colours
//            floatBuffer = Buffers.newDirectFloatBuffer(verticeLength * 80);
//        }
//
//        public void addVertex(float x, float y, float z)
//        {
//
//            vertices[vertexCount][0] = x;
//            vertices[vertexCount][1] = y;
//            vertices[vertexCount][2] = z;
//            vertexCount++;
//        }
//
//        public void addFace(int i, int ii, int iii)
//        {
//            faces[faceCount][0] = i;
//            faces[faceCount][1] = ii;
//            faces[faceCount][2] = iii;
//
//            faceCount++;
//
//        }
//
//        public void addFace(int i, int ii, int iii, int iv)
//        {
//            faces[faceCount][0] = i;
//            faces[faceCount][1] = ii;
//            faces[faceCount][2] = iii;
//            faces[faceCount][3] = iv;
//
//            faceCount++;
////            Debug.info(faceCount + " " + faces.length);
//        }
//
//        //Clockwise means that the three vertices, in order, rotate clockwise around the triangle's center.
//        //Counter-clockwise means that the three vertices, in order, rotate counter-clockwise around the triangle's center.
//        //Add 3 vertices with 3 floats each to draw triangle, then 4 floats for colour.
//
//        //Triangles are defined in clockwise manner.
//        public void calculate()
//        {
//            for(float[] vert : vertices)
//            {
//                Debug.info(Arrays.toString(vert));
//            }
//            //Iterate through faces and add the vertices their 4 values point to.
//            for(int[] face : faces)
//            {
//
//                if(face[3] != 0)
//                {//Must be quadrilateral, draw 2 triangles.
//                    floatBuffer.put(vertices[face[2] - 1]);
//                    addColour();
//
//                    floatBuffer.put(vertices[face[3] - 1]);
//                    addColour();
//
//                    floatBuffer.put(vertices[face[0] - 1]);
//                    //4 colours are expected after 3 triangles.
//                    addColour();
//                }
//                //Either a standard triangle, or the second bit to the quadrilateral.
//                try
//                {
//                    floatBuffer.put(vertices[face[0] - 1]);
//                }
//                catch(ArrayIndexOutOfBoundsException e)
//                {}
//
//                addColour();
//                //1 is top-right.
//                floatBuffer.put(vertices[(face[1] - 1)]);
//                addColour();
//
//                floatBuffer.put(vertices[(face[2] - 1)]);
//                //2 is top left
//                addColour();
//
//            }
//            floatBuffer.flip();
//        }
//
//        private void addColour()
//        {
//            Random random = new Random();
//            floatBuffer.put(random.nextFloat());
//            floatBuffer.put(random.nextFloat());
//            floatBuffer.put(random.nextFloat());
//            floatBuffer.put(random.nextFloat());
//
////            floatBuffer.put(0.0f);
////            floatBuffer.put(0.0f);
////            floatBuffer.put(0.0f);
////            floatBuffer.put(0.0f);
//        }
//    }
//
//}
package client.render.core.model.loading;

import com.jogamp.common.nio.Buffers;

import java.nio.FloatBuffer;
import java.util.Random;

/**
* Created by Caelum on 3/5/15.
*/
public abstract class TagReader
{
    //This should not be modified after being loaded as TagReaders should be used more than once.
    public SubModel[] subModels;

    public class SubModel
    {
        public FloatBuffer floatBuffer;

        public SubModel(int verticeLength, int vertexSize)
        {

            //Needs room for 3 verts each with 3 floats, plus 4 colours
            floatBuffer = Buffers.newDirectFloatBuffer(verticeLength * vertexSize);
        }


        public void addColour()
        {
            Random random = new Random();
            floatBuffer.put(random.nextFloat());
            floatBuffer.put(random.nextFloat());
            floatBuffer.put(random.nextFloat());
            floatBuffer.put(random.nextFloat());
        }
    }
}
