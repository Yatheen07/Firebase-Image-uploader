package com.example.kmy07.firebaseimageuploader;

import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StoreImage {

    private String filePath;
    private String fileName;
    private Bitmap image;

    public StoreImage(String filePath,String fileName,Bitmap image){
        this.fileName = fileName;
        this.filePath = filePath;
        this.image = image;
    }

    public boolean storeImage(){
        boolean status = true;

        FileOutputStream out = null;
        File file = new File(filePath, fileName);
        try {
            out = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 100, out);
            //Toast.makeText(this, "Success! Image Succesfully Stored!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            //Toast.makeText(this, "Error Here!"+e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            try {
                if (out != null) {
                    out.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
                status = false;
            }
        }

        return status;
    }


}
