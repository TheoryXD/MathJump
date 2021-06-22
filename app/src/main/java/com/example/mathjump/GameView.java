package com.example.mathjump;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

//"Bewegung"

public class GameView extends SurfaceView implements Runnable{


    public GameView(Context context){
        super(context);
    }


    private Thread thread;
    private boolean isPlaying;
    private int screenX, screenY;
    private Background background1_, background2_;
    private float screenratioX, screenratioY;
    private Paint paint;
    private Guy guy_;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;
        this.screenratioY = 1920f / screenY;
        this.screenratioX = 1080f / screenX;

        background1_ = new Background(screenX, screenY, getResources());
        background2_ = new Background(screenX, screenY, getResources());

        background2_.y = -screenY;

        guy_ = new Guy(screenY, getResources());

        this.paint = new Paint();
    }

    @Override
    public void run() {

        while(isPlaying) {

            update();
            draw();
            sleep();

        }

    }


    private void update() {

        background1_.y += 5; //* screenratioY;
        background2_.y += 5; //* screenratioY;

        if(background1_.y - background1_.background.getHeight() > 0  ) {
            background1_.y = -screenY;
        }

        if(background2_.y - background2_.background.getHeight() > 0  ) {
            background2_.y = -screenY;
        }

    }

    private void draw() {

        //surfaceView Objekt erfolgreich initialisiert
        if(getHolder().getSurface().isValid()){

            //returns the current Canvas that is displayed on the screen
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1_.background, background1_.x, background1_.y, paint);
            canvas.drawBitmap(background2_.background, background2_.x, background2_.y, paint);

            canvas.drawBitmap(guy_.getGuy(), guy_.x, guy_.y , paint);

            getHolder().unlockCanvasAndPost(canvas);
        }








    }

    private void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
