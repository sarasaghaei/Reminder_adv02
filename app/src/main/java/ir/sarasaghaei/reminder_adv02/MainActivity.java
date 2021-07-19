package ir.sarasaghaei.reminder_adv02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ir.sarasaghaei.reminder_adv02.Ui.AddReminderActivity;
import ir.sarasaghaei.reminder_adv02.adapter.ReminderAdapter;
import ir.sarasaghaei.reminder_adv02.database.ReminderDBHelper;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingadd;
    ListView listView;
    ReminderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        listView = findViewById(R.id.lv_reminder);
        adapter = new ReminderAdapter(this,new ReminderDBHelper(this).select());
        listView.setAdapter(adapter);

        floatingadd = findViewById(R.id.flotingadd);
        floatingadd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddReminderActivity.class);
                startActivity(intent);
            }
        });
       /* btn = findViewById(R.id.btn);

// define Animation ((fade in))
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(5000);
// set Animation
        btn.setVisibility(View.GONE);
        btn.setVisibility(View.VISIBLE);
        btn.setAnimation(fadeIn);*/


        /*GifView gifView=(GifView)findViewById(R.id.gifloder);
        gifView.setImageResource(R.drawable.ab);*/

    }
}