package com.connectricity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Toolkit {
    public static String DIRETORIO = System.getProperty("user.dir") + "/src/connectricity/maps_folder/"; /*+ "/src/connectricity/maps_folder/"; */

    private BufferedReader retrieveFile(String fileName){
        BufferedReader mapFile = null;
        try {
            mapFile = new BufferedReader(new FileReader(fileName));
        } catch(IOException erro){
            erro.printStackTrace();
        }
        return mapFile;
    }

    public String[][] getMap(int mapID){
        Vector<String[]> v = new Vector<String[]>();
        BufferedReader mapFile = retrieveFile(DIRETORIO + Integer.toString(mapID) + ".csv");
        try {
            String line = mapFile.readLine();
            while (line != null) {
                String ln[]  = line.split(",");
                v.add(ln);
                line = mapFile.readLine();
            }
            mapFile.close();
        } catch (Exception erro) {
            erro.printStackTrace();
        }
        return (String[][])v.toArray(new String[v.size()][]);
    }
}
