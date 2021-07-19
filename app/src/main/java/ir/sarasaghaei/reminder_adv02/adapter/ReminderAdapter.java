package ir.sarasaghaei.reminder_adv02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ir.sarasaghaei.reminder_adv02.R;
import ir.sarasaghaei.reminder_adv02.entity.Reminder;

public class ReminderAdapter extends BaseAdapter {
    private Context context;
    private List<Reminder> reminders;

    public ReminderAdapter(Context context, List<Reminder> reminders) {
        this.context = context;
        this.reminders = reminders;
    }

    @Override
    public int getCount() {
        return reminders.size();
    }

    @Override
    public Object getItem(int position) {
        return reminders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.listreminder,parent,false);

            final Reminder reminder = reminders.get(position);

            TextView tv_category = view.findViewById(R.id.tv_category);
            TextView tv_detail = view.findViewById(R.id.tv_detail);
            TextView tv_date = view.findViewById(R.id.tv_date);
            TextView tv_time = view.findViewById(R.id.tv_time);

            tv_category.setText(reminder.getCategory());
            tv_detail.setText(reminder.getDetail());
            tv_date.setText(reminder.getDate());
            tv_time.setText(reminder.getTime());

        }
        return view;
    }
}
