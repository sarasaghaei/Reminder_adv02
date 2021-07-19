package ir.sarasaghaei.reminder_adv02.adapter;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import java.util.List;
import ir.sarasaghaei.reminder_adv02.R;
import ir.sarasaghaei.reminder_adv02.database.ReminderDBHelper;
import ir.sarasaghaei.reminder_adv02.entity.Reminder;

public class WidgetRemoteViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetRemoteViewFactory(this.getApplicationContext(),intent);
    }

    class WidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {
        private Context context;
        ReminderDBHelper reminderDBHelper;
        private List<Reminder> reminders;

        public WidgetRemoteViewFactory(Context context, Intent intent) {
            this.context = context;
            reminderDBHelper = new ReminderDBHelper(context);
            reminders = reminderDBHelper.select();
        }


        //initialize:
        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            reminders = reminderDBHelper.select();

        }

        @Override
        public void onDestroy() {
            if (context != null){
                reminders.clear();
            }

        }

        @Override
        public int getCount() {
           return reminders.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.collection_widget_list_item);
            rv.setTextViewText(R.id.widgetItemdetail, String.valueOf(reminders.get(position).getDetail()));
            rv.setTextViewText(R.id.widgetItemid,String.valueOf(reminders.get(position).getId()));

            Bundle extras = new Bundle();
            extras.putInt("id", position);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);

            rv.setOnClickFillInIntent(R.id.widgetItemdetail, fillInIntent);
            rv.setOnClickFillInIntent(R.id.widgetItemid, fillInIntent);


            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
