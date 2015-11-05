package it.bsdsoftware.library;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import it.bsdsoftware.imagelibrary.R;

/**
 * Created by Simone on 29/10/15.
 */
public class BSDImageSwitcherDialogFragment extends BaseDialogFragment {

    private ImageSwitcher imageSwitcher;
    private int indexArray = 0;
    private View title;

    public BSDImageSwitcherDialogFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bsdimage_switcher_dialog, container, false);
        if(titleLayout!=-1){
            FrameLayout fl = (FrameLayout) rootView.findViewById(R.id.top_container);
            title = inflater.inflate(titleLayout, fl);
        }

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
            setTitle(gallery.get(indexArray).getImageTitle());
            setListener(gallery.get(indexArray));

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    indexArray++;
                    if(indexArray>gallery.size()-1)
                        indexArray = 0;
                    BSDImage img = gallery.get(indexArray);
                    imageSwitcher.setImageDrawable(img.getImage(getActivity()));
                    setTitle(img.getImageTitle());
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
                    setTitle(img.getImageTitle());
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
                    BSDUtility.handleClickItem(getActivity(), image, 0, null);
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

    public void setTitle(String title){
        if(showTitle){
            getDialog().setTitle(title);
        }
        else if(titleLayout != -1){
            TextView textView = (TextView) getDialog().findViewById(android.R.id.title);
            if(textView!=null)
                textView.setText(title);
            else if(this.title!=null){
                textView = (TextView) this.title.findViewById(android.R.id.title);
                
                if(textView!=null)
                    textView.setText(title);
            }
        }
    }
}
