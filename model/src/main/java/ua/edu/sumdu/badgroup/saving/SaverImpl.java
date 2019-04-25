package ua.edu.sumdu.badgroup.saving;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.edu.sumdu.badgroup.entities.Point;

import java.io.*;
import java.util.*;

public class SaverImpl implements Saver {
    @Override
    public Collection<Point> parse(File file) throws IOException {
        return new GsonBuilder().setPrettyPrinting().create().fromJson(read(file)
                , new TypeToken<Collection<Point>>(){}.getType());
    }

    private static String read(File file) throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            byte [] buffer = new byte[bufferedInputStream.available()];
            int bytes;
            if ((bytes = bufferedInputStream.read(buffer)) == 0) {
                throw new IOException("Empty input");
            }
            return new String(buffer);
        }
    }

    @Override
    public void save(Collection<Point> points, File file) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            String gson = new GsonBuilder().setPrettyPrinting().create().toJson(points);
            bufferedOutputStream.write(gson.getBytes());
        }
    }
}
