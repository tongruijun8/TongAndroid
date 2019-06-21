package com.trj.tbase.dialog;


import com.contrarywind.interfaces.IPickerViewData;

public class LessonsBean  implements IPickerViewData {

    private int lessons;

    public LessonsBean() {
    }

    public LessonsBean(int lessons) {
        this.lessons = lessons;
    }

    public int getLessons() {
        return lessons;
    }

    public void setLessons(int lessons) {
        this.lessons = lessons;
    }

    @Override
    public String getPickerViewText() {
        return "第"+lessons+"节";
    }
}
