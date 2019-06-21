package com.trj.tlib.uils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * RxPermission 权限的工具类
 * <p>
 * 功能：①权限申请
 *      ②查看权限是否授权
 */
public class PermissionRxUtil {

    private final RxPermissions rxPermissions;

    private Disposable disposable;

    public PermissionRxUtil(FragmentActivity fragmentActivity) {
        rxPermissions = new RxPermissions(fragmentActivity);
    }

    public PermissionRxUtil(Fragment fragment) {
        rxPermissions = new RxPermissions(fragment);
    }

    /**
     * 申请权限
     */
    public void apply(final ApplyEvent applyEvent, String... permissions) {
        //同时申请多个权限：
        disposable = rxPermissions.request(permissions)
                .subscribe(new Consumer<Boolean>() {
                               @Override
                               public void accept(Boolean b) throws Exception {
                                   applyEvent.accept(b);
//                                   if (b) {
//                                       //申请的权限全部允许
//                                       ToastUtil.showToast(context, "允许了权限");
//                                   } else {
//                                       //只要有一个权限被拒绝，就会执行
//                                       ToastUtil.showToast(context, "未授权权限，部分功能不能使用");
//                                   }
                               }
                           }
                );

    }

    /**
     * 查看是否授权
     */
    public void findAuthorize(final AuthorizeEvent authorizeEvent, String... permissions) {

        //监听具体的某一些权限是否进行了授权
        disposable = rxPermissions.requestEach(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            authorizeEvent.accept(2, permission.name);
                            // `permission.name` is granted !
                            // 允许
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            authorizeEvent.accept(1, permission.name);
                            // Denied permission without ask never again
                            // 询问
                        } else {
                            authorizeEvent.accept(0, permission.name);
                            // Denied permission with ask never again
                            // Need to go to the settings
                            // 拒绝
                        }
                    }
                });
    }




    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public interface ApplyEvent {
        void accept(Boolean b);
    }

    public interface AuthorizeEvent {
        //授权状态：2已授权状态；1询问是否授权状态；0拒绝访问状态
        void accept(int state, String name);
    }



//    private void test1(){
//        //相机的权限
//        String[] perms = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        //打电话的权限
////        String[] perms = {Manifest.permission.CALL_PHONE};
//
//        apply(new ApplyEvent() {
//            @Override
//            public void accept(Boolean b) {
//                if(b){
//                    //授权
//                }else{
//                    //未授权
//                }
//            }
//        },perms);
//    }
}
