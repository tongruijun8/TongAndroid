package com.trj.tlib.uils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.DrawableMarginSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.IconMarginSpan;
import android.text.style.ImageSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;

/**
 * 用于改变文字相关样式：如文字大小、颜色等
 *
 * 使用：如 new TextStyleHelper("string").addBackColorSpan().show();
 */
@SuppressWarnings("JavadocReference")
public class TextStyleHelper {


    private SpannableStringBuilder spannableString;


    //    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括，即在指定范围的前面和后面插入新字符都不会应用新样式
//    Spannable.SPAN_EXCLUSIVE_INCLUSIVE ：前面不包括，后面包括。即仅在范围字符的后面插入新字符时会应用新样式
//    Spannable.SPAN_INCLUSIVE_EXCLUSIVE ：前面包括，后面不包括。
//    Spannable.SPAN_INCLUSIVE_INCLUSIVE ：前后都包括。
    private int flags;

    public TextStyleHelper(String sourceStr) {
        this(sourceStr, -1);
    }

    public TextStyleHelper(String sourceStr, int flags) {
        if (flags == -1) {//默认值
            this.flags = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;
        }
        spannableString = new SpannableStringBuilder(sourceStr);
    }

    /**
     * 光栅效果
     */
    public TextStyleHelper addRasterizerSpan(int start, int end) {
        StrikethroughSpan span = new StrikethroughSpan();
        spannableString.setSpan(span, start, end, flags);
        return this;
    }


    /**
     * 修饰效果:模糊
     *
     * @param radius 指定要模糊的范围，必须大于0
     * @param style
     * @see BlurMaskFilter.Blur.NORMAL 对应物体边界的内部和外部都将进行模糊
     * @see BlurMaskFilter.Blur.SOLID 图像边界外产生一层与Paint颜色一致阴影效果，不影响图像的本身
     * @see BlurMaskFilter.Blur.OUTER  图像边界外产生一层阴影，图像内部镂空
     * @see BlurMaskFilter.Blur.INNER 在图像边界内部产生模糊效果，外部不绘制
     *
     *
     */
    public TextStyleHelper addBlurMaskfilteSpan(float radius, BlurMaskFilter.Blur style, int start, int end) {
        //模糊(BlurMaskFilter)
        MaskFilterSpan maskFilterSpan = new MaskFilterSpan(new BlurMaskFilter(radius, style));
        spannableString.setSpan(maskFilterSpan, start, end, flags);
        return this;
    }


    /**
     * 超链接
     */
    public TextStyleHelper addUrlSpan(String url, int start, int end) {
        URLSpan span = new URLSpan("tel:0123456789");
        spannableString.setSpan(span, start, end, flags);
        return this;
//        tv.setMovementMethod(LinkMovementMethod.getInstance());//如果控件需要点击效果，需要加上这句话
    }


    /**
     * 超链接点击事件
     */
    public TextStyleHelper addUrlSpan(ClickableSpan span, int start, int end) {
        spannableString.setSpan(span, start, end, flags);
        return this;
//        tv.setMovementMethod(LinkMovementMethod.getInstance());//如果控件需要点击效果，需要加上这句话
    }

    /**
     * 文字背景颜色
     */
    public TextStyleHelper addBackColorSpan(@ColorInt int color, int start, int end) {
        BackgroundColorSpan span = new BackgroundColorSpan(color);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 设置文字字体、文字样式（粗体、斜体、等等）、文字颜色状态、文字下划线颜色状态等等。
     * @param family  "monospace", "serif", and "sans-serif" 三种系统字体：
     * @param style {@link #addStyleSpan }
     * @param size 大小
     * @param color 颜色
     * @param linkColor 颜色
     * @param start
     * @param end
     * @return
     */
    public TextStyleHelper addTextAppearanceSpan(String family, int style, int size,
                                                 ColorStateList color, ColorStateList linkColor,
                                                 int start, int end) {
        TextAppearanceSpan span = new TextAppearanceSpan(family, style, size, color, linkColor);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 文字颜色
     */
    public TextStyleHelper addForeColorSpan(@ColorInt int color, int start, int end) {
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 文字大小
     */
    public TextStyleHelper addFontSizeSpan(int size, int start, int end) {
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(size);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 文字大小
     */
    /**
     * 设置文字相对大小，指相对于文本设定的大小的相对比例。
     * @param proportion 比例大小
     * @param start
     * @param end
     * @return
     */
    public TextStyleHelper addRelativeSizeSpan(float proportion, int start, int end) {
        RelativeSizeSpan span = new RelativeSizeSpan(proportion);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 文字字体
     * @param family "monospace", "serif", and "sans-serif" 三种系统字体：
     */
    public TextStyleHelper addTypefaceSpan(String family, int start, int end) {
        TypefaceSpan span = new TypefaceSpan(family);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 文字字体
     * @param typeface 指定字体
     * @param start
     * @param end
     * @return
     */
    public TextStyleHelper addTypefaceSpan(Typeface typeface, int start, int end) {
        TypefaceSpan span = new TypefaceSpan(typeface);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }


    /**
     * 粗体，斜体
     *
     * @param style {
     * @see Typeface.BOLD_ITALIC
     * @see Typeface.NORMAL
     * @see Typeface.BOLD
     * @see Typeface.ITALIC
     */
    public TextStyleHelper addStyleSpan(int style, int start, int end) {
        StyleSpan span = new StyleSpan(style);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 上标
     * @param start
     * @param end
     * @return
     */
    public TextStyleHelper addSuperscriptSpan(int start, int end) {
        SuperscriptSpan span = new SuperscriptSpan();
        spannableString.setSpan(span, start, end, flags);
        return this;
    }


    /**
     * 下标
     * @param start
     * @param end
     * @return
     */
    public TextStyleHelper addSubscriptSpan(int start, int end) {
        SubscriptSpan span = new SubscriptSpan();
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 文本水平方向缩放：
     * @param proportion  proportion = 1 不变,proportion<1 压缩，proportion>1 拉伸
     * @param start
     * @param end
     * @return
     */
    public TextStyleHelper addScaleXSpan(float proportion, int start, int end) {
        ScaleXSpan span = new ScaleXSpan(proportion);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }




    /**
     * 下划线
     *
     * @param start
     * @param end
     */
    public TextStyleHelper addUnderLineSpan(int start, int end) {
        UnderlineSpan span = new UnderlineSpan();
        spannableString.setSpan(span, start, end, flags);
        return this;
    }


    /**
     * 设置文本缩进。
     * @param first 首行缩进
     * @param rest 其它行缩进
     * @param start
     * @param end
     * @return
     */
    public TextStyleHelper addLeadingMarginSpan(int first, int rest, int start, int end) {
        LeadingMarginSpan span = new LeadingMarginSpan.Standard(first, rest);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }


    /**
     * 删除线
     */
    public TextStyleHelper addStrikeSpan(int start, int end) {
        StrikethroughSpan span = new StrikethroughSpan();
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 图片
     */
    public TextStyleHelper addImageSpan(@NonNull Drawable drawable, int start, int end) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    public TextStyleHelper addImageSpan(@NonNull Context context, @DrawableRes int resourceId,
                                        int start, int end) {
        ImageSpan span = new ImageSpan(context, resourceId, ImageSpan.ALIGN_BASELINE);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 文本插入图片+Margin。
     * @param bitmap
     * @param pad
     * @param start
     * @param end
     * @return
     */
    public TextStyleHelper addIconMarginSpan(@NonNull Bitmap bitmap, int pad,
                                             int start, int end) {
        IconMarginSpan span = new IconMarginSpan(bitmap, pad);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }
    public TextStyleHelper addDrawableMarginSpan(@NonNull Drawable drawable, int pad,
                                                 int start, int end) {
        DrawableMarginSpan span = new DrawableMarginSpan(drawable, pad);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 类似于HTML中的<li>标签的圆点效果。
     * @param gapWidth 圆点的宽度
     * @param color 圆点的颜色
     * @param start
     * @param end
     * @return
     */
    public TextStyleHelper addBulletSpan(int gapWidth, @ColorInt int color,
                                         int start, int end) {
        BulletSpan span = new BulletSpan(gapWidth, color);
        spannableString.setSpan(span, start, end, flags);
        return this;
    }

    /**
     * 显示
     *
     * @return
     */
    public SpannableStringBuilder show() {
        return spannableString;
    }


}
