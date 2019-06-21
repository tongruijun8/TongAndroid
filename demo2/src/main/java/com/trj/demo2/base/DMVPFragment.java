package com.trj.demo2.base;

import com.trj.demo2.http.DPresenter;
import com.trj.demo2.http.DemoModel;
import com.trj.tbase.activity.mvp.BaseMVPFragment;
import com.trj.tbase.mvp.TView;

public abstract class DMVPFragment<V extends TView, P extends DPresenter<V>>
        extends BaseMVPFragment<V, DemoModel, P> {
}
