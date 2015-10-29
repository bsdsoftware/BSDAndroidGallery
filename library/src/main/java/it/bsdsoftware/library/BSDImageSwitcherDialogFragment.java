package it.bsdsoftware.library;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;

import it.bsdsoftware.imagelibrary.R;

/**
 * Created by Simone on 29/10/15.
 */
public class BSDImageSwitcherDialogFragment extends BaseDialogFragment {

    private ImageSwitcher imageSwitcher;
    private int indexArray = 0;

    public BSDImageSwitcherDialogFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bsdimage_switcher_dialog, container, false);

        Bundle args = getArguments();

        indexArray = args.getInt(getString(R.string.index_extra), 0);

        ImageButton next = (ImageButton) rootView.findViewById(R.id.btn_next_image);
        ImageButton prev = (ImageButton) rootView.findViewById(R.id.btn_prev_image);
        if(!lightTheme){
            next.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
            prev.setImageResource(R.drawable.ic_keyboard_arrow_left_white_24dp);
        }
        imageSwitcher = (ImageSwitcher) rootView.findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView myView = new ImageView(getActivity());
                myView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                myView.setLayoutParams(new ImageSwitcher.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                return myView;
            }
        });

        if(gallery.size()>0){
            imageSwitcher.setImageDrawable(gallery.get(indexArray).getImage(getActivity()));
            getDialog().setTitle(gallery.get(indexArray).getImageTitle());
            setListener(gallery.get(indexArray));

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    indexArray++;
                    if(indexArray>gallery.size()-1)
                        indexArray = 0;
                    BSDImage img = gallery.get(indexArray);
                    imageSwitcher.setImageDrawable(img.getImage(getActivity()));
                    getDialog().setTitle(img.getImageTitle());
                    setListener(img);
                }
            });
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    indexArray--;
                    if (indexArray < 0)
                        indexArray = gallery.size() - 1;
                    BSDImage img = gallery.get(indexArray);
                    imageSwitcher.setImageDrawable(img.getImage(getActivity()));
                    getDialog().setTitle(img.getImageTitle());
                    setListener(img);
                }
            });
        }
        return rootView;
    }

    private void setListener(final BSDImage image){
        if(image.isClickable()){
            imageSwitcher.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BSDUtility.handleClickItem(getActivity(), image, gallery, 0);
                }
            });
        }else {
            imageSwitcher.setOnClickListener(null);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
