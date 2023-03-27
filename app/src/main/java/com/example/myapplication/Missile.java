package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Missile extends GameObject {
    private final int score;
    private int speed;
    private final Random rand = new Random();
    private final Animation animation = new Animation();
    private final Bitmap spritesheet;

    public Missile(Bitmap res, int x, int y, int w, int h, int s, int numFrames) {
        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;

        speed = 7 + (int) (rand.nextDouble() * score / 30);

        //cap missile speed
        if (speed > 40) speed = 40;

        Bitmap[] image = new Bitmap[numFrames];

        spritesheet = res;

        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(spritesheet, 320 - width * (i + 1), 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(100 - speed);

    }

    public void update() {
        x -= speed;
        animation.update();
    }

    public void draw(Canvas canvas) {
        try {
            canvas.drawBitmap(animation.getImage(), x, y, null);
        } catch (Exception e) {
        }
    }

    @Override
    public int getWidth() {
        //offset slightly for more realistic collision detection
        return width - 10;
    }

}