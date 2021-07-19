package ir.sarasaghaei.reminder_adv02.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ir.sarasaghaei.reminder_adv02.Const;
import ir.sarasaghaei.reminder_adv02.entity.Reminder;

class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, Const.DB_NAME,null,Const.VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        CreatTable_reminder(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void CreatTable_reminder(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Reminder.class.getSimpleName() + "(\n" +
                "    id integer primary key, \n" +
                "    category nvarchar(100),\n" +
                "    detail nvarchar(200),\n" +
                "    date nvarchar(50),\n" +
                "    time nvarchar(50));" ;

        try
        {
            db.execSQL(query);
        }
        catch (Exception ex) {
            Log.e("TAG", "CreatTable_reminder: " + ex.getMessage() );
        }

    }
}
