package com.cniao.bean;

import java.util.List;

/**
 * Created by chenqi on 2017/8/17.
 */

public class IndexBean {
    private List<Banner> banners;
    private List<AppInfo> recommendApps;
    private List<AppInfo> recommendGames;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<AppInfo> getRecommendApps() {
        return recommendApps;
    }

    public void setRecommendApps(List<AppInfo> recommendApps) {
        this.recommendApps = recommendApps;
    }

    public List<AppInfo> getRecommendGames() {
        return recommendGames;
    }

    public void setRecommendGames(List<AppInfo> recommendGames) {
        this.recommendGames = recommendGames;
    }
}
