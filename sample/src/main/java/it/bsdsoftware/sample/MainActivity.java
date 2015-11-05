package it.bsdsoftware.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import it.bsdsoftware.library.BSDImage;
import it.bsdsoftware.library.BSDImageGridDialogFragment;
import it.bsdsoftware.library.BSDImageSwitcherDialogFragment;

public class MainActivity extends AppCompatActivity {

    private List<BSDImage> gallery = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //base 64
        BSDImage image = new BSDImage();
        image.setBase64File(Base64Sample.base64PNG, "png");
        image.setImageTitle("PNG 1 (base 64)");
        gallery.add(image);
        image = new BSDImage();
        image.setBase64File(Base64Sample.base64JPG, "jpg");
        image.setImageTitle("JPG 2 (base 64)");
        gallery.add(image);
        image = new BSDImage();
        image.setBase64File(Base64Sample.base64JPEG, "jpeg");
        image.setImageTitle("JPEG 3 (base 64)");
        gallery.add(image);
        image = new BSDImage();
        image.setBase64File(Base64Sample.base64GIF, "gif");
        image.setImageTitle("GIF 4 (base 64)");
        gallery.add(image);
        image = new BSDImage();
        image.setBase64File(Base64Sample.base64PDF, "pdf");
        image.setImageTitle("PDF 5 (base 64)");
        gallery.add(image);
        image = new BSDImage();
        image.setBase64File(Base64Sample.base64TXT, "txt");
        image.setImageTitle("TXT 6 (base 64)");
        gallery.add(image);
        image = new BSDImage();
        image.setBase64File(Base64Sample.base64TIF, "tif");
        image.setImageTitle("TIFF 7 (base 64)");
        gallery.add(image);
        image = new BSDImage();
        image.setBase64File(Base64Sample.base64BMP, "bmp");
        image.setImageTitle("BMP 8 (base 64)");
        gallery.add(image);

        //resources
        image = new BSDImage();
        image.setRes(R.drawable.png_sample);
        image.setImageTitle("PNG  (res)");
        gallery.add(image);
        image = new BSDImage();
        image.setRes(R.drawable.jpg_sample);
        image.setImageTitle("JPG  (res)");
        gallery.add(image);
        image = new BSDImage();
        image.setRes(R.drawable.gif_sample);
        image.setImageTitle("GIF  (res)");
        gallery.add(image);


        Button openSwitcher = (Button) findViewById(R.id.open_image_switcher);
        openSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BSDImageSwitcherDialogFragment dialog = new BSDImageSwitcherDialogFragment();
                dialog.setGallery(gallery);
                dialog.show(getSupportFragmentManager(), "tag_switcher");
            }
        });

        Button openGrid = (Button) findViewById(R.id.open_image_grid);
        openGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BSDImageGridDialogFragment dialog = new BSDImageGridDialogFragment();
                dialog.setGallery(gallery);
                dialog.setLightTheme(false);
                dialog.setLayoutTop(R.layout.layout_top);
                dialog.show(getSupportFragmentManager(), "tag_grid");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
