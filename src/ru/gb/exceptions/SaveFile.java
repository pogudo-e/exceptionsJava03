package ru.gb.exceptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFile {
    private String fileName;

    public SaveFile() {

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean fileExist() {
        File file = new File(this.fileName + ".txt");

        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void saveNewFile(String text) {
        String fileName = this.fileName + ".txt";
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(text);
            writer.append('\n');

            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public void saveOldFile(String text) {
        String fileName = this.fileName + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(text);
            writer.append('\n');

            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
