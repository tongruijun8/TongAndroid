package com.trj.tbase.activity.mvp;

import com.trj.tbase.fragment.TFragment;
import com.trj.tbase.mvp.TModel;
import com.trj.tbase.mvp.TPresenter;
import com.trj.tbase.mvp.TView;

public abstract class BaseMVPFragment<V extends TView,M extends TModel, P extends TPresenter<V,M>>
        extends TFragment {

    private P presenter;

    /**
     * 获取 Presenter 对象，
     */
    public P getPresenter() {
        if (presenter == null) {
            presenter = initPersenter();
            presenter.setModel(initModel());
        }
        return presenter;
    }

    protected abstract P initPersenter();

    protected abstract M initModel();

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        super.onDestroy();
    }


}
