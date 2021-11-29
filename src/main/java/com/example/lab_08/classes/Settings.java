package com.example.lab_08.classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;

public class Settings {
    private int carWarehouseSize;
    private int engineWarehouseSize;
    private int bodyWarehouseSize;
    private int accessoryWarehouseSize;
    private int supplierCount;
    private int dealerCount;
    private final String filepath = "settings.config";

    public Settings() {

    }

    public static Settings deserialize() {
        final String filepath = "settings.config";
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String content = readFile(filepath);
        return gson.fromJson(content, Settings.class);
    }

    public static void serialize() {
        Settings s = new Settings();
        s.carWarehouseSize = 5;
        s.engineWarehouseSize = 5;
        s.bodyWarehouseSize = 5;
        s.accessoryWarehouseSize = 5;
        s.supplierCount = 3;
        s.dealerCount = 3;

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        writeToFile(gson.toJson(s), s.getFilepath());
    }

    private static void writeToFile(String content, String filePath) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String filepath) {
        String fileContent = "";
        FileReader fin = null;
        Scanner scr = null;

        try {
            fin = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scr = new Scanner(fin);

        while (scr.hasNextLine()) {
            fileContent += scr.nextLine() + "\n";
        }

        scr.close();
        return fileContent;
    }

    public int getCarWarehouseSize() {
        return carWarehouseSize;
    }

    public int getEngineWarehouseSize() {
        return engineWarehouseSize;
    }

    public int getBodyWarehouseSize() {
        return bodyWarehouseSize;
    }

    public int getAccessoryWarehouseSize() {
        return accessoryWarehouseSize;
    }

    public int getSupplierCount() {
        return supplierCount;
    }

    public int getDealerCount() {
        return dealerCount;
    }

    public String getFilepath() {
        return filepath;
    }
}
