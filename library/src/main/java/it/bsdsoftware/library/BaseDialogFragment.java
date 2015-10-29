package it.bsdsoftware.library;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.Window;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 29/10/15.
 */
public class BaseDialogFragment extends DialogFragment{

    private boolean showTitle = true;
    private int width = 600, height = 600;
    protected boolean lightTheme = true;
    protected List<BSDImage> gallery = new ArrayList<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if(!showTitle)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onStart() {
        BSDUtility.createWorkingPathIfIsNotExist();
        if(getDialog()!=null){
            getDialog().getWindow().setLayout(width, height);
        }
        super.onStart();
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void setLightTheme(boolean lightTheme) {
        this.lightTheme = lightTheme;
        setLightThemeInGallery();
    }

    public void setGallery(List<BSDImage> gallery) {
        this.gallery = gallery;
        setLightThemeInGallery();
    }

    private void setLightThemeInGallery(){
        for(BSDImage img : gallery){
            img.setThemeLight(lightTheme);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BSDUtility.deleteContentWorkingDirectory();
    }
}
