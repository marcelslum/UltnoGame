package com.marcelslum.ultnogame;


public class AsyncsTasks{

  public static AsyncTask initLoader;
  public void cancelAll(){
      if (initLoader != null){
          Log.e(TAG, "initLoader not null");
          if (task.getStatus() == Status.RUNNING){
            task.cancel(true);
          }
      }
   }
}
