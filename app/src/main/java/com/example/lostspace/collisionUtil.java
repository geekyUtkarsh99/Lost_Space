package com.example.lostspace;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;

import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class collisionUtil {

    public  boolean checkBoxCollision(int x1, int y1, int x2, int y2, int w,int h){

       Rect r1 = new Rect(x2,y2,x2 + w,y2 + h);

       return r1.contains(x1,y1);

    }

   public  boolean checkPixellCollision(Bitmap image1,Bitmap image2,int x1,int x2,int y1,int y2){

       // initialization
       double width1 = x1 + image1.getWidth() -1,
               height1 = y1 + image1.getHeight() -1,
               width2 = x2 + image2.getWidth() -1,
               height2 = y2 + image2.getHeight() -1;
       int xstart = (int) Math.max(x1, x2),
               ystart = (int) Math.max(y1, y2),
               xend   = (int) Math.min(width1, width2),
               yend   = (int) Math.min(height1, height2);
       // intersection rectangle
       int toty = Math.abs(yend - ystart);
       int totx = Math.abs(xend - xstart);
       for (int y=1;y < toty-1;y++){
           int ny = Math.abs(ystart - (int) y1) + y;
           int ny1 = Math.abs(ystart - (int) y2) + y;
           for (int x=1;x < totx-1;x++) {
               int nx = Math.abs(xstart - (int) x1) + x;
               int nx1 = Math.abs(xstart - (int) x2) + x;
               try{
                   if (((image1.getPixel(nx,ny) & 0xFF000000) != 0x00) && ((image2.getPixel(nx1,ny1) & 0xFF000000) != 0x00)) {
                       // collide!!
                       image1 = null;
                       image2 = null;
                       return true;

                   }
               }catch(Exception e){

               }
           }
       }
       return false;


}

   public boolean checkPixelCollision(Bitmap bitmap1,Bitmap bitmap2,int x1,int x2,int y1, int y2){


       Rect bounds1 = new Rect(x1, y1, x1 + bitmap1.getWidth(), y1 + bitmap1.getHeight());
       Rect bounds2 = new Rect(x2, y2, x2 + bitmap2.getWidth(), y2 + bitmap2.getHeight());

       if (Rect.intersects(bounds1, bounds2)) {
           Rect collisionBounds = getCollisionBounds(bounds1, bounds2);
           for (int i = collisionBounds.left; i < collisionBounds.right; i++) {
               for (int j = collisionBounds.top; j < collisionBounds.bottom; j++) {
                   int bitmap1Pixel = bitmap1.getPixel(i - x1, j - y1);
                   int bitmap2Pixel = bitmap2.getPixel(i - x2, j - y2);
                   if (isFilled(bitmap1Pixel) && isFilled(bitmap2Pixel)) {

                       bitmap1 = null;

                       bitmap2 = null;
                       return true;
                   }
               }
           }
       }

       bitmap1 = null;

       bitmap2 = null;
       return false;


   }

    private static Rect getCollisionBounds(Rect rect1, Rect rect2) {
        int left = Math.max(rect1.left, rect2.left);
        int top = Math.max(rect1.top, rect2.top);
        int right = Math.min(rect1.right, rect2.right);
        int bottom = Math.min(rect1.bottom, rect2.bottom);
        return new Rect(left, top, right, bottom);
    }

    private static boolean isFilled(int pixel) {
        return pixel != Color.TRANSPARENT;
    }



}
