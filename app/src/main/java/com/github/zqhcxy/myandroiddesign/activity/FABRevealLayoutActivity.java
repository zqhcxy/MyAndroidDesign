package com.github.zqhcxy.myandroiddesign.activity;

import android.animation.ObjectAnimator;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.zqhcxy.myandroiddesign.R;
import com.truizlop.fabreveallayout.FABRevealLayout;
import com.truizlop.fabreveallayout.OnRevealChangeListener;

public class FABRevealLayoutActivity extends AppCompatActivity {

    private static final int FIRSTVIEW=1;
    private static final int SECONDVIEW=2;

    private FABRevealLayout fab_rly;
    private FloatingActionButton fab_play;
    private TextView tv_title;//主界面的标题
    private TextView tv_content;//主界面的内容
    private ImageView previous;
    private ImageView stop;
    private ImageView next;
    private SeekBar song_progress_bar;
    private TextView song_title_text;//第二界面的标题
    private ImageView cover;//封面

    private int whitchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabreveal_layout);
        findViews();
        whitchView=FIRSTVIEW;
        configureFABReveal();
    }

    private void findViews() {
        fab_rly = (FABRevealLayout) findViewById(R.id.fab_rly);
        fab_play = (FloatingActionButton) findViewById(R.id.fab_play);

        song_progress_bar = (SeekBar) findViewById(R.id.song_progress_bar);
        styleSeekbar(song_progress_bar);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);
        song_title_text = (TextView) findViewById(R.id.song_title_text);

        previous = (ImageView) findViewById(R.id.previous);
        stop = (ImageView) findViewById(R.id.stop);
        next = (ImageView) findViewById(R.id.next);
        cover = (ImageView) findViewById(R.id.cover);
        cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(whitchView==SECONDVIEW){
                   fab_rly.revealMainView();//重置到第一界面
                }
            }
        });
    }

    private void styleSeekbar(SeekBar songProgress) {
        int color = getResources().getColor(R.color.background);
        songProgress.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        songProgress.getThumb().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    private void configureFABReveal() {
        fab_rly.setOnRevealChangeListener(new OnRevealChangeListener() {

            //主界面
            @Override
            public void onMainViewAppeared(FABRevealLayout fabRevealLayout, View mainView) {
                showMainViewItems();
                cover.setImageResource(R.drawable.photo2);
                whitchView=FIRSTVIEW;
            }

            //第二界面
            @Override
            public void onSecondaryViewAppeared(FABRevealLayout fabRevealLayout, View secondaryView) {
                showSecondaryViewItems();
                cover.setImageResource(R.drawable.photo1);
//                prepareBackTransition(fabRevealLayout);
                whitchView=SECONDVIEW;
            }
        });
    }

    private void showMainViewItems() {
        scale(tv_title, 50);
        scale(tv_content, 150);
        scale(cover,50);
    }

    private void showSecondaryViewItems() {
        scale(cover,50);
        scale(song_progress_bar, 0);
        animateSeekBar(song_progress_bar);
        scale(song_title_text, 100);
        scale(previous, 150);
        scale(stop, 100);
        scale(next, 200);
    }

    private void prepareBackTransition(final FABRevealLayout fabRevealLayout) {
    //        new Handler().postDelayed(new Runnable() {
    //            @Override
    //            public void run() {
    //                fabRevealLayout.revealMainView();//重置到第一界面
    //            }
    //        }, 5000);
    }

    private void scale(View view, long delay) {
        view.setScaleX(0);
        view.setScaleY(0);
        view.animate()
                .scaleX(1)
                .scaleY(1)
                .setDuration(500)
                .setStartDelay(delay)
                .setInterpolator(new OvershootInterpolator())
                .start();
    }

    private void animateSeekBar(SeekBar seekBar) {
        seekBar.setProgress(15);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(seekBar, "progress", 15, 0);
        progressAnimator.setDuration(300);
        progressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        progressAnimator.start();
    }
}
