package com.trj.tbase.module.select_img_module;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.trj.tbase.R;
import com.trj.tbase.util.Glide4Engine;
import com.trj.tlib.uils.Logger;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.List;

import static android.support.v7.app.AppCompatActivity.RESULT_OK;

/**
 * 图片选择的module
 */
public class SelectImageModule {

    private Context context;

    private FragmentActivity activity;

    private Fragment fragment;

    private SelectImageListener listener;

    public SelectImageModule(FragmentActivity fragmentActivity) {
        this.activity = fragmentActivity;
        context = fragmentActivity.getBaseContext();
    }

    public SelectImageModule(Fragment fragment) {
        this.fragment = fragment;
        context = fragment.getContext();
    }

    public void selectImage(int count, SelectImageListener listener) {
        if (count > 0) {
            this.listener = listener;
            Matisse matisse;
            if (activity == null) {
                matisse = Matisse.from(fragment);
            } else {
                matisse = Matisse.from(activity);
            }
            matisse.choose(MimeType.ofImage())
                    .theme(R.style.Matisse_Dracula)
                    .countable(true)
                    .capture(true)
                    .captureStrategy(
                            new CaptureStrategy(true, "com.trj.tlib.fileprovider","t_img"))
                    .maxSelectable(count)
                    .originalEnable(true)
                    .maxOriginalSize(10)
//                    .autoHideToolbarOnSingleTap(true)
//                    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                    .gridExpectedSize(context.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f)
                    .imageEngine(new Glide4Engine())
                    .forResult(10001);

        }
    }

    /**
     * 在Activity或者Fragment 的 onActivityResult()方法中调用此方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //选择返回
        if (resultCode == RESULT_OK && requestCode == 10001) {
            Logger.t("---------------####--------requestCode = " + requestCode);
//            Matisse.obtainResult(data),
//                    Matisse.obtainOriginalState(data)
            List<String> photos = Matisse.obtainPathResult(data);
            if (photos != null && photos.size() > 0 && listener != null) {
                listener.backImage(photos);
            }

        }
    }

}
