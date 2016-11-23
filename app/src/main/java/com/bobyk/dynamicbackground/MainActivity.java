package com.bobyk.dynamicbackground;

import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    HorizontalScrollView scrollView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        scrollView = (HorizontalScrollView) findViewById(R.id.scroll_view);
//        scrollView.isSmoothScrollingEnabled();
//        init();

//        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
//        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);
//
//        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.setDuration(10000L);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                final float progress = (float) animation.getAnimatedValue();
//                final float width = backgroundOne.getWidth();
//                final float translationX = width * progress;
//                backgroundOne.setTranslationX(translationX);
//                backgroundTwo.setTranslationX(translationX - width);
//            }
//        });
//        animator.start();

        final ImageView bg = (ImageView) findViewById(R.id.bg);
        final Matrix matrix = new Matrix();
        final ValueAnimator animator = ValueAnimator.ofFloat(0f, -700.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(40000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                final float progress = (float) animator.getAnimatedValue();
                System.out.println("progress " + progress);
                matrix.reset();
                matrix.postScale((float)0.75, (float)0.75);
                matrix.postTranslate(progress, 0);
                bg.setImageMatrix(matrix);
            }
        });
        animator.start();
//        bg.setImageMatrix(matrix);
//        matrix.postScale((float)0.5, (float)0.5);
//        for (int i = 0; i < 100; i++) {
//            matrix.postTranslate(i, -100);
//            bg.setImageMatrix(matrix);
//        }
//        matrix.postTranslate(0, -100);
//        bg.setImageMatrix(matrix);

//        matrix.postScale((float)0.5, (float)0.5);
//        matrix.postTranslate(-700, -100);
//        bg.setImageMatrix(matrix);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, LoginFragment.newInstance());
        ft.addToBackStack(null);
        ft.commit();
    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(scrollView.getScrollX() + 10, scrollView.getScrollY());
                handler.postDelayed(this, 50);
            }
        }).start();

    }
}
