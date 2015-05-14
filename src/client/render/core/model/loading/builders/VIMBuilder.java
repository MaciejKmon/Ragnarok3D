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

    @Tag public void objectCount(int count)
    {
        subModels = new SubModel[count];
    }
    @Tag public void makeObject(String vertices, String faces)
    {
        //Finish previous model
        if(currentModel != null)
            currentModel.calculate();
        Debug.info("blahhhhhhhhhhhhhhh");
        currentModel = new SubModel(Integer.parseInt(vertices), Integer.parseInt(faces));
        subModels[loadedObjects] = currentModel;

        loadedObjects++;
    }

    @Tag public void addVertex(float x, float y, float z)
    {
        Debug.info("s" + verticesAdded);
        verticesAdded++;
        currentModel.addVertex(x, y, z);
    }
//
    @Tag public void addFace3(int i, int ii, int iii)
    {
        currentModel.addFace(i, ii, iii);
    }

    @Tag public void addFace4(int i, int ii, int iii, int iv)
    {
        currentModel.addFace(i, ii, iii, iv);
    }

    @Tag public void end()
    {
        currentModel.calculate();
    }

    @Tag
    public void addUV()
    {

    }
}