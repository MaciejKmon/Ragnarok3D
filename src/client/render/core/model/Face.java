package client.render.core.model;

/**
 * Created by Caelum on 9/10/14.
 */
public class Face
{
    public int[] connections = new int[4];

    public Face(int one, int two, int three, int four)
    {
        connections[0] = one;
        connections[1] = two;
        connections[2] = three;
        connections[3] = four;
    }

    public Face(int... connections)
    {
        this.connections = connections;
    }
}
