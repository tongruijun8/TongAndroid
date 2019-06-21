package com.trj.tbase.module.filtermodule;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tbase.adapter.SelectSskAdapter;
import com.trj.tbase.R;
import com.trj.tlib.views.TLinearLayout;

import java.util.List;

/**
 * @author tong
 * @date 2018/7/18 17:28
 */
public class TFilterModule implements TLinearLayout.TLLOnClickListenter{

    protected boolean isChangeTitleStr = false;

    private Context context;
    protected ViewHolder viewHolder;
    private int itemHeight = 40;

    //搜索框显示状态
    private boolean isShowSsk = false;

    //列数
    private int columnsNumber;

    //默认tab标题
    protected String defaultTitle1, defaultTitle2, defaultTitle3, defaultTitle4;

    //---------------集合相关
    //是否显示对应的选项框
    protected boolean oneShow, twoShow, threeShow, fourShow;
    //对应列的集合
    protected List<String> list1;
    protected List<String> list2;
    protected List<String> list3;
    protected List<String> list4;
    //设置是否标记已经选择的条目：默认标记
    protected boolean itemSelectMark = true;
    //对应列的选择项
    protected int listPosition1 = 0;
    protected int listPosition2 = 0;
    protected int listPosition3 = 0;
    protected int listPosition4 = 0;

    /**
     * 筛选框
     *
     * @param context
     * @param view    根View
     * @param columns 列数(目前最大支持4)
     *
     *                <p>
     *                备注：在使用的时候，需要的布局中添加layout_filter.xml 布局文件，且必须放在activity布局的最上层
     */
    public TFilterModule(Context context, View view, int columns) {
        this.context = context;
        this.columnsNumber = columns;
        initLayoutView(view);
    }

    private void initLayoutView(View view) {
        viewHolder = new ViewHolder(view);
        viewHolder.mLayoutFilterLl1.setTLLOnClickListenter(this);
        viewHolder.mLayoutFilterLl2.setTLLOnClickListenter(this);
        viewHolder.mLayoutFilterLl3.setTLLOnClickListenter(this);
        viewHolder.mLayoutFilterLl4.setTLLOnClickListenter(this);
        viewHolder.mLayoutFilterScrollview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSSK();
                hideSsk();
            }
        });
        viewHolder.mLayoutFilterListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (index == 1) {
                    listPosition1 = position;
                } else if (index == 2) {
                    listPosition2 = position;
                } else if (index == 3) {
                    listPosition3 = position;
                } else if (index == 4) {
                    listPosition4 = position;
                }
                onFilterItemBefore(index, position);
                if (null != listener) {
                    listener.onFilterItem(index, position);
                }

                initSSK();
                hideSsk();
            }
        });
    }


    /**
     * 点击条目：onFilterItem监听之前,用于改变筛选框tab的显示
     */
    private void onFilterItemBefore(int tabIndex, int itemPosition) {
        if (isChangeTitleStr) {
            if (tabIndex == 1) {
                if (list1 != null && list1.size() > itemPosition) {
                    if (itemPosition == 0 && defaultTitle1 != null && !defaultTitle1.equals("")) {
                        viewHolder.mLayoutFilterName1.setText(defaultTitle1);
                    } else {
                        viewHolder.mLayoutFilterName1.setText(list1.get(itemPosition));
                    }
                }
            } else if (tabIndex == 2) {
                if (list2 != null && list2.size() > itemPosition) {
                    if (itemPosition == 0 && defaultTitle2 != null && !defaultTitle2.equals("")) {
                        viewHolder.mLayoutFilterName2.setText(defaultTitle2);
                    } else {
                        viewHolder.mLayoutFilterName2.setText(list2.get(itemPosition));
                    }
                }
            } else if (tabIndex == 3) {
                if (list3 != null && list3.size() > itemPosition) {
                    if (itemPosition == 0 && defaultTitle3 != null && !defaultTitle3.equals("")) {
                        viewHolder.mLayoutFilterName3.setText(defaultTitle3);
                    } else {
                        viewHolder.mLayoutFilterName3.setText(list3.get(itemPosition));
                    }
                }
            } else if (tabIndex == 4) {
                if (list4 != null && list4.size() > itemPosition) {
                    if (itemPosition == 0 && defaultTitle4 != null && !defaultTitle4.equals("")) {
                        viewHolder.mLayoutFilterName4.setText(defaultTitle4);
                    } else {
                        viewHolder.mLayoutFilterName4.setText(list4.get(itemPosition));
                    }
                }
            }
        }
    }


    @Override
    public void onTLLClick(View v) {
        initSSK();
        if (v == viewHolder.mLayoutFilterLl1) {
            initSSKState(1, oneShow);
        } else if (v == viewHolder.mLayoutFilterLl2) {
            initSSKState(2, twoShow);
        } else if (v == viewHolder.mLayoutFilterLl3) {
            initSSKState(3, threeShow);
        } else if (v == viewHolder.mLayoutFilterLl4) {
            initSSKState(4, fourShow);
        }
    }

    //隐藏搜索框
    protected void hideSsk() {
        viewHolder.mLayoutFilterScrollview.setVisibility(View.GONE);
        isShowSsk = false;
    }

    //隐藏搜索框
    private void showSsk() {
        viewHolder.mLayoutFilterScrollview.setVisibility(View.VISIBLE);
        isShowSsk = true;
    }

    private boolean toggleSsk() {
        if (viewHolder.mLayoutFilterScrollview.getVisibility() == View.VISIBLE) {
            hideSsk();
            return false;
        } else {
            showSsk();
            return true;
        }
    }

    private SelectSskAdapter adapter;

    private void bindListData(int index) {
        if (index == 1) {
            if (null == list1) {
                return;
            }
            adapter = new SelectSskAdapter(context, list1, listPosition1, itemSelectMark);
            viewHolder.mLayoutFilterListview.setAdapter(adapter);
//            if(itemSelectMark){
            viewHolder.mLayoutFilterListview.setSelection(listPosition1);
//            }
            setListViewHeight(list1.size());
        } else if (index == 2) {
            if (null == list2) {
                return;
            }
            adapter = new SelectSskAdapter(context, list2, listPosition2, itemSelectMark);
            viewHolder.mLayoutFilterListview.setAdapter(adapter);
//            if(itemSelectMark){
            viewHolder.mLayoutFilterListview.setSelection(listPosition2);
//            }
            setListViewHeight(list2.size());
        } else if (index == 3) {
            if (null == list3) {
                return;
            }
            adapter = new SelectSskAdapter(context, list3, listPosition3, itemSelectMark);
            viewHolder.mLayoutFilterListview.setAdapter(adapter);
//            if(itemSelectMark){
            viewHolder.mLayoutFilterListview.setSelection(listPosition3);
//            }
            setListViewHeight(list3.size());
        } else if (index == 4) {
            if (null == list4) {
                return;
            }
            adapter = new SelectSskAdapter(context, list4, listPosition4, itemSelectMark);
            viewHolder.mLayoutFilterListview.setAdapter(adapter);
//            if(itemSelectMark){
            viewHolder.mLayoutFilterListview.setSelection(listPosition4);
//            }
            setListViewHeight(list4.size());
        }
    }

    private void setListViewHeight(int listSize) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) (context.getResources().getDisplayMetrics().density * itemHeight * (listSize > 7 ? 7 : listSize) + 1));
        viewHolder.mLayoutFilterListview.setLayoutParams(params);
    }

    private int index = 0;

    /**
     * 初始化搜素框标题栏
     */
    private void initSSK() {
        switch (columnsNumber) {
            case 4:
                viewHolder.mLayoutFilterName4.setTextColor(context.getResources().getColor(R.color.color_filter_item_));
                viewHolder.mLayoutFilterImg4.setImageResource(R.mipmap.icon_xiala);
            case 3:
                viewHolder.mLayoutFilterName3.setTextColor(context.getResources().getColor(R.color.color_filter_item_));
                viewHolder.mLayoutFilterImg3.setImageResource(R.mipmap.icon_xiala);
            case 2:
                viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_filter_item_));
                viewHolder.mLayoutFilterImg2.setImageResource(R.mipmap.icon_xiala);
            case 1:
                viewHolder.mLayoutFilterName1.setTextColor(context.getResources().getColor(R.color.color_filter_item_));
                viewHolder.mLayoutFilterImg1.setImageResource(R.mipmap.icon_xiala);
                break;
        }

    }

    /**
     * 初始化搜素框
     *
     * @param position 位置
     * @param showSsk  是否需要显示搜索框
     */
    private void initSSKState(int position, boolean showSsk) {
        if (position == 1) {
            if (!showSsk) {
                viewHolder.mLayoutFilterName1.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                hideSsk();
                //针对无下拉筛选框的情况
                if (null != listener) {
                    listener.onFilterItem(1, -1);
                }
            } else {
                if (index == 1) {
                    if (toggleSsk()) {
                        viewHolder.mLayoutFilterName1.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                        viewHolder.mLayoutFilterImg1.setImageResource(R.mipmap.icon_shouqi);
                        if (itemSelectMark) {
                            adapter.setIndex(listPosition1);
                            adapter.notifyDataSetChanged();
                            viewHolder.mLayoutFilterListview.setSelection(listPosition1);
                        }
                    }
                } else {
                    viewHolder.mLayoutFilterName1.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                    viewHolder.mLayoutFilterImg1.setImageResource(R.mipmap.icon_shouqi);
                    bindListData(position);
                    if (!isShowSsk) {
                        showSsk();
                    }
                }
            }
            index = 1;

        } else if (position == 2) {
            if (!showSsk) {
                viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                hideSsk();
                //针对无下拉筛选框的情况
                if (null != listener) {
                    listener.onFilterItem(2, -1);
                }
            } else {
                if (index == 2) {
                    if (toggleSsk()) {
                        viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                        viewHolder.mLayoutFilterImg2.setImageResource(R.mipmap.icon_shouqi);
                        if (itemSelectMark) {
                            adapter.setIndex(listPosition2);
                            adapter.notifyDataSetChanged();
                            viewHolder.mLayoutFilterListview.setSelection(listPosition2);
                        }
                    }
                } else {
                    viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                    viewHolder.mLayoutFilterImg2.setImageResource(R.mipmap.icon_shouqi);
                    bindListData(position);
                    if (!isShowSsk) {
                        showSsk();
                    }
                }
            }
            index = 2;
        } else if (position == 2) {
            if (!showSsk) {
                viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                hideSsk();
                //针对无下拉筛选框的情况
                if (null != listener) {
                    listener.onFilterItem(2, -1);
                }
            } else {
                if (index == 2) {
                    if (toggleSsk()) {
                        viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                        viewHolder.mLayoutFilterImg2.setImageResource(R.mipmap.icon_shouqi);
                        if (itemSelectMark) {
                            adapter.setIndex(listPosition2);
                            adapter.notifyDataSetChanged();
                            viewHolder.mLayoutFilterListview.setSelection(listPosition2);
                        }
                    }
                } else {
                    viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                    viewHolder.mLayoutFilterImg2.setImageResource(R.mipmap.icon_shouqi);
                    bindListData(position);
                    if (!isShowSsk) {
                        showSsk();
                    }
                }
            }
            index = 2;
        } else if (position == 3) {
            if (!showSsk) {
                viewHolder.mLayoutFilterName3.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                hideSsk();
                //针对无下拉筛选框的情况
                if (null != listener) {
                    listener.onFilterItem(3, -1);
                }
            } else {
                if (index == 3) {
                    if (toggleSsk()) {
                        viewHolder.mLayoutFilterName3.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                        viewHolder.mLayoutFilterImg3.setImageResource(R.mipmap.icon_shouqi);
                        if (itemSelectMark) {
                            adapter.setIndex(listPosition3);
                            adapter.notifyDataSetChanged();
                            viewHolder.mLayoutFilterListview.setSelection(listPosition3);
                        }
                    }
                } else {
                    viewHolder.mLayoutFilterName3.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                    viewHolder.mLayoutFilterImg3.setImageResource(R.mipmap.icon_shouqi);
                    bindListData(position);
                    if (!isShowSsk) {
                        showSsk();
                    }
                }
            }
            index = 3;
        } else if (position == 4) {
            if (!showSsk) {
                viewHolder.mLayoutFilterName4.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                hideSsk();
                //针对无下拉筛选框的情况
                if (null != listener) {
                    listener.onFilterItem(4, -1);
                }
            } else {
                if (index == 4) {
                    if (toggleSsk()) {
                        viewHolder.mLayoutFilterName4.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                        viewHolder.mLayoutFilterImg4.setImageResource(R.mipmap.icon_shouqi);
                        if (itemSelectMark) {
                            adapter.setIndex(listPosition4);
                            adapter.notifyDataSetChanged();
                            viewHolder.mLayoutFilterListview.setSelection(listPosition4);
                        }
                    }
                } else {
                    viewHolder.mLayoutFilterName4.setTextColor(context.getResources().getColor(R.color.color_filter_item_select));
                    viewHolder.mLayoutFilterImg4.setImageResource(R.mipmap.icon_shouqi);
                    bindListData(position);
                    if (!isShowSsk) {
                        showSsk();
                    }
                }
            }
            index = 4;
        }
    }


    private OnTFilterItemClickListener listener;

    public void setOnTFilterModuleListener(OnTFilterItemClickListener listener) {
        this.listener = listener;
    }


    class ViewHolder {
        View view;
        TextView mLayoutFilterName1;
        ImageView mLayoutFilterImg1;
        TLinearLayout mLayoutFilterLl1;
        TextView mLayoutFilterName2;
        ImageView mLayoutFilterImg2;
        TLinearLayout mLayoutFilterLl2;
        LinearLayout mLayoutFilterLl;
        ListView mLayoutFilterListview;
        RelativeLayout mLayoutFilterScrollview;

        TextView mLayoutFilterName3;
        ImageView mLayoutFilterImg3;
        TLinearLayout mLayoutFilterLl3;
        TextView mLayoutFilterName4;
        ImageView mLayoutFilterImg4;
        TLinearLayout mLayoutFilterLl4;

        ViewHolder(View view) {
            this.view = view;
            this.mLayoutFilterName4 = view.findViewById(R.id.layout_filter_name4);
            this.mLayoutFilterImg4 = view.findViewById(R.id.layout_filter_img4);
            this.mLayoutFilterLl4 = view.findViewById(R.id.layout_filter_ll4);
            this.mLayoutFilterName3 = view.findViewById(R.id.layout_filter_name3);
            this.mLayoutFilterImg3 = view.findViewById(R.id.layout_filter_img3);
            this.mLayoutFilterLl3 = view.findViewById(R.id.layout_filter_ll3);
            this.mLayoutFilterName2 = view.findViewById(R.id.layout_filter_name2);
            this.mLayoutFilterImg2 = view.findViewById(R.id.layout_filter_img2);
            this.mLayoutFilterLl2 = view.findViewById(R.id.layout_filter_ll2);
            this.mLayoutFilterName1 = view.findViewById(R.id.layout_filter_name1);
            this.mLayoutFilterImg1 = view.findViewById(R.id.layout_filter_img1);
            this.mLayoutFilterLl1 = view.findViewById(R.id.layout_filter_ll1);
            this.mLayoutFilterLl = view.findViewById(R.id.layout_filter_ll);
            this.mLayoutFilterListview = view.findViewById(R.id.layout_filter_listview);
            this.mLayoutFilterScrollview = view.findViewById(R.id.layout_filter_scrollview);
            switch (columnsNumber) {
                case 1:
                    this.mLayoutFilterLl2.setVisibility(View.GONE);
                    this.mLayoutFilterLl3.setVisibility(View.GONE);
                    this.mLayoutFilterLl4.setVisibility(View.GONE);
                    mLayoutFilterName1.setMaxWidth(context.getResources().getDimensionPixelOffset(R.dimen.filter_max_w1));
                case 2:
                    this.mLayoutFilterLl3.setVisibility(View.GONE);
                    this.mLayoutFilterLl4.setVisibility(View.GONE);
                    mLayoutFilterName1.setMaxWidth(context.getResources().getDimensionPixelOffset(R.dimen.filter_max_w2));
                    mLayoutFilterName2.setMaxWidth(context.getResources().getDimensionPixelOffset(R.dimen.filter_max_w2));
                case 3:
                    this.mLayoutFilterLl4.setVisibility(View.GONE);
                    mLayoutFilterName1.setMaxWidth(context.getResources().getDimensionPixelOffset(R.dimen.filter_max_w3));
                    mLayoutFilterName2.setMaxWidth(context.getResources().getDimensionPixelOffset(R.dimen.filter_max_w3));
                    mLayoutFilterName3.setMaxWidth(context.getResources().getDimensionPixelOffset(R.dimen.filter_max_w3));
                    break;
                default:
                    break;
            }

        }
    }
}
