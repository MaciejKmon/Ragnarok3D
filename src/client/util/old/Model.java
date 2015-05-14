//package client.util.old;
//
//import client.render.core.model.Triangle;
//import client.render.core.model.Face;
//import client.util.Debug;
//import client.util.files.FileIO;
//import javafx.geometry.Point3D;
//
//import java.nio.FloatBuffer;
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// * Created by Caelum on 9/9/14.
// */
//public class Model
//{
//
//    private static HashMap<String, Model>loadedModels = new HashMap<>();
//    private static int maxIndex = 0;
//
//
//    private ArrayList<Triangle> triangles = new ArrayList<>();
//    private ArrayList<SubModel> subModels = new ArrayList<>();
//    FloatBuffer floatBuffer;
//
//    private class SubModel
//    {
//        ArrayList<Point3D> vertexes = new ArrayList<>();
//        ArrayList<Face> faces = new ArrayList<>();
//    }
//
//    private Model(String filePath)
//    {
//        loadedModels.put(filePath, this);
//        maxIndex++;
//
//        new vimLoader(filePath);
//        generateTriangles();
//
//        for(int i = 0; i < triangles.size() / 3; i++)
//        {
//            floatBuffer.put(triangles.get(i).vertices[0]);
//            floatBuffer.put(triangles.get(i).vertices[1]);
//            floatBuffer.put(triangles.get(i).vertices[2]);
//
//            for(int j = 0; j < 4; j++)
//                floatBuffer.put(0.4f);
//        }
//        floatBuffer.flip();
//    }
//
//    private void generateTriangles()
//    {
//        for(SubModel subModel : subModels)
//        {
//            ArrayList<Face> faces = subModel.faces;
//            ArrayList<Point3D> vertexes = subModel.vertexes;
//
//            for(int i = 0; i < faces.size(); i++)
//            {
//                Face face = faces.get(i);
//                triangles.add(new Triangle(
//                        vertexes.get(face.getOne()),
//                        vertexes.get(face.getTwo()),
//                        vertexes.get(face.getThree())));
//                if(face.getFour() != - 1)
//                    triangles.add(new Triangle(
//                            vertexes.get(face.getOne()),
//                            vertexes.get(face.getFour()),
//                            vertexes.get(face.getThree())));
//
//                triangles.add(new Triangle(
//                        vertexes.get(face.getThree()),
//                        vertexes.get(face.getTwo()),
//                        vertexes.get(face.getOne())));
//                if(face.getFour() != - 1)
//                    triangles.add(new Triangle(
//                            vertexes.get(face.getThree()),
//                            vertexes.get(face.getFour()),
//                            vertexes.get(face.getOne())));
//            }
//        }
//    }
//
//    private class vimLoader
//    {
//        private final byte NONE = 0;
//        private final byte TAG_VERTICES = 1;
//        private final byte TAG_FACES = 2;
//        private String path;
//        private int line = 1;
//
//        private vimLoader(String path)
//        {
//            byte activeTag = 0;
//            this.path = path;
//            SubModel beingLoaded = null;
//
//            for(String s : FileIO.readLines(path))
//            {
//                if(s.equals("}"))
//                    activeTag = NONE;
//                switch(activeTag)
//                {
//                    case (NONE):
//
//                        break;
//                    case (TAG_VERTICES):
//                        beingLoaded.vertexes.add(extractPoints(s));
//                        break;
//                    case (TAG_FACES):
//                        beingLoaded.faces.add(extractFace(s));
//                        break;
//                }
//                switch(s)
//                {
//                    case ("vertices{"):
//                        activeTag = TAG_VERTICES;
//                        beingLoaded = new SubModel();
//                        subModels.add(beingLoaded);
//                        break;
//                    case ("faces{"):
//                        activeTag = TAG_FACES;
//                        break;
//                    case ("}"):
//                        activeTag = NONE;
//                        break;
//                }
//                line++;
//            }
//        }
//
//        private Point3D extractPoints(String line)
//        {
//            String[] divided = line.split(",");
//            if(divided.length != 3)
//                Debug.error(path + " is invalid at" + line + " expected 3 values, found " + divided.length + " on line " + this.line);
//
//            return new Point3D(Double.valueOf(divided[0]), Double.valueOf(divided[1]), Double.valueOf(divided[2]));
//        }
//
//        private Face extractFace(String line)
//        {
//            String[] divided = line.split(",");
//            if(divided.length != 4 && divided.length != 3)
//                Debug.error(path + " is invalid at" + line + " expected 3-4 values, found " + divided.length + " on line " + this.line);
//            if(divided.length == 4)
//                return new Face(Integer.valueOf(divided[0]), Integer.valueOf(divided[1]), Integer.valueOf(divided[2]), Integer.valueOf(divided[3]));
//            return new Face(Integer.valueOf(divided[0]), Integer.valueOf(divided[1]), Integer.valueOf(divided[2]));
//        }
//    }
//
//    public ArrayList<Triangle> getTriangles()
//    {
//        return triangles;
//    }
//
//    public static Model getModel(String path)
//    {
//        return loadedModels.get(path) == null ? loadedModels.get(path) : new Model(path);
//    }
//}
//
//
//
