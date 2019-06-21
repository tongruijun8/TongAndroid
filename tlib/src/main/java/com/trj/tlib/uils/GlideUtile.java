package com.trj.tlib.uils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * 图片加载
 *
 *
 * 注：①带圆角的控件(可以使用com.trj.tlib.views.TRoundImageView控件)
 *
 */
public class GlideUtile {

    /**
     * 一般的加载
     * @param context
     * @param path
     * @param imageView
     */
    public static void bindImageView(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }

    /**
     * 一般的加载
     * @param context
     * @param path
     * @param resourceId 占位图片
     * @param imageView
     */
    public static void bindImageView(Context context, Object path,
                                     @DrawableRes int resourceId, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions.noTransformation()
                        .placeholder(resourceId))
                .transition(withCrossFade())
                .into(imageView);
    }

    /**
     * 一般的加载
     * @param context
     * @param path
     * @param errorResourceId 错误图片
     * @param imageView
     */
    public static void bindImageView2(Context context, Object path,
                                     @DrawableRes int errorResourceId, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions.noTransformation()
                        .error(errorResourceId))
                .transition(withCrossFade())
                .into(imageView);
    }

    /**
     * 一般的加载
     * @param context
     * @param path
     * @param resourceId  占位图片
     * @param errorResourceId  加载错误的图片
     * @param imageView
     */
    public static void bindImageView(Context context, Object path,@DrawableRes int resourceId,
                                     @DrawableRes int errorResourceId, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions.noTransformation()
                        .placeholder(resourceId)
                        .error(errorResourceId))
                .transition(withCrossFade())
                .into(imageView);
    }

    /**
     * 带圆角
     * @param context
     * @param path
     * @param radius
     * @param errorImgResourceId
     * @param imageView
     *
     * 注：(可以使用com.trj.tlib.views.TRoundImageView控件)
     */
    public static void bindImageViewC(Context context, Object path,int radius, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions.bitmapTransform(new GlideRoundTransform(radius)))
                .transition(withCrossFade())
                .into(imageView);
    }

    /**
     * 带圆角
     * @param context
     * @param path
     * @param radius
     * @param errorImgResourceId
     * @param imageView
     *
     * 注：(可以使用com.trj.tlib.views.TRoundImageView控件)
     */
    public static void bindImageViewC(Context context, Object path,int radius,
                                      @DrawableRes int resourceId, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius))
                        .placeholder(resourceId))
                .transition(withCrossFade())
                .into(imageView);
    }
    
    public static void bindImageViewC2(Context context, Object path,int radius,
                                     @DrawableRes int errorResourceId, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius))
                        .error(errorResourceId))
                .transition(withCrossFade())
                .into(imageView);
    }

    /**
     * 带圆角
     * @param context
     * @param path
     * @param radius
     * @param resourceId  占位图片
     * @param errorResourceId  加载错误的图片
     * @param imageView
     */
    public static void bindImageViewC(Context context, Object path,int radius,@DrawableRes int resourceId,
                                     @DrawableRes int errorResourceId, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius))
                        .placeholder(resourceId)
                        .error(errorResourceId))
                .transition(withCrossFade())
                .into(imageView);
    }

    /**
     * 圆形图片
     * @param context
     * @param path
     * @param resourceId 占位图片
     * @param imageView
     */
    public static void bindImageViewRound(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .transition(withCrossFade())
                .into(imageView);

    }

    /**
     * 圆形图片
     * @param context
     * @param path
     * @param resourceId 占位图片
     * @param imageView
     */
    public static void bindImageViewRound(Context context, Object path,
                                     @DrawableRes int resourceId, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                        .placeholder(resourceId))
                .transition(withCrossFade())
                .into(imageView);

    }

    /**
     * 圆形图片
     * @param context
     * @param path
     * @param errorResourceId 加载错误的图片
     * @param imageView
     */
    public static void bindImageViewRound2(Context context, Object path,
                                     @DrawableRes int errorResourceId, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                        .error(errorResourceId))
                .transition(withCrossFade())
                .into(imageView);

    }

    /**
     * 圆形图片
     * @param context
     * @param path
     * @param resourceId  占位图片
     * @param errorResourceId  加载错误的图片
     * @param imageView
     */
    public static void bindImageViewRound(Context context, Object path,@DrawableRes int resourceId,
                                      @DrawableRes int errorResourceId, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                        .placeholder(resourceId)
                        .error(errorResourceId))
                .transition(withCrossFade())
                .into(imageView);
    }

}
