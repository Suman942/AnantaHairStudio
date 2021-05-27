package com.freelance.anantahairstudio.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Raj on 25-01-2019.
 */

public class GlideHelper {
    public static void setImageView(Context context, ImageView view, String url) {
        RequestOptions options = new RequestOptions();
        options = options.fitCenter();
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
    }
    public static void setImageViewRoundedCorners(Context context, ImageView view, String url) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(),new RoundedCorners(20));
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(view);
    }

    public static void setImageViewRoundedCorners(Context context, ImageView view, String url, Integer placeholder) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.placeholder(placeholder).transforms(new CenterCrop(),new RoundedCorners(20));
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(view);
    }

    public static void setImageViewRoundedCorners(Context context, ImageView view, String url,
                                                  Integer height, Integer width, Integer radius,
                                                  Integer margin, Integer placeHolder) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.placeholder(placeHolder)
                .transforms(new CenterCrop(),new RoundedCorners(radius))
                .override(width,height);
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(view);
    }

    public static void setImageViewCustomRoundedCorners(Context context, ImageView view, String url, Integer radius,Integer placeHolder){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.placeholder(placeHolder).transforms(new CenterCrop(),new RoundedCorners(radius));
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(view);
    }

    public static void setImageViewCustomRoundedCorners(Context context, ImageView view, String url, Integer radius){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(),new RoundedCorners(radius));
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(view);
    }

    public static void setImageViewCustomRoundedCornersDrawable(Context context, ImageView view, int drawableId, Integer radius,Integer placeHolder){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.placeholder(placeHolder).transforms(new CenterCrop(),new RoundedCorners(radius));
        Glide.with(context)
                .load(drawableId)
                .apply(requestOptions)
                .into(view);
    }

    public static void setImageViewCustomRoundedCornersDrawable(Context context, ImageView view, int drawableId, Integer radius){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(),new RoundedCorners(radius));
        Glide.with(context)
                .load(drawableId)
                .apply(requestOptions)
                .into(view);
    }

    public static void setImageViewWithURI(Context context, ImageView view, Uri uri) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.fitCenter();
        Glide.with(context)
                .load(uri)
                .apply(requestOptions)
                .into(view);
    }

    public static void setImageViewWithDrawable(Context context, ImageView view, int drawableId) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.fitCenter();
        Glide.with(context)
                .load(drawableId)
                .apply(requestOptions)
                .into(view);
    } 
}
