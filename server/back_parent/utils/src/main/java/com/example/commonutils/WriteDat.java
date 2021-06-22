package com.example.commonutils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriteDat {

    public void writeDat(String content){

        String filename = "/root/project_file/frontstage/book_ratings.csv";

        File file = new File(filename);
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8));
//            writer.newLine();
            writer.write(content);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
