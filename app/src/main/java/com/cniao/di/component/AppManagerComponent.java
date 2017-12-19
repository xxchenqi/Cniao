package com.cniao.di.component;


import com.cniao.di.FragmentScope;
import com.cniao.di.module.AppManagerModule;
import com.cniao.ui.fragment.DownloadedFragment;
import com.cniao.ui.fragment.DownloadingFragment;
import com.cniao.ui.fragment.InstalledAppAppFragment;

import dagger.Component;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.di
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

@FragmentScope
@Component(modules = AppManagerModule.class,dependencies = AppComponent.class)
public interface AppManagerComponent {
    void inject(DownloadingFragment fragment);
    void injectDownloaded(DownloadedFragment fragment);
    void injectInstalled(InstalledAppAppFragment fragment);

}
