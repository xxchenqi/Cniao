package com.cniao.presenter.contract;

import com.cniao.bean.BaseBean;
import com.cniao.bean.Category;
import com.cniao.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;


/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.presenter.contract
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public interface CategoryContract {


    public interface ICagegoryModel {
        Observable<BaseBean<List<Category>>> getCategories();
    }

    public interface CategoryView extends BaseView {
        public void showData(List<Category> categories);
    }

}
