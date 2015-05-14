package client.world.entities;


import client.render.core.VBOO;
import client.render.core.model.loading.TagReader;
import client.util.Debug;

/**
 * Created by Caelum on 1/30/15.
 */
public class Entity
{
    int x;
    int y;
    int z;
    public VBOO vboos[];

    public Entity(int x, int y, int z, String model)
    {
        this.x = x;
        this.y = y;
        this.z = z;

//        this.model = new Model("test.vim");
    }

    public Entity(int x, int y, int z, TagReader model)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        vboos = new VBOO[model.subModels.length];

        TagReader.SubModel[] subModels = model.subModels;
        for(int i = 0; i < subModels.length; i++)
        {
            TagReader.SubModel subModel = subModels[i];
            vboos[i] = new VBOO(subModel.floatBuffer.duplicate());
            Debug.info("d" + vboos[i] == null);
        }
    }


}
