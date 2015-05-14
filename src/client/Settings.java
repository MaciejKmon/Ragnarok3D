package client;

/**
 * Created by Caelum on 6/18/14.
 */
public class Settings
{
    //TODO It would probably be easier to re-make this then spend 20 seconds finding what's wrong.
    public enum Strings
    {
        vertexShaderPath("Vertex shader", "defaultVertex.glsl", "hur durr"),
        fragmentShaderPath("Fragment shader", "defaultFragment.glsl", "hur durr");
        ;

        String name;
        String value;
        String description;

        Strings(String name, String value, String description)
        {
            this.name = name;
            this.value = value;
            this.description = description;
        }

        public String getValue()
        {
            return value;
        }
    }

    public enum Integers
    {
        maxFPS("Max FPS", 90, "Maximum Frames Per Second displayed on the screen. \nthis should be ~15 higher than your monitor or 75 if you're not sure."),
        chunkSize("Chunk size", 16, "Size of chunks in metres, the smaller chunks are the less CPU-\n" +
                "used, but when a entity transfers to another chunk it produces lag\n" +
                "So smaller will lag less, but if things move a lot than bigger is better. "),
        defaultWindowWidth("Default window width", 1024, "Starting window width"),
        defaultWindowHeight("Default window height", 768, "Starting window height"),


        connectionAttempts("Max connection attempts", 16, "Maximum attempts to establish a connection before giving up");


        String name;
        int value;
        String description;

        Integers(String name, int defaultValue, String description)
        {

            this.name = name;
            this.value = defaultValue;
            this.description = description;

        }


        public void setName(String name)
        {
            this.name = name;
        }

        public int getValue()
        {
            return value;
        }
    }
}
