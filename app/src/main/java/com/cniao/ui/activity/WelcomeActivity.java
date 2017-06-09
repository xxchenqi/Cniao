package com.cniao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cniao.R;
import com.eftimoff.androipathview.PathView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenqi on 2017/6/9.
 */

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.path_view)
    PathView pathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        pathView.getPathAnimator()
                .delay(10)
                .duration(5000)
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        finish();
                    }
                })
                .start();
    }
}
