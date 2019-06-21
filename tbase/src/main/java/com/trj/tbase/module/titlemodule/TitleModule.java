package com.trj.tbase.module.titlemodule;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.trj.tbase.R;
import com.trj.tlib.views.TListView;

import java.util.List;

/**
 * @author tong
 * @date 2018/3/18 18:28
 */

/**
 * TitleModule的使用：
 *
 * @author tong
 * @date 2018/3/18 18:28
 *
 * 1.  <include layout="@layout/ttitle" />  // 添加布局
 * 2.  titleModule = new TitleModule(context, rootView);  // 新建对象
 * 3.  titleModule.initTitle("测试MVP的activity"); // 初始化标题栏的标题
 * 4.  titleModule.initTitleMenu(TitleModule.MENU_TEXT,"添加"); // 初始化标题栏的菜单
 * 5.  具体使用可以查看TitleModule类的方法
 *
 *
 */
public class TitleModule {

    private Context context;
    private View rootView;

    private TitleListenter listenter;
    /** 标题 */
    private String titleStr = "";
    /** 返回按钮 */
    private boolean showBack = false;

    public TitleModule(Context context, View rootView) {
        this.context = context;
        this.rootView = rootView;
        titleTopView = rootView.findViewById(R.id.title_top_view);
        titleBottomLine = rootView.findViewById(R.id.title_bottom_line);
    }

    public void setListenter(TitleListenter listenter) {
        this.listenter = listenter;
    }

    private LinearLayout title_rl;
    private View titleTopView;
    private TextView lefttext;
    private TextView centertext;
    private TextView righttext;
    private ImageView back,menu;
    private View titleBottomLine;

    /**
     * 初始化标题栏
     * @param titleStr 标题名称
     */
    public void initTitle(String titleStr){
        initTitle(titleStr, false);
    }

    /**
     * 初始化标题栏
     * @param titleStr 标题名称
     * @param showBack 是否显示返回按钮
     */
    public void initTitle(String titleStr, boolean showBack){
        this.titleStr = titleStr;
        this.showBack = showBack;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        title_rl = rootView.findViewById(R.id.title_rl);
        back =  rootView.findViewById(R.id.title_back);
        lefttext =  rootView.findViewById(R.id.title_lefttext);
        centertext =  rootView.findViewById(R.id.title_centertext);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listenter){
                    listenter.onClickBack(v);
                }
            }
        });
        lefttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listenter){
                    listenter.onClickLeftText(v);
                }
            }
        });
        //设置标题
        setTitleText(titleStr);
        //显示返回按钮
        setShowBack(showBack);
    }

    private boolean isShowTitle = true;

    /**
     * 获取标题的跟View
     * @return
     */
    public LinearLayout getTitleView(){
        return title_rl;
    }

    /**
     * 可以设置隐藏和显示标题
     * @param isShow
     */
    public void showTitleLayout(boolean isShow) {
        isShowTitle = isShow;
        if (isShow) title_rl.setVisibility(View.VISIBLE);
        else title_rl.setVisibility(View.GONE);
    }

    //改变标题栏的 隐藏 和 显示 状态
    public void taggleTitleLayout() {
        showTitleLayout(!isShowTitle);
    }

    /**
     * 设置标题的层级
     */
    public void setTitleElevation(float translationZ){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            title_rl.setTranslationZ(translationZ);
        }
    }

    public void setTitleBackground(@ColorRes int colorRes){
        title_rl.setBackgroundColor(context.getResources().getColor(colorRes));
//        titleTopView.setBackgroundColor(context.getResources().getColor(colorRes));
    }

    /**
     * 设置标题
     *
     * @param titleStr
     */
    public void setTitleText(String titleStr) {
        centertext.setText(titleStr);
    }

    /**
     * 设置标题颜色
     *
     * @param colorRes
     */
    public void setTitleTextColor(@ColorRes int colorRes) {
        centertext.setTextColor(context.getResources().getColor(colorRes));
    }

    /** 设置标题字体的大小 */
    public void setTitleTextSize(@DimenRes int dimenRes){
        centertext.setTextSize(context.getResources().getDimensionPixelOffset(dimenRes));
    }

    /**
     * 设置左边标题的显示
     */
    public void setTitleLeftText(String titleLeftStr){
        lefttext.setText(titleLeftStr);
    }
    /**
     * 设置左边标题的颜色
     */
    public void setTitleLeftTextColor(@ColorRes int colorRes){
        lefttext.setTextColor(context.getResources().getColor(colorRes));
    }

    /** 设置左边标题字体的大小 */
    public void setTitleLeftTextSize(@DimenRes int dimenRes){
        lefttext.setTextSize(context.getResources().getDimensionPixelOffset(dimenRes));
    }

    /**
     * 设置是否显示返回按钮：默认不显示
     * @param showBack
     */
    private void setShowBack(boolean showBack) {
        if(showBack){
            back.setVisibility(View.VISIBLE);
        }else{
            back.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题返回按钮的图标
     * @param resId
     */
    public void setBackImage(@DrawableRes int resId){
        back.setImageResource(resId);
    }


    /** 菜单类型 */
    private int menuType = MENU_NONE;

    /*不显示菜单文本和按钮(默认)*/
    public static final int MENU_NONE = 0;
    /*显示菜单按钮*/
    public static final int MENU_IMAGE = 1;
    /*显示菜单文本*/
    public static final int MENU_TEXT = 2;
    /*显示菜单文本和按钮*/
    public static final int MENU_ALL = 3;


    /**
     * 初始化标题菜单
     * @param menuType 菜单类型
     */
    public void initTitleMenu(int menuType){
        this.initTitleMenu(menuType,"");
    }

    /**
     * 初始化标题菜单
     * @param menuType 菜单类型
     * @param menuStr 菜单显示文本
     */
    public void initTitleMenu(int menuType,String menuStr){
        this.menuType = menuType;
        initMenu(menuStr);
    }

    /**
     * 初始化标题的菜单按钮
     */
    private void initMenu(String menuStr) {
        righttext =  rootView.findViewById(R.id.title_righttext);
        menu =  rootView.findViewById(R.id.title_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listenter){
                    listenter.onClickMenu(v);
                }
            }
        });
        righttext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(null != listenter){
                    listenter.onClickRightText(v);
                }
            }
        });
        setMenuVisibility(menuType);
        setMenuText(menuStr);
    }

    /**
     * 设置标题菜单的显示
     */
    public void setMenuVisibility(int menuType){
        righttext.setVisibility(View.GONE);
        menu.setVisibility(View.GONE);
        if(menuType == MENU_IMAGE){
            menu.setVisibility(View.VISIBLE);
        }else if(menuType == MENU_TEXT){
            righttext.setVisibility(View.VISIBLE);
        }else if(menuType == MENU_ALL){
            righttext.setVisibility(View.VISIBLE);
            menu.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置菜单文本
     * @param titleStr
     */
    public void setMenuText(String titleStr) {
        if (titleStr != null || titleStr.equals("")) {
            righttext.setText(titleStr);
            righttext.setVisibility(View.VISIBLE);
        }else{
            righttext.setVisibility(View.GONE);
        }
    }

    /** 设置菜单文本的颜色 */
    public void setMenuTextColor(@ColorRes int colorRes){
        righttext.setTextColor(context.getResources().getColor(colorRes));
    }

    /** 设置菜单文本的大小 */
    public void setMenuTextSize(@DimenRes int dimenRes){
        righttext.setTextSize(context.getResources().getDimensionPixelOffset(dimenRes));
    }

    //设置标题菜单的图标

    /**
     * 设置标题菜单的图标
     * @param resId 图片资源id
     */
    public void setTitleMenuImage(@DrawableRes int resId){
        menu.setImageResource(resId);
    }

    private List<String> stringList;
    public void setMenuContent(List<String> stringList){
        this.stringList = stringList;
    }

    private PopupWindow popupWindow;
    public void showMenuItem(){
        if (null != popupWindow && popupWindow.isShowing()) {
            return;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.title_menu, null);
        TListView tListview = view.findViewById(R.id.title_menu_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.item_menu, R.id.item_menu_textview, stringList);
        tListview.setAdapter(adapter);
        tListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onMenuItemClick(position);
            }
        });
        popupWindow = new PopupWindow(context);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.showAsDropDown(menu,0,context.getResources().getDimensionPixelOffset(R.dimen.ttitle_menu_top));

    }

    public void onMenuItemClick(int position){
        popupWindow.dismiss();
        if(null!=listenter){
            listenter.onMenuItemClick(position);
        }
    }


    /** 是否显示状态栏的高度：默认显示 */
    public void setTitleTopViewShow(boolean isShow) {
        ViewGroup.LayoutParams params = title_rl.getLayoutParams();
        if(isShow){
            titleTopView.setVisibility(View.VISIBLE);
            params.height = context.getResources().getDimensionPixelOffset(R.dimen.ttitle_and_top_h);
        }else{
            titleTopView.setVisibility(View.GONE);
            params.height = context.getResources().getDimensionPixelOffset(R.dimen.ttitle_h);
        }
        title_rl.setLayoutParams(params);
    }

    /** 是否显示标题栏底部分割线：默认不显示 */
    public void setTitleBottomLineShow(boolean isShow) {
        if(isShow){
            titleBottomLine.setVisibility(View.VISIBLE);
        }else{
            titleBottomLine.setVisibility(View.GONE);
        }
    }

    /**  标题栏底部分割线的颜色 */
    public void setTitleBottomLineColor(@ColorRes int colorRes){
        titleBottomLine.setBackgroundResource(colorRes);
    }



}
