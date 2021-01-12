package com.example.mvvmkotlinexample.db;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by satnamsingh on 23/05/18.
 */

public class QueryData {

    public static void onUpdateN(final Context context, String status, String name ) {

        new onUpdate(context,status ,name).execute();
    }

    public static class onUpdate extends AsyncTask<Void, Void, Void> {
        Context context;

        // int count;
        String name,status;

        public onUpdate(Context context, String status, String name ) {
            this.context = context;

            this.status = status;
            this.name=name;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase.getAppDatabase(context).medicineDB().updateCheck(status,name);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //callback.checkUpdateStatusCallback(true);
        }

        @Override
        protected void onPreExecute() {
        }
    }
}
