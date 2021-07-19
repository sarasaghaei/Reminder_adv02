package ir.sarasaghaei.reminder_adv02.Ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import ir.sarasaghaei.reminder_adv02.R;
import ir.sarasaghaei.reminder_adv02.database.ReminderDBHelper;
import ir.sarasaghaei.reminder_adv02.entity.Reminder;
import ir.sarasaghaei.reminder_adv02.widget.AppWidget;
import lal.adhish.gifprogressbar.GifView;

public class AddReminderActivity extends AppCompatActivity  {
    Button btn_addlist,btn_edit, btn_addtime;
    Spinner spinner;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView tv_date,tv_time;
    EditText et_detail;
    final Calendar date = Calendar.getInstance();
    final Calendar time = Calendar.getInstance();
    String[] Categories ={"Work", "Personally","Wiseliest"};
    Reminder reminder;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        //-----set date now system
        mYear = date.get(Calendar.YEAR);
        mMonth = date.get(Calendar.MONTH);
        mDay = date.get(Calendar.DAY_OF_MONTH);

        //-----set time now system
        mHour = time.get(Calendar.HOUR_OF_DAY);
        mMinute = time.get(Calendar.MINUTE);

        Intent intent = getIntent();
        init();
        int id = intent.getIntExtra("id", 0);
        Toast.makeText(this, "id = " + id, Toast.LENGTH_SHORT).show();
        if (id != 0){
            EditRemoveReminder(id);

        }

    }

    private void EditRemoveReminder(int id) {
        reminder = new ReminderDBHelper(AddReminderActivity.this).select(id);
        et_detail.setText(reminder.getDetail());
        tv_date.setText(reminder.getDate());
        tv_time.setText(reminder.getTime());

        btn_addlist.setVisibility(View.GONE);
        btn_edit = findViewById(R.id.btn_edit);
        btn_edit.setVisibility(View.VISIBLE);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddReminderActivity.this, "Edit", Toast.LENGTH_SHORT).show();
                updateWidget(AddReminderActivity.this);
                finish();
            }
        });


    }

    private void init() {
        GifView gf_add= findViewById(R.id.gf_add);
        gf_add.setImageResource(R.drawable.listreminder);
        
        spinner_category();

        et_detail = findViewById(R.id.et_detail);
        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        btn_addtime = findViewById(R.id.btn_add_date);

        String date = mYear + "-" + (mMonth +1) + "-" + mDay;
        tv_date.setText(date );
        String time = mHour + ":" + mMinute;
        tv_time.setText(time);

        btn_addtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Launch Date Picker Dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddReminderActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                monthOfYear -= 1;
                                String date = year + "-" + monthOfYear + "-" + dayOfMonth;
                                tv_date.setText(date );
                            }

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddReminderActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                               String time = hourOfDay + ":" + minute;

                                tv_time.setText(time);
                            }

                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });



        btn_addlist = findViewById(R.id.btn_add);
        btn_addlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String detail  = et_detail.getText().toString();
                String date = tv_date.getText().toString();
                String time = tv_time.getText().toString();

                if(!category.equals("") && !detail.equals("") &&
                   !date.equals("") && !time.equals("")) {
                    if (detail.length() >200){
                        Toast.makeText(AddReminderActivity.this, "detail length is not allowed  ", Toast.LENGTH_SHORT).show();
                    }
                    Reminder new_record = new Reminder(category,detail,date,time);
                    try {
                        long result = new ReminderDBHelper(AddReminderActivity.this).insert(new_record);
                        Toast.makeText(AddReminderActivity.this, "Successfully added ", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception ex){
                        Log.e("TAG", "onClick: " + ex.getMessage() );
                    }
                    updateWidget(AddReminderActivity.this);
                    finish();
                }else {
                    Toast.makeText(AddReminderActivity.this, "Empty values error ", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public static void updateWidget(Context context) {
        Intent i = new Intent(context, AppWidget.class);
        i.setAction(AppWidget.ADD_REMINDER_TO_DB);
        context.sendBroadcast(i);
    }

    private void spinner_category() {
        spinner = findViewById(R.id.sp_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddReminderActivity.this,
                android.R.layout.simple_spinner_dropdown_item,Categories);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = Categories[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}