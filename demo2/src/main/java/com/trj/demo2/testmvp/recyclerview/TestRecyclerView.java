package com.trj.demo2.testmvp.recyclerview;

import com.trj.demo2.bean.Users;
import com.trj.tbase.mvp.TView;

import java.util.List;

interface TestRecyclerView extends TView {

    void bindListData(List<Users> usersList);

}
