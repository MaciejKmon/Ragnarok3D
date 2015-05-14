package client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Caelum on 10/30/14.
 */
public class Info
{
    private static HashMap<String, Data> dataStores;

    private static class Data
    {
        private String name;
        private Object value;
        private String path;

        private Data(String name, String path, Object value)
        {
            this.name = name;
            this.value = value;
            this.path = path;
        }
    }

    public static void setValue(String name, String path, Object value)
    {
        Data data = new Data(name, path + "/" + name, value);

        dataStores.put(path + "/" + name, data);
    }

    public static Data getData(String path, String name)
    {
        return dataStores.get(path + "/" + name);
    }

    public static ArrayList<Data> getData(String path)
    {
        ArrayList<Data> arrayList = new ArrayList<>();

        for(Data data : dataStores.values())
            arrayList.add(data);

        return arrayList;
    }

    public static List<Data> getDataStores()
    {
        return (List<Data>) dataStores.values();
    }

}
