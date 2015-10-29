package it.bsdsoftware.library;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Simone on 29/10/15.
 */
public class BSDUtility {

    public static String workingDirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/BSDImage";

    public static Drawable getDrawableFromRes(int res, Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getResources().getDrawable(res, context.getTheme());
        else
            return context.getResources().getDrawable(res);
    }

    public static byte[] convertBase64IntoByte(String base64){
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return decodedString;
    }

    public static Bitmap convertBase64IntoBitmap(String base64){
        byte[] decodedString = convertBase64IntoByte(base64);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public static File convertBase64IntoFile(String base64, String path){
        File dwldsPath = new File(path);
        byte[] pdfAsBytes = convertBase64IntoByte(base64);
        FileOutputStream os;
        try {
            os = new FileOutputStream(dwldsPath, false);
            os.write(pdfAsBytes);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dwldsPath;
    }

    public static boolean isImage(String extension){
        if(extension == null || extension.equals(""))
            return true;
        extension = extension.toLowerCase();
        switch (extension){
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
           // case "tiff":
           // case "tif":
            case "bmp":
                return true;
            default:
                return false;
        }
    }

    public static void handleClickItem(FragmentActivity context, BSDImage image, List<BSDImage> gallery){
        if(isImage(image.getExtension())){
            Toast.makeText(context, image.getImageTitle(), Toast.LENGTH_LONG).show();
            BSDImageSwitcherDialogFragment dialog = new BSDImageSwitcherDialogFragment();
            dialog.setGallery(gallery);
            dialog.show(context.getSupportFragmentManager(), "tag_switcher");
        }else {
            String path = String.format("%s/%s.%s", workingDirectory, image.getImageTitle(), image.getExtension());
            convertBase64IntoFile(image.getBase64File(), path);
            Intent intent;
            switch (image.getExtension().toLowerCase()) {
                case "pdf":
                    File file = new File(path);
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    break;
                case "txt":
                    intent = new Intent(Intent.ACTION_EDIT);
                    Uri uri = Uri.parse("file:" + path);
                    intent.setDataAndType(uri, "text/plain");
                    break;
                case "tiff":
                case "tif":
                    file = new File(path);
                    intent = new Intent();
                    intent.setAction(android.content.Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), "image/tiff");
                    break;
                default:
                    file = new File(path);
                    String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension("." + image.getExtension());
                    intent = new Intent();
                    intent.setAction(android.content.Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), mime);
            }
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "Nessuna applicazione installata per visualizzare file con estensione ." + image.getExtension(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void createWorkingPathIfIsNotExist(){
        File file = new File(workingDirectory);
        if(!file.exists()){
            file.mkdir();
        }
    }

    public static void deleteContentWorkingDirectory(){
        deleteDirectoryContent(workingDirectory);
    }

    private static void deleteDirectoryContent(String directoryPath){
        File dir = new File(directoryPath);
        if (dir.exists() && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File file = new File(dir, children[i]);
                if(file.isDirectory()){
                    deleteDirectoryContent(file.getAbsolutePath());
                }else{
                    file.delete();
                }
            }
            dir.delete();
        }
    }

}