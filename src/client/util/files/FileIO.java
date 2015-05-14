package client.util.files;

import client.util.StringOperations;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by GAYLUM on 8/8/14.
 */
//Todo: needs rewriting
public class FileIO
{
    public static String[] readLines(String sFileName)
    {
        ArrayList<String> lines = new ArrayList<>();

        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(sFileName));

            String line;
            while((line = br.readLine()) != null)
            {
                lines.add(line);
            }

            br.close();
        }
        catch(Exception e)
        {
            // redundant
            //return (String[])lines.toArray();
        }
        finally
        {
            try
            {
                if(br != null)
                    br.close();
            }
            catch(Exception e){}
        }

        return StringOperations.convertList(lines);
    }

    //
    public static String readText(String fileName)
    {
        System.out.println("Reading " + fileName);

        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(fileName));
        }
        catch(FileNotFoundException e1)
        {
            return "0";
        }
        try
        {
            StringBuilder sb = new StringBuilder();
            String line;

            line = br.readLine();

            while(line != null)
            {
                sb.append(line + "\n");
                line = br.readLine();
            }
            br.close();
            return sb.toString();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            try
            {
                br.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        return "Could not read " + fileName;

    }

    private static long written = 0;

    public static void writeZip(String path, String names, byte[][] data)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(path);
            ZipOutputStream zos = new ZipOutputStream(fos);
            System.out.print("length" + data.length);

            for(byte[] aData : data)
            {
                ZipEntry ze = new ZipEntry(String.valueOf(names + written + ".txt"));
                zos.putNextEntry(ze);
                zos.write(aData);
                zos.closeEntry();

                written++;
            }
            zos.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void writeZip(String path, BufferedImage... bi)
    {
        for(BufferedImage byteImage : bi)
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
        }
    }

    public static void writeText(String path, String name, Object text)
    {
        FileOutputStream fos = null;
        new File(path).mkdirs();
        try
        {
            fos = new FileOutputStream(path + "\\" + name);
            fos.write(String.valueOf(text).getBytes());
            fos.flush();
            fos.close();
            System.out.println();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
