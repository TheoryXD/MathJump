package com.example.mathjump;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Guy {

    int width_, height_, x, y;
    Bitmap guy1_;

    Guy(int screenY, Resources res){
        guy1_ = BitmapFactory.decodeResource(res, R.drawable.guy2);

        width_  = guy1_.getWidth();
        height_ = guy1_.getHeight();

        guy1_ = Bitmap.createScaledBitmap(guy1_,width_,height_,false);

        y = screenY / 2;
        x = 32;
    }


    Bitmap getGuy () {
        return this.guy1_;
    }

}
