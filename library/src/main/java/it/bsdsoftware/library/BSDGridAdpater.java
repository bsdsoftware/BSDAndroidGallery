package it.bsdsoftware.library;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import it.bsdsoftware.imagelibrary.R;

/**
 * Created by Simone on 29/10/15.
 */
class BSDGridAdpater extends ArrayAdapter<BSDImage> {

    private Activity context;

    public BSDGridAdpater(Activity context) {
        super(context, R.layout.thumb);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderInfo viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.thumb, parent, false);
            viewHolder = new ViewHolderInfo(convertView);
            convertView.setTag(viewHolder);
        }

        if (viewHolder == null && convertView != null) {
            Object tag = convertView.getTag();
            if (tag instanceof ViewHolderInfo) {
                viewHolder = (ViewHolderInfo) tag;
            }
        }

        final BSDImage image = getItem(position);
        if (image != null && viewHolder != null) {
            viewHolder.icon.setImageDrawable(image.getImage(context));
            viewHolder.title.setText(image.getImageTitle());
        }
        return convertView;
    }

    class ViewHolderInfo {
        private final ImageView icon;
        private final TextView title;

        public ViewHolderInfo(View view) {
            title = (TextView) view.findViewById(R.id.textview_title);
            icon = (ImageView) view.findViewById(R.id.imageview_thumb);
        }
    }
}
