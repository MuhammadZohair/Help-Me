package com.lunaticaliens.helpme.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Encryption {

    public static String encodePicture(Bitmap bmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bb = bos.toByteArray();
        return Base64.encodeToString(bb, 0);
    }

    public static Bitmap decodePicture(String image) {
        byte[] imageAsBytes = Base64.decode(image.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }
}
