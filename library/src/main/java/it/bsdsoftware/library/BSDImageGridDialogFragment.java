package it.bsdsoftware.library;


import android.app.Dialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import it.bsdsoftware.imagelibrary.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BSDImageGridDialogFragment extends BaseDialogFragment {

    private int numColumns = 3;
    private GridView gridView;

    public BSDImageGridDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bsdimage_grid_dialog, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridview_image);
        gridView.setNumColumns(numColumns);
        final BSDGridAdpater adapter = new BSDGridAdpater(getActivity());
        for(BSDImage img : gallery){
            adapter.add(img);
        }
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BSDImage image = adapter.getItem(position);
                BSDUtility.handleClickItem(getActivity(), image, gallery, position);
            }
        });

        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        if(gridView!=null)
            gridView.setNumColumns(numColumns);
    }
}
