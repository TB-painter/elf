package ua.edu.sumdu.badgroup.saving;

import ua.edu.sumdu.badgroup.entities.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public interface Saver {
    Collection<Point> parse(File file) throws IOException;
    void save(Collection<Point> points, File file) throws IOException;
}
