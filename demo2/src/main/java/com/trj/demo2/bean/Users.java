package com.trj.demo2.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.trj.demo2.BR;
import com.trj.tlib.assist.ImgPaths;
import com.trj.tlib.uils.GlideUtile;

import java.util.Random;

public class Users extends BaseObservable {

    /**
     * id : 1
     * user_phone : 15091288100
     * user_psw : e10adc3949ba59abbe56e057f20f883e
     * user_name : 童瑞军
     * user_no : ta0000001
     * user_sex : 1
     * user_nick : 小童
     * user_age : 30
     * user_type : 0
     * user_address : 陕西省西安市长安区
     * user_identity_no : 123456789123456789
     * user_weixin_no : 001
     * user_qq_no : 001
     */

//    private int id;
    private String user_photo;
    private String user_phone;
    private String user_psw;
    private String user_name;
    private String user_no;
    private int user_sex;
    private String user_nick;
    private int user_age;
    private int user_type;
    private String user_address;
    private String user_identity_no;
    private String user_weixin_no;
    private String user_qq_no;


    private String msg;

    @BindingAdapter({"imgUrl"})
    public static void loadImage(ImageView imageView, String imgUrl){
        if (TextUtils.isEmpty(imgUrl)) {
            int index = new Random().nextInt(ImgPaths.path.length);
            imgUrl = ImgPaths.path[index];
        }
        GlideUtile.bindImageView(imageView.getContext(),imgUrl,imageView);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_psw() {
        return user_psw;
    }

    public void setUser_psw(String user_psw) {
        this.user_psw = user_psw;
    }

    @Bindable
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
        notifyPropertyChanged(BR.user_name);
    }

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public int getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(int user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    @Bindable
    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
        notifyPropertyChanged(BR.user_address);
    }

    public String getUser_identity_no() {
        return user_identity_no;
    }

    public void setUser_identity_no(String user_identity_no) {
        this.user_identity_no = user_identity_no;
    }

    public String getUser_weixin_no() {
        return user_weixin_no;
    }

    public void setUser_weixin_no(String user_weixin_no) {
        this.user_weixin_no = user_weixin_no;
    }

    public String getUser_qq_no() {
        return user_qq_no;
    }

    public void setUser_qq_no(String user_qq_no) {
        this.user_qq_no = user_qq_no;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_photo='" + user_photo + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_psw='" + user_psw + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_no='" + user_no + '\'' +
                ", user_sex=" + user_sex +
                ", user_nick='" + user_nick + '\'' +
                ", user_age=" + user_age +
                ", user_type=" + user_type +
                ", user_address='" + user_address + '\'' +
                ", user_identity_no='" + user_identity_no + '\'' +
                ", user_weixin_no='" + user_weixin_no + '\'' +
                ", user_qq_no='" + user_qq_no + '\'' +
                '}';
    }
}
