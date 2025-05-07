package ua.edu.nuos.lab11_java.main.repository;

import java.io.*;
import java.util.List;

public interface Repository<T> {
    void outputList(List<T> t, File file);
    void outputList(List<T> t, String fileName);

    List<T> readList(File file);
    List<T> readList(String fileName);
}