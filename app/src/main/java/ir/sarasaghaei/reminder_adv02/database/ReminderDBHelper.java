package ir.sarasaghaei.reminder_adv02.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ir.sarasaghaei.reminder_adv02.entity.Reminder;

public class ReminderDBHelper extends DBHelper{
    public Context context;
    private final String TABLE_NAME = Reminder.class.getSimpleName();
    private final String FILD_Id = "id" , FILD_Category = "category",
                    FILD_Detail = "detail", FILD_Date = "date", FILD_Time = "time";

    public ReminderDBHelper(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insert(Reminder reminder_record){
        long result = -1;
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put(FILD_Category, reminder_record.getCategory());
            cv.put(FILD_Detail, reminder_record.getDetail());
            cv.put(FILD_Date, reminder_record.getDate());
            cv.put(FILD_Time, reminder_record.getTime());

            result = db.insert(TABLE_NAME, null, cv);
        } catch (Exception ex) {
            Log.e("TAG", "insert: " + ex.getMessage() );
        }
        return result;

    }

    public List<Reminder> select(){
        List<Reminder> result = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] Columns = {FILD_Id,FILD_Category,FILD_Detail,FILD_Date,FILD_Time};

        Cursor cursor = db.query(TABLE_NAME,Columns,null,null,null,null,null);
        if (cursor.getCount() != 0 ) {

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(FILD_Id));
                String category = cursor.getString(cursor.getColumnIndex(FILD_Category));
                String detail = cursor.getString(cursor.getColumnIndex(FILD_Detail));
                String date = cursor.getString(cursor.getColumnIndex(FILD_Date));
                String time = cursor.getString(cursor.getColumnIndex(FILD_Time));

                Reminder reminder_item = new Reminder(id, category, detail, date, time);
                result.add(reminder_item);
            }
            cursor.close();
            db.close();
        }
        return result;
    }

    public Reminder select(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] Columns = {FILD_Id,FILD_Category,FILD_Detail,FILD_Date,FILD_Time};

        Cursor cursor = db.query(TABLE_NAME,Columns,"id=?", new String[]{String.valueOf(id)},null,null,null);
        if (cursor.getCount() != 0 ) {
                id = cursor.getInt(cursor.getColumnIndex(FILD_Id));
                String category = cursor.getString(cursor.getColumnIndex(FILD_Category));
                String detail = cursor.getString(cursor.getColumnIndex(FILD_Detail));
                String date = cursor.getString(cursor.getColumnIndex(FILD_Date));
                String time = cursor.getString(cursor.getColumnIndex(FILD_Time));

                Reminder reminder_item = new Reminder(id, category, detail, date, time);
                cursor.close();
                db.close();
                return reminder_item;
        }
        return null;

    }
}
