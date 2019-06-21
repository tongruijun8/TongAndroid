package com.trj.tbase.module.titlemodule;

import android.view.View;

/**
 * @author tong
 * @date 2018/3/18 18:34
 */

public interface TitleListenter {

    void onClickBack(View view);

    void onClickLeftText(View view);

    void onClickRightText(View view);

    void onClickMenu(View view);

    void onMenuItemClick(int position);
}
