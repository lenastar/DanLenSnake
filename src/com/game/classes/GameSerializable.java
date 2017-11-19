package com.game.classes;

import com.game.classes.exceptions.GameSerializableException;

import java.io.*;

public class GameSerializable<TClass> implements Serializable{
    public void save(String path) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(this);
        out.close();
    }

    public static <TClass> TClass get(String path) throws GameSerializableException, ClassNotFoundException {
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            return (TClass) in.readObject();
        } catch (IOException e) {
            throw new GameSerializableException();
        }
    }
}
