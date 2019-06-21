package com.trj.demo2.http;

import android.support.annotation.NonNull;

import com.trj.demo2.bean.RespBean;
import com.trj.tbase.mvp.TPresenter;
import com.trj.tbase.mvp.TView;

import rx.Subscriber;

public class DPresenter<V extends TView> extends TPresenter<V, DemoModel> {

    public DPresenter(@NonNull V view) {
        super(view);
    }

    //判断数据的状态
    public boolean responseState(RespBean respBean) {
        return responseState(respBean, false);//默认不需要提醒
    }

    //判断数据的状态
    public boolean responseState(RespBean respBean,boolean isRemind) {
        if (respBean != null) {
            int state = respBean.getState();
            if(state == 1){//成功返回数据
                return true;
            }else{
                if (isRemind && isViewAttach()) {
                    getView().tRemind(respBean.getMsg());//需要提醒的调用此方法
                }
            }
            return false;
        }else{
            if (isViewAttach()) {
                getView().tPostError("返回的数据异常");
            }
        }
        return false;
    }

    public abstract class DSubscriber<T> extends Subscriber<RespBean<T>> {
//        onCompleted 与 onError 只能同时执行一个方法
        //正长结束
        @Override
        public void onCompleted() {
            onDFinish(-1);
        }
        //异常
        @Override
        public void onError(Throwable e) {
            onDError(e);
            onDFinish(-1);
        }
//        请求数据
        @Override
        public abstract void onNext(RespBean<T> respBean);

    }

}
