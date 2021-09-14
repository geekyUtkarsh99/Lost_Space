package com.example.lostspace.dataBlock;

import android.content.Context;
import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class dataHandler implements Serializable {

  private File f;
  private FileReader r;
  private FileWriter w;
  private BufferedReader br;
  private BufferedWriter bw;
  private Context c;

     public dataHandler(Context c){
      this.c = c;
  }

       public void writeFile(byte[][] data,String filename) throws IOException {

         long bef,time;
         bef = System.currentTimeMillis(); //time before file creation



         f =new File(c.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + filename);
         if(!f.exists()){
             System.out.println("creating file ");
             w = new FileWriter(f);
             bw = new BufferedWriter(w);

             for(int i = 0; i < data.length; i++){

             byte[] dataE = data[i];
             String s = new String(dataE);
             bw.write(s);
             bw.newLine();
             }
             time = System.currentTimeMillis() - bef;
             System.out.println("generated ... [processing time] : " + time/60 + "seconds");
             bw.close();

         }else {
             System.out.println("file exists ");

         }


     }

    public byte[][] read(String filename) throws IOException {


       byte[][] data = new byte[2][];
       f = new File(c.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + filename);
       r =new FileReader(f);
       br = new BufferedReader(r);
       System.out.println("reading from file ... ");
       String s;
       int i = 0;
       while((s = br.readLine()) != null){
          if(i < data.length){
              byte[] dataE = s.getBytes();
              data[i] = dataE;
              i++;

          }

       }



        return  data;

    }

    public String getPath(String filename){

      f = new File(c.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + filename);
      if(f.exists()){
          return c.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + filename;
      }
      return null;
   }

   public void delete(String filename){
       f = new File(c.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + filename);
       if(f.exists()) {
           f.delete();
       }

   }

   public void saveChanges(byte[] data,String filename) throws IOException {

         f = new File(c.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + filename);
         f.delete();

       w = new FileWriter(f);
       bw = new BufferedWriter(w);

       String s = new String(data);
       System.out.println("saving");
       bw.write(s);
       bw.close();
       bw.flush();


   }

}
