package com.trj.tbase.mvp;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * MVP设计模式：Presenter层（基类）
 * <p>
 * 中介（MVP设计目的：为了将UI层和数据层进行解耦和），通过P层进行关联
 */
public class TPresenter<V extends TView, M extends TModel> implements IBaseTPresenter {

    // 防止 Activity 不走 onDestory() 方法，所以采用弱引用来防止内存泄漏
    private WeakReference<V> mViewRef;
    protected M model;

    public TPresenter(@NonNull V view) {
        attachView(view);
    }

    private void attachView(V view) {
        mViewRef = new WeakReference(view);
    }

    /**
     * 设置TModel
     *
     * @param model
     */
    public void setModel(M model) {
        this.model = model;
    }

    /**
     * 获取View层对象
     *
     * @return
     */
    public V getView() {
        return mViewRef.get();
    }

    /**
     * 判断是否关联了View层
     *
     * @return
     */
    @Override
    public boolean isViewAttach() {
        return mViewRef != null && getView() != null;
    }

    /**
     * 解除View层关联
     */
    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    protected void onDFinish(int code) {
        if (isViewAttach()) {
            getView().tPostFinish(code);
        }
    }

    protected void onDError(Throwable e) {
        if (isViewAttach()) {
            getView().tPostError(e.getMessage());
        }
    }

    protected void onDRemind(String msg) {
        if (isViewAttach()) {
            getView().tRemind(msg);
        }
    }

    protected <B> void onDFaile(B b) {
        if (isViewAttach()) {
            getView().tPostFail(b);
        }
    }

}
