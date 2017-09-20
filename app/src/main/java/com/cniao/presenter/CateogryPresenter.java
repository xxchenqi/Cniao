package com.cniao.presenter;


import com.cniao.bean.Category;
import com.cniao.common.rx.RxHttpResponeCompat;
import com.cniao.common.rx.subscriber.ProgressSubscriber;
import com.cniao.presenter.contract.CategoryContract;

import java.util.List;

import javax.inject.Inject;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.presenter
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class CateogryPresenter extends BasePresenter<CategoryContract.ICagegoryModel, CategoryContract.CategoryView> {

    @Inject
    public CateogryPresenter(CategoryContract.ICagegoryModel iCagegoryModel, CategoryContract.CategoryView categoryView) {
        super(iCagegoryModel, categoryView);
    }

    public void getAllCategory() {
        mModel.getCategories().compose(RxHttpResponeCompat.<List<Category>>compatResult())
                .subscribe(new ProgressSubscriber<List<Category>>(mContext, mView) {
                    @Override
                    public void onNext(List<Category> categories) {
                        mView.showData(categories);
                    }
                });
    }
}
