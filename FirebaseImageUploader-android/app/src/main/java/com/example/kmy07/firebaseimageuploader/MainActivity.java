package com.example.kmy07.firebaseimageuploader;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button takePictureButton;
    private ImageView imageView;
    final int CAMERA_PIC_REQUEST = 1337;
    private String filePath = Environment.getExternalStorageDirectory().toString();
    private String fileName = "sampleImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takePictureButton = (Button) findViewById(R.id.takePic);
        imageView = (ImageView) findViewById(R.id.imageview);


        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(image);

            //Code to store the image locally in your device
            StoreImage object = new StoreImage(filePath,fileName,image);
            if(object.storeImage()){
                Toast.makeText(this, "Image Succesfully Saved!\nUploading Image...", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Error in saving Image!", Toast.LENGTH_SHORT).show();
            }

            //Code to upload image to firebase
            ImageUploader uploader = new ImageUploader(filePath,fileName,fileName);
            if(uploader.upload2Firebase()){
                Toast.makeText(this, "Image Succesfully Uploaded!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Error in uploading Image", Toast.LENGTH_SHORT).show();
            }

        }

    }
}

