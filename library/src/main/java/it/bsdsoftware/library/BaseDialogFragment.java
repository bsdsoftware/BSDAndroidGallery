package it.bsdsoftware.library;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.Window;
import java.util.ArrayList;
import java.util.List;

import it.bsdsoftware.imagelibrary.R;

/**
 * Created by Simone on 29/10/15.
 */
public class BaseDialogFragment extends DialogFragment{

    protected boolean showTitle = true;
    private int width, height;
    protected boolean lightTheme = true;
    protected List<BSDImage> gallery = new ArrayList<>();
    private boolean cancel = true;
    protected int titleLayout = -1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        width = (int)getActivity().getResources().getDimension(R.dimen.bsd_grid_size);
        height = (int)getActivity().getResources().getDimension(R.dimen.bsd_grid_size);
        if(!showTitle) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
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
        if(cancel)
            BSDUtility.deleteContentWorkingDirectory();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isLightTheme() {
        return lightTheme;
    }

    public List<BSDImage> getGallery() {
        return gallery;
    }

    protected void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public void setTitleLayout(int titleLayout) {
        this.titleLayout = titleLayout;
        this.showTitle = false;
    }
}
