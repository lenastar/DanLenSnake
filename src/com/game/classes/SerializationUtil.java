package com.game.classes;

import com.game.classes.exceptions.GameSerializableException;

import java.io.*;


public class SerializationUtil{
    public static void save (String path, Object obj) {
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(obj);
            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Object get(String path) throws GameSerializableException {
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new GameSerializableException();
        }
    }
}

