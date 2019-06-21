package com.trj.demo2.http;

import com.trj.demo2.bean.PageBean;
import com.trj.demo2.bean.RespBean;
import com.trj.demo2.bean.Users;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface DemoAPI {

    @FormUrlEncoded
    @POST("userController/userinfo")
    Observable<RespBean<Users>> login(@Field("phone") String username,
                                      @Field("psw") String password);

    @FormUrlEncoded
    @POST("userController/userinfoAll")
    Observable<RespBean<List<Users>>> userAll(@Field("page") int page,
                                              @Field("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("userController/userinfoAll2")
    Observable<RespBean<PageBean<Users>>> userAll2(@Field("page") int page,
                                                   @Field("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("userController/userinfoCreate")
    Observable<RespBean> userAdd(@Field("phone") String phone,
                                 @Field("psw") String psw);

    @FormUrlEncoded
    @POST("userController/userinfoCreateMore")
    Observable<RespBean> userAddMore(@Field("info") String contactsJsonStr);


    //----------------------测试用--------------------------------
    @FormUrlEncoded
    @POST("https://tpmtea.gantower.com/app/Leave/saveLeave.do")
    Observable<RespBean> saveLeave(@Field("data") String data);


//    @FormUrlEncoded
//    @POST
//    Call<Users> login(@Url String url,
//                      @Field("sch_id") int sch_id,
//                      @Field("username") String username,
//                      @Field("password") String password);

}
