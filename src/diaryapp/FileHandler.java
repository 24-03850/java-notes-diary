package diaryapp;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandler {
    public static void checkFileExistence(String path) {
        try {
            Path file = Paths.get(path);
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
        }
        catch (IOException e) {
            System.out.println("Error file creation!" + e.getMessage());
        }
    }
    
    public static List<String> readAllLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void writeAllLines(String path, List<String> lines) {
        try {
            Files.write(Paths.get(path), lines);
        }
        catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void appendLine(String path, String line) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))){
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }
}
