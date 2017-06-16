package com.kirak.util;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Kir on 14.06.2017.
 */
public class DbMockDataReducer {

    public static void main(String[] args) throws IOException {

        reduceCountries();
    }


    public static void reduceCountries(){

        File inFile = new File("D:\\cities\\COUNTRIES_REDUCED.txt");
        int counter = 0;

        Set<String> countries = new TreeSet<String>();

        try (FileReader outStream = new FileReader("D:\\cities\\COUNTRIES_BIG_CITIES.txt");
             BufferedReader reader = new BufferedReader(outStream);
             FileWriter inStream = new FileWriter(inFile);
             BufferedWriter writer = new BufferedWriter(inStream)) {

            String line;
            while ((line = reader.readLine()) != null) {
                if(!countries.contains(line)) {
                    countries.add(line);
                }
            }

            for(String s : countries){
                writer.write(s);
                writer.newLine();
            }

        }catch (IOException e){
            System.out.println(e.getCause().toString());
        }

        System.out.println(counter);
    }



    //option for table with large amount of data(more than 2 Mb)
    public static void reduceByRuCountries(){

        File inFile = new File("D:\\cities\\RU_REDUCED.txt");

        int stringsCount = 0;

        try (FileReader outStream = new FileReader("D:\\cities\\BIG.txt");
             BufferedReader reader = new BufferedReader(outStream);
             FileWriter inStream = new FileWriter(inFile);
             BufferedWriter writer = new BufferedWriter(inStream)) {

            String line;
            while ((line = reader.readLine()) != null) {

                String code = line.substring(0, 2);
                if(code.equals("UP")||code.equals("RS")||code.equals("BO")){
                    writer.write(line);
                    writer.newLine();
                    stringsCount++;
                }
            }
        }catch (IOException e){
            System.out.println(e.getCause().toString());
        }
        System.out.println(stringsCount);
    }
}
