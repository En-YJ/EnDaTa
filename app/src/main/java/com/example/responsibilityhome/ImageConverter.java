package com.example.responsibilityhome;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;


import java.io.ByteArrayOutputStream;

public class ImageConverter {

    public ImageConverter(){

    }

    public static String getImageToString(Bitmap Image){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Bitmap dstBitmap = resize(Image);

        dstBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] imageBytes = stream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return  encodedImage;
    }

    public static Bitmap getImageToBitmap(String encodedImage){

        byte[] decodedByte = Base64.decode(encodedImage, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static Bitmap getReplaceRegexToBitmap(String encodedImage){

        String replaceSlash = encodedImage.replaceAll("\\\\", "");
        String replaceImage = replaceSlash.replaceAll("\\\\n","\n");
        Log.e("replaceImage", replaceImage);

        byte[] decodedByte = Base64.decode(replaceImage, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static Bitmap resize(Bitmap bitmap){
        Bitmap dstBitmap;
        if(bitmap.getWidth() > bitmap.getHeight())
            dstBitmap = Bitmap.createScaledBitmap(bitmap, 300, (bitmap.getHeight()*300)/bitmap.getWidth(), true);
        else
            dstBitmap = Bitmap.createScaledBitmap(bitmap, (300*bitmap.getWidth())/bitmap.getHeight(), 300, true);
        return dstBitmap;
    }
}
