package it.bsdsoftware.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import it.bsdsoftware.imagelibrary.R;

/**
 * Created by Simone on 29/10/15.
 */
public class BSDImage {

    private String extension;
    private String base64File;
    private int res;
    private boolean isResources = false;
    private Drawable drawable;
    private Bitmap bitmap;
    private String imageTitle;
    private boolean themeLight = true;
    private boolean isClickable = false;


    public Drawable getImage(Context context){
        Drawable drw = null;
        if(drawable!=null)
            drw = drawable;
        else if(isResources) {
            drw = BSDUtility.getDrawableFromRes(res, context);
        }else if(bitmap!=null){
            drw = new BitmapDrawable(context.getResources(), bitmap);
        }else if(!base64File.equals("")) {
            if(BSDUtility.isImage(extension)){
                Bitmap bpm = BSDUtility.convertBase64IntoBitmap(base64File);
                drw = new BitmapDrawable(context.getResources(), bpm);
            }else{
                isClickable = true;
                int r;
                if(extension.toLowerCase().equals("pdf")){
                    if(themeLight)
                        r = R.drawable.ic_file_pdf;
                    else
                        r = R.drawable.ic_file_pdf;
                }/*else if(extension.toLowerCase().equals("txt")) {
                    if(themeLight)
                        r = R.drawable.ic_txt_black_48dp;
                    else
                        r = R.drawable.ic_txt_white_48dp;
                }*/else if(extension.toLowerCase().equals("tiff") || extension.toLowerCase().equals("tif")) {
                    if(themeLight)
                        r = R.drawable.ic_file_tiff;
                    else
                        r = R.drawable.ic_file_tiff;
                }else {
                    if(themeLight)
                        r = R.drawable.ic_file_gen;
                    else
                        r = R.drawable.ic_file_gen;
                }
                drw = BSDUtility.getDrawableFromRes(r, context);
            }
        }else {
            drw = BSDUtility.getDrawableFromRes(R.drawable.trasparent, context);
        }
        return drw;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getBase64File() {
        return base64File;
    }

    public void setBase64File(String base64File, String extension) {
        this.base64File = base64File;
        setExtension(extension);
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.isResources = true;
        this.res = res;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public void setThemeLight(boolean themeLight) {
        this.themeLight = themeLight;
    }

    public boolean isClickable() {
        return isClickable;
    }

    public boolean isBase64(){
        return !(base64File == null || base64File.equals(""));
    }
}
