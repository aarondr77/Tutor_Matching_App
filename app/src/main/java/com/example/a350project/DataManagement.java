package com.example.a350project;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class DataManagement {


    public static void writeComplaint(Context context, ComplaintsObject newComplaint) {

        /*private String complaintsFile = context.getFilesDir().getPath().toString() + "/ComplaintsFile.txt";

        private File complaints = new File(complaintsFile);

        if (!complaints.exists()) {
            try {complaints.createNewFile();}
            catch (IOException e) {
                Log.d("Create File", e.toString());
            }

        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(complaintsFile));
            writer.write(newComplaint.getContent() + "," + newComplaint.getSubmitter() + "," + newComplaint.getStatus() + "," + newComplaint.getTarget());
            writer.close();
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        } */

        String FILENAME = "hello_file";
        String string = "hello world!";

        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
