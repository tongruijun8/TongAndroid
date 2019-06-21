package com.trj.tbase.dialog;

import com.contrarywind.interfaces.IPickerViewData;

public class QxjBean implements IPickerViewData {

    private int idd;

    private String name;

    public QxjBean() {
    }

    public QxjBean(int idd, String name) {
        this.idd = idd;
        this.name = name;
    }

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
