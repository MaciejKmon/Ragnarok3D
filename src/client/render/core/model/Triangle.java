package client.render.core.model;

import javafx.geometry.Point3D;

public class Triangle
{
    public final float[][] vertices;
    public final float[][] colors;

    public Triangle(Point3D p1, Point3D p2, Point3D p3)
    {
        vertices = new float[3][3];

        vertices[0] = point3DToFloatArray(p1);
        vertices[1] = point3DToFloatArray(p2);
        vertices[2] = point3DToFloatArray(p3);


        colors = new float[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                colors[i][j] = 0.5f + (float) Math.random() / 10;
    }

    public void randomize()
    {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                colors[i][j] = 0.5f + (float) Math.random() / 10;
    }

    /**
     * @param point3D
     * @return
     */
    public static float[] point3DToFloatArray(Point3D point3D)
    {
        return new float[]{(float) point3D.getX(), (float) point3D.getY(), (float) point3D.getZ()};
    }

    private static float[] createRandomPoint(float[] p, float dist)
    {
        return new float[]
                {
                        (float) ((Math.random() - 0.5) * dist + p[0]),
                        (float) ((Math.random() - 0.5) * dist + p[1]),
                        (float) ((Math.random() - 0.5) * dist + p[2])
                };
    }
}
