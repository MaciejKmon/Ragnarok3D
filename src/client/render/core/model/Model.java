//package client.render.core.model;
//
//import client.util.Debug;
//import com.jogamp.common.nio.Buffers;
//import javafx.geometry.Point3D;
//
//import java.nio.FloatBuffer;
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
//* Created by Caelum on 9/9/14.
//* This class is like a cookie cutter to create Entities, similar to how classes make objects.
//*
//*/
//public class Model
//{
//    //     h
//    //     r
////fuguo.huang@ie.ibm.com
//    private static HashMap<String, Model> loadedModels = new HashMap<>();
//    private static int maxIndex = 0;
//
//
//    /*
//        loaders models are loaded by
//     */
//    private ArrayList<Triangle> triangles = new ArrayList<>();
//    public ArrayList<SubModel> subModels = new ArrayList<>();
//    private boolean loaded;
//
//    public class SubModel
//    {
//        ArrayList<Triangle> triangles = new ArrayList<>();
//        ArrayList<Point3D> vertices = new ArrayList<>();
//        ArrayList<Face> faces = new ArrayList<>();
//        public FloatBuffer floatBuffer;
//
//        public void generateTriangles()
//        {
//            Debug.info("generating");
//            //Connect vertices together with faces and generate Triangles for easier processing.
//            for(Face face : faces)
//            {
//                int[] connections = face.connections;
//                triangles.add(new Triangle(
//                        vertices.get(connections[0]),
//                        vertices.get(connections[1]),
//                        vertices.get(connections[2])));
//                if(connections[3] != - 1)
//                    triangles.add(new Triangle(
//                            vertices.get(connections[0]),
//                            vertices.get(connections[3]),
//                            vertices.get(connections[2])));
//                triangles.add(new Triangle(
//                        vertices.get(connections[2]),
//                        vertices.get(connections[1]),
//                        vertices.get(connections[0])));
//                if(connections[3] != - 1)
//                    triangles.add(new Triangle(
//                            vertices.get(connections[2]),
//                            vertices.get(connections[3]),
//                            vertices.get(connections[0])));
//            }
//            //Allocate room for triangles(3 doubles) + colours (4 floats)
//            floatBuffer = Buffers.newDirectFloatBuffer(triangles.size() * 70);
//            //fill floatBuffer with Triangles and their colours.
//            for(Triangle triangle : triangles)
//                //3 locations and 1 colour per triangle.
//                for(int i = 0; i < 3; i++)
//                {
//                    //3 floats for x y and z
//                    floatBuffer.put(triangle.vertices[i][0]).put(triangle.vertices[i][1]).put(triangle.vertices[i][2]);
//                    // + 4 color floats(RGBA) = attrib thingy
//                    for(int j = 0; j < 4; j++)
//                        floatBuffer.put(0.5f);
//                }
//        }
//    }
//    private Model(String filePath)
//    {
//
//        loadedModels.put(filePath, this);
//        maxIndex++;
//
//
////        generateTriangles();
//
//
//
////        for(int i = 0; i < triangles.size() / 3; i++)
////        {
////
////            floatBuffer.put(triangles.get(i).vertices[0]);
////            floatBuffer.put(triangles.get(i).vertices[1]);
////            floatBuffer.put(triangles.get(i).vertices[2]);
////            Debug.info(triangles.get(i).vertices[0]);
////            for(int j = 0; j < 4; j++)
////                floatBuffer.put(0.4f);
////        }
//
//
//    }
//
//
//    public ArrayList<Triangle> getTriangles()
//    {
//        return triangles;
//    }
//
//    public static Model getModel(String path)
//    {
//        return loadedModels.get(path) != null ? loadedModels.get(path) : new Model(path);
//    }
//
//    public boolean isLoaded()
//    {
//        return loaded;
//    }
//
//}
//
//
//
