package com.cniao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cniao.R;
import com.cniao.common.Constant;
import com.cniao.common.util.ACache;
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
                        jump();
                    }
                })
                .start();
    }

    private void jump() {
        String isShowGuide = ACache.get(this).getAsString(Constant.IS_SHOW_GUIDE);
        // 第一次启动进入引导页面
        if (null == isShowGuide) {
            startActivity(new Intent(this, GuideActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        this.finish();
    }
}
