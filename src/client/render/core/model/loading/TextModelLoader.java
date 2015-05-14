package client.render.core.model.loading;

import client.util.Debug;
import client.util.StringOperations;
import client.util.files.FileIO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
*
 * Model Object that contains an interleaved buffer, components(hinges etc.), functions for manipulation<br></br>
 *
 * Server plans: Engine<br></br><br></br>
 *
 * Models will be either text or binary based, with two model loaders to extract data from either. These model loaders<br></br>
 * simply call functions by name from a provided model builder(Through <i>format</i> argument)
 *
*/

public class TextModelLoader
{
    public static final TextModelLoader instance = new TextModelLoader();

    //Each TagReader method list should only be generated once.
    private static HashMap<Class<?extends TagReader>, HashMap<String, Method>> methodMaps = new HashMap<>();
    //Each model should only be loaded once, indexed by name.
    private static HashMap<String, TagReader> loadedModels = new HashMap<>();


    public TagReader load(String path, Class<?extends TagReader> readerType)
    {
        //Don't want to load it twice
        TagReader model = loadedModels.get(path);
        if(model == null)
        {
            try
            {
                model = readerType.newInstance();
            }
            catch(InstantiationException e)
            {
                e.printStackTrace();
            }
            catch(IllegalAccessException e)
            {
                e.printStackTrace();
            }
            //Maybe this model type has been loaded before but the model hasn't?
            HashMap<String, Method> methodMap = methodMaps.get(readerType);
            if(methodMap == null)
            {
                methodMap = loadTagReader(new HashMap<String, Method>(), readerType);
            }
            for(String s : FileIO.readLines(path))
            {
                readLine(methodMap, s, model);
            }
            loadedModels.put(path, model);
        }
        return model;
    }
//Having combat consist of lots of attacks, counters, counter-counters etc. will make it more like real life and maybe be as exciting?
    /**
     *  Reading 'n' stuff.
     * @param methodMap
     * @param line
     * @param reader
     */
    private void readLine(HashMap<String, Method> methodMap, String line, TagReader reader)
    {
        if(line.startsWith("//"))
            return;
        StringBuilder commandBuilder = new StringBuilder();
        char[] lineChars = line.toCharArray();

        for(int i = 0; i < 32; i++)
        {
            if(lineChars.length == i)
            {
                return;
            }
            if(lineChars[i] == '(')
                readCommand(methodMap, commandBuilder.toString(), i + 1, lineChars, reader);
            //Build command name excluding (
            else
                commandBuilder.append(lineChars[i]);
        }
    }
    //TODO: Neaten and document
    //TODO: support type...name
    //TODO: Slay dragons
    /*
            *DANGER* Dragons be here
     */
    /*                    ^    ^
                                               / \  //\
                                 |\___/|      /   \//  .\
                                 /O  O  \__  /    //  | \ \
                                /     /  \/_/
                                 //   |  \  \
                                @___@'    \/_   //    |   \   \
                                   |       \/_ //     |    \    \
                                   |        \///      |     \     \
                                  _|_ /   )  //       |      \     _\
                                 '/,_ _ _/  ( ; -.    |    _ _\.-~        .-~~~^-.
                                 ,-{        _      `-.|.-~-.           .~         `.
                                  '/\      /                 ~-. _ .-~      .-~^-.  \
                                     `.   {            }                   /      \  \
                                   .----~-.\        \-'                 .~         \  `. \^-.
                                  ///.----..>    c   \             _ -~             `.  ^-`   ^-_
                                    ///-._ _ _ _ _ _ _}^ - - - - ~                     ~--,   .-~
    */
    private void readCommand(HashMap<String, Method> methodMap, String command, int startingIndex, char[] lineChars, TagReader reader)
    {
        //find parameter count, get method with same name and function count from associated tagReader, get their Types, convert parameter Strings to correct types and call.
        Method method = methodMap.get(command);

        if(method == null)
        {
            Debug.error(command + " Is not found in the provided Tag Reader. " + String.valueOf(lineChars));
            return;
        }
        Type[] parameterTypes = method.getGenericParameterTypes();
        //Ignore parameters if the TagReader method of the same name has no arguments
        if(parameterTypes.length == 0)
        {
            try
            {
                method.invoke(reader, null);

            }
            catch(IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch(InvocationTargetException e)
            {
                e.printStackTrace();
            }
            return;
        }
        //
        String[] parameters = new String[parameterTypes.length];
        int parameterCount = 0;

        StringBuilder parameter = new StringBuilder();

        for(int j = startingIndex;; j++)
        {
            if(j >= lineChars.length)
                return;

            if(lineChars[j] != ')' && lineChars[j] != '|' && lineChars[j] != ',')
                parameter.append(lineChars[j]);
            else if(lineChars[j] == ',')
            {
                //Since it's the end of the parameter,
                parameters[parameterCount] = parameter.toString();
                parameterCount++;
                parameter = new StringBuilder();


            }
            else if(lineChars[j] == '|') //Know all the data now, process it.
            {
                //Add final parameter since there is no ',' for this one.
                parameters[parameterCount] = parameter.toString();
                parameterCount++;

                Object[] typedParamters = new Object[parameterTypes.length];

                for(int i = 0; i < parameters.length; i++)
                {
                    if(parameters[i] != null)
                        typedParamters[i] = StringOperations.stringToType(parameterTypes[i], parameters[i]);
                }
                try
                {
                    method.invoke(reader, typedParamters);
                }
                catch(IllegalAccessException e)
                {
                    e.printStackTrace();
                }
                catch(InvocationTargetException e)
                {
                    e.printStackTrace();
                }

                //Run the command again from where we left off(Skip the '|') and re-use data we already know (Command, first j lineChars, methodMap)
                if(j < lineChars.length - 1 && lineChars[j + 1] == '$')
                {
                    command = command.substring(0, command.length() - 1);
                    command += lineChars[j + 2];
                    j += 2;
                }

                readCommand(methodMap, command, j + 1, lineChars,reader);
                return;
            }
            else if(lineChars[j] == ')')
            {
                parameters[parameterCount] = parameter.toString();
                parameterCount++;

                Object[] typedParamters = new Object[parameterTypes.length];

                for(int i = 0; i < parameters.length; i++)
                {
                    if(parameters[i] != null)
                        typedParamters[i] = StringOperations.stringToType(parameterTypes[i], parameters[i]);
                }
                try
                {
                    method.invoke(reader, typedParamters);
                }
                catch(IllegalAccessException e)
                {
                    e.printStackTrace();
                    int d = 0;
                }
                catch(InvocationTargetException e)
                {
                    e.printStackTrace();
                    Debug.error("Failed to print " + command);
                }
            }

            else //Got the method name and parameters, time for a call.
                break;
        }
    }

    private HashMap<String, Method> loadTagReader(HashMap<String, Method> readerMethodMap, Class<?extends TagReader> tagReader)
    {
        Class<?> klass = tagReader;
        //To capture all superclasses' methods too.
        while(klass != Object.class)
        {
            List<Method> allMethods = new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));

            for(final Method method : allMethods)
            {
                Tag tagAnnotation = method.getAnnotation(Tag.class);

                //if tag Annotation isn't null, it must be a instanceof Tag.class(AKA annotation)
                if(tagAnnotation != null)
                {
                    readerMethodMap.put(method.getName(), method);
                }
            }
            klass = klass.getSuperclass();
        }
        return readerMethodMap;
    }
}



