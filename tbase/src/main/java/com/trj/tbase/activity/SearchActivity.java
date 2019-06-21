package com.trj.tbase.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.trj.tbase.R;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.SharedPreferencesUtils;
import com.trj.tlib.uils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by W on 2018/3/24.
 */

public class SearchActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    public static String searchHistory = "SearchHistory";

    private EditText editText;
    private ListView listView;
    private LinearLayout historyLL;
    private TextView btnClear;

    private ListView listview_history;

    private ImageView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle(null,true);

        editText = findViewById(R.id.title_center_text);

        listView = findViewById(R.id.listview);
        empty = findViewById(R.id.empty);
        historyLL = findViewById(R.id.history_ll);
        btnClear = findViewById(R.id.search_clear);
        listview_history = findViewById(R.id.listview_history);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyData.clear();
                arrayAdapter.notifyDataSetChanged();
                changeSearchData("");
            }
        });

        getSearchHistoryData();

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyLL.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                empty.setVisibility(View.GONE);
                getSearchHistoryData();
            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Logger.t("-----keyCode = " + keyCode);
                if (keyCode == KeyEvent.KEYCODE_ENTER && isSearch) {
                    String ssStr = editText.getText().toString().trim();
                    if(ssStr.equals("")){
                        ToastUtil.showToast(context,"搜索内容不能为空");
                        return true;
                    }
                    isSearch = false;
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
//                    ToastUtil.showToast(context,"搜索内容："+ssStr);
                    Logger.t("---------ss = " + ssStr);
                    searchData(ssStr);
                    return true;
                }
                return false;
            }
        });

    }

    private boolean isSearch = true;

    private List<String> historyData = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    private void getSearchHistoryData() {
        historyData.clear();
        String searchHistoryStr = (String) SharedPreferencesUtils.getParam(context,searchHistory, "");
        Logger.t("----------searchHistoryStr = " + searchHistoryStr);
        if (!searchHistoryStr.equals("")) {
            historyData.addAll(Arrays.asList(searchHistoryStr.split("\\|")));
        }
        Logger.t("----------size = " + historyData.size());
        if(null == arrayAdapter){
            arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, android.R.id.text1, historyData);
            listview_history.setAdapter(arrayAdapter);
            listview_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String str = historyData.get(position);
                    editText.setText(str);
                    editText.setSelection(str.length());
                    searchData(str);
                }
            });
        }else{
            arrayAdapter.notifyDataSetChanged();
        }


    }

    private void searchData(String searchStr){
        historyLL.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
//        kcInfoList.clear();
        searchPost(searchStr);
        historyData.add(0, searchStr);
        changeSearchData(searchStr);
    }

    private void changeSearchData(String searchStr){
        StringBuilder stringBuilder = new StringBuilder();
        int totalNum = 0;
        if (historyData.size() > 0) {
            e:for (int i = 0; i < historyData.size(); i++) {
                String conStr = historyData.get(i);
                if (!conStr.equals(searchStr)) {
                    stringBuilder.append(historyData.get(i) + "|");
                    totalNum++;
                    if (totalNum == 10) {
                        break e;
                    }
                }else{
                    if (i == 0) {
                        stringBuilder.append(historyData.get(i) + "|");
                        totalNum++;
                    }
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
        Logger.t("--------------str = "+stringBuilder.toString());
        SharedPreferencesUtils.setParam(context,searchHistory,stringBuilder.toString());
    }

//    搜索请求
    private void searchPost(String searchStr){

    }



//    private List<RespKcInfoJB> kcInfoList = new ArrayList<>();
//
//    private SearchKcAdapter adapter;
//
//    @Override
//    public void getSearchList(TBaseRespJB2<List<RespKcInfoJB>> kcInfoJBList) {
//        isSearch = true;
//        List<RespKcInfoJB> kcList =  kcInfoJBList.getData();
//        if(null!=kcList){
//            kcInfoList.addAll(kcList);
//        }
//        if(kcInfoList.size()==0){
//            empty.setVisibility(View.VISIBLE);
//        }else{
//            empty.setVisibility(View.GONE);
//        }
//        if(null == adapter){
//            adapter = new SearchKcAdapter(context, kcInfoList);
//            listView.setAdapter(adapter);
//            listView.setOnItemClickListener(this);
//        }else{
//            adapter.notifyDataSetChanged();
//        }
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
