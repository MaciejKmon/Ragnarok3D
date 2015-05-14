package client.render.core.model;

import client.util.StringOperations;

/**
 * Created by Caelum on 9/9/14.
 */
public class Vert
{
    private double x, y, z, w;

    public Vert(double x, double y, double z, double w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vert(String x, String y, String z)
    {
        this.x = (Double) StringOperations.stringToType(Double.class, x);
        this.y = (Double) StringOperations.stringToType(Double.class, y);
        this.z = (Double) StringOperations.stringToType(Double.class, z);
    }


    public double getW()
    {
        return w;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }


    @Override public String toString()
    {
        return "Vert{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
