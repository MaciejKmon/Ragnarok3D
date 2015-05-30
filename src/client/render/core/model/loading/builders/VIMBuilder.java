//package client.render.core.model.loading.builders;
//
//import client.render.core.model.loading.Tag;
//import client.render.core.model.loading.TagReader;
//import client.util.Debug;
//
///**
//* Created by Caelum on 3/8/15.
//*/
//public class VIMBuilder extends TagReader
//{
//    private SubModel currentModel;
//    private int loadedObjects;
//
//    private int verticesAdded = 0;
//
//    @Tag public void objectCount(int count)
//    {
//        subModels = new SubModel[count];
//    }
//    @Tag public void makeObject(String vertices, String faces)
//    {
//        //Finish previous model
//        if(currentModel != null)
//            currentModel.calculate();
//        Debug.info("blahhhhhhhhhhhhhhh");
//        currentModel = new SubModel(Integer.parseInt(vertices), Integer.parseInt(faces));
//        subModels[loadedObjects] = currentModel;
//
//        loadedObjects++;
//    }
//
//    @Tag public void addVertex(float x, float y, float z)
//    {
//        Debug.info("s" + verticesAdded);
//        verticesAdded++;
//        currentModel.addVertex(x, y, z);
//    }
////
//    @Tag public void addFace1(int i, int ii, int iii, float u0, float v0, float u1, float v1, float u2, float v2)
//    {
//        currentModel.addFace(i, ii, iii);
//    }
//
//    @Tag public void addFace2(int i, int ii, int iii, int iv, float u0, float v0, float u1, float v1, float u2, float v2,
//                                              float u3, float v3, float u4, float v4, float u5, float v5)
//    {
//        currentModel.addFace(i, ii, iii, iv);
//    }
//
//    @Tag public void end()
//    {
//        currentModel.calculate();
//    }
//
//    @Tag
//    public void addUV()
//    {
//
//    }
//    info: [-1.0, -1.0, -1.0]
//    info: [-1.0, -1.0, 1.0]
//    info: [-1.0, 1.0, -1.0]
//    info: [-1.0, 1.0, 1.0]
//    info: [1.0, -1.0, -1.0]
//    info: [1.0, -1.0, 1.0]
//    info: [1.0, 1.0, -1.0]
//    info: [1.0, 1.0, 1.0]


//}
//
    package client.render.core.model.loading.builders;

import client.render.core.model.loading.Tag;
import client.render.core.model.loading.TagReader;
import client.util.Debug;

    /**
     * Created by Caelum on 3/8/15.
     */
    public class VIMBuilder extends TagReader
    {
        private SubModel currentModel;
        private int loadedObjects;

        private int verticesAdded = 0;

        //Second dimension 3 floats for X Y and Z of vertex.
        float[][] vertices = {
                {-1f, -1f, 0.5773f},
                {0f, -1f, -1.15475f},
                {1f, -1f, 0.5773f},
                {0f, 1f, 0f, 0.5f}
        };
        float[][] UVs = {
//                {0.0f, 0.666667f},
//                {1.0f, 0.0f},
//                {0.666667f, 0.0f},
//                {0.666667f, 0.333333f},
//                {0.666667f, 0.333333f},
//                {1.0f, 0.333333f},
//                {1.0f, 0.333333f},
//                {1.0f, 0.0f},
//                {0.333333f, 0.333333f},
//                {0.333333f, 0.666667f},
//                {0.666667f, 0.666667f},
//                {0.666667f, 0.666667f},
//                {0.666667f, 0.666667f},
//                {0.666667f, 0.333333f},
//                {0.333333f, 0.333333f},
//                {0.0f, 0.333333f},
//                {0.333333f, 0.333333f},
//                {0.333333f, 0.0f},
//                {0.333333f, 0.0f},
//                {0.333333f, 0.0f},
//                {0.0f, 0.0f},
//                {0.0f, 0.333333f},
//                {0.0f, 0.666667f},
//                {0.333333f, 0.666667f},
//                {0.333333f, 0.666667f},
//                {0.333333f, 0.333333f},
//                {0.333333f, 0.333333f},
//                {0.0f, 0.333333f},
//                {0.0f, 0.666667f},
//                {0.333333f, 0.333333f},
//                {0.333333f, 0.333333f},
//                {0.666667f, 0.333333f},
//                {0.666667f, 0.0f},
//                {0.666667f, 0.0f},
//                {0.333333f, 0.0f},
//                {0.333333f, 0.333333f}
                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},


                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},


                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},




                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},



                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},

                {0f, 0f},
                {0.5f, 0f},
                {1f, 0f},




        };
        //Second dimension 3-4 floats for vertex index
        int[][] faces = {
                {1, 4, 2, 0},
                {2, 4, 3, 0},
                {3, 4, 1, 0},
                {1, 2, 3, 0}
        };

        int faceCount = 0;
        int vertexCount = 0;
        int UVCount = 0;


        @Tag public void objectCount(int count)
        {
            subModels = new SubModel[count];
        }
        @Tag public void makeObject(String vertices, String faces)
        {
            //Finish previous model
            if(currentModel != null)
                calculate();
            Debug.info("blahhhhhhhhhhhhhhh");


            int verticeLength = Integer.parseInt(vertices);
            int faceLength = Integer.parseInt(faces);

            currentModel = new SubModel(verticeLength, 80);
            this.vertices = new float[verticeLength][3];
            this.faces = new int[faceLength][4];

            this.UVs = new float[faceLength * 6][2];
            subModels[loadedObjects] = currentModel;

            loadedObjects++;
        }

        @Tag public void addVertex(float x, float y, float z)
        {
            vertices[vertexCount][0] = x;
            vertices[vertexCount][1] = y;
            vertices[vertexCount][2] = z;

            vertexCount++;
        }

        @Tag public void addFace1(int i, int ii, int iii, float u0, float v0, float u1, float v1, float u2, float v2)
        {
            faces[faceCount][0] = i;
            faces[faceCount][1] = ii;
            faces[faceCount][2] = iii;

            UVs[UVCount + 0][0] = u0;
            UVs[UVCount + 0][1] = v0;

            UVs[UVCount + 1][0] = u1;
            UVs[UVCount + 1][1] = v1;

            UVs[UVCount + 2 ][0] = u2;
            UVs[UVCount + 2][1] = v2;
            UVCount += 3;
            faceCount++;

        }

        @Tag public void addFace2(int i, int ii, int iii, int iv, float u0, float v0, float u1, float v1, float u2, float v2,
                                  float u3, float v3, float u4, float v4, float u5, float v5)
        {
            faces[faceCount][0] = i;
            faces[faceCount][1] = ii;
            faces[faceCount][2] = iii;
            faces[faceCount][3] = iv;

            UVs[UVCount + 0][0] = u0;
            UVs[UVCount + 0][1] = v0;

            UVs[UVCount + 1][0] = u1;
            UVs[UVCount + 1][1] = v1;

            UVs[UVCount + 2][0] = u2;
            UVs[UVCount + 2][1] = v2;



            UVs[UVCount + 3][0] = u3;
            UVs[UVCount + 3][1] = v3;

            UVs[UVCount + 4][0] = u4;
            UVs[UVCount + 4][1] = v4;

            UVs[UVCount + 5][0] = u5;
            UVs[UVCount + 5][1] = v5;

            UVCount += 6;
            faceCount++;

            Debug.info(faceCount);
        }

        @Tag public void end()
        {
//            for(int i = 0; i < UVs.length; i++)
//            {
//                Debug.info("UV: {" + UVs[i][0] + "f, " + UVs[i][1] + "f}");
//            }
           // [[0.33333298563957214,0.33333298563957214],[0.33333298563957214,0.0],[0.6666669845581055,0.0],[0.6666669845581055,0.0],[0.6666669845581055,0.33333298563957214],[0.33333298563957214,0.33333298563957214],[0.33333298563957214,0.33333298563957214],[0.0,0.6666669845581055],[0.0,0.33333298563957214],[0.33333298563957214,0.33333298563957214],[0.33333298563957214,0.33333298563957214],[0.33333298563957214,0.6666669845581055],[0.33333298563957214,0.6666669845581055],[0.0,0.6666669845581055],[0.0,0.33333298563957214],[0.0,0.0],[0.33333298563957214,0.0],[0.33333298563957214,0.0],[0.33333298563957214,0.0],[0.33333298563957214,0.33333298563957214],[0.0,0.33333298563957214],[0.33333298563957214,0.33333298563957214],[0.6666669845581055,0.33333298563957214],[0.6666669845581055,0.6666669845581055],[0.6666669845581055,0.6666669845581055],[0.6666669845581055,0.6666669845581055],[0.33333298563957214,0.6666669845581055],[0.33333298563957214,0.33333298563957214],[1.0,0.0],[1.0,0.33333298563957214],[1.0,0.33333298563957214],[0.6666669845581055,0.33333298563957214],[0.6666669845581055,0.33333298563957214],[0.6666669845581055,0.0],[1.0,0.0],[0.0,0.6666669845581055]]
//            UVs = Maths.reverseArray(UVs);
            calculate();
        }

        //Clockwise means that the three vertices, in order, rotate clockwise around the triangle's center.
        //Counter-clockwise means that the three vertices, in order, rotate counter-clockwise around the triangle's center.
        //Add 3 vertices with 3 floats each to draw triangle, then 4 floats for colour.

        //Triangles are defined in clockwise manner.
        public void calculate()
        {
            //Iterate through faces and add the vertices their 4 values point to.
            for(int i = 0; i < faces.length; i++)
            {
                int[] face = faces[i];

                if(face[3] != 0)
                {//Must be a quadrilateral, draw 2 triangles.
                    currentModel.floatBuffer.put(vertices[face[2] - 1]);
                    currentModel.addColour();
                    currentModel.floatBuffer.put(UVs[i + 2]);

                    currentModel.floatBuffer.put(vertices[face[3] - 1]);
                    currentModel.addColour();
                    currentModel.floatBuffer.put(UVs[i + 3]);

                    currentModel.floatBuffer.put(vertices[face[0] - 1]);
                    //4 colours are expected after 3 triangles.
                    currentModel.addColour();
                    currentModel.floatBuffer.put(UVs[i + 0]);
                }
                //Either a standard triangle, or the second bit to the quadrilateral.
                try
                {
                    currentModel.floatBuffer.put(vertices[face[0] - 1]);
                    currentModel.addColour();
                    currentModel.floatBuffer.put(UVs[i + 0]);
                    //1 is top-right.
                    currentModel.floatBuffer.put(vertices[(face[1] - 1)]);
                    currentModel.addColour();
                    currentModel.floatBuffer.put(UVs[i + 1]);
                    //2 is top left
                    currentModel.floatBuffer.put(vertices[(face[2] - 1)]);
                    currentModel.addColour();
                    currentModel.floatBuffer.put(UVs[i + 2]);
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    e.printStackTrace();
                }

            }
            currentModel.floatBuffer.flip();
        }

        @Tag
        public void addUV()
        {

        }
    }