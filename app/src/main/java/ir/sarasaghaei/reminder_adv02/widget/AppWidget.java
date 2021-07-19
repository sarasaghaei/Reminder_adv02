package ir.sarasaghaei.reminder_adv02.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import ir.sarasaghaei.reminder_adv02.Const;
import ir.sarasaghaei.reminder_adv02.R;
import ir.sarasaghaei.reminder_adv02.Ui.AddReminderActivity;
import ir.sarasaghaei.reminder_adv02.adapter.WidgetRemoteViewService;

public class AppWidget extends AppWidgetProvider {
    public static final String ADD_REMINDER_TO_DB = "ir.sarasaghaei.reminder_adv02.widget.DATABASE_CHANGED";
    private static final String DETAIL_REMINDER_ACTION = "ir.sarasaghaei.reminder_adv02.widget.DETAIL_REMINDER_ACTION";

    public AppWidget() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ADD_REMINDER_TO_DB.equals(action)) {
            //update:
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] ids = appWidgetManager.getAppWidgetIds(new ComponentName(context, AppWidget.class));
            appWidgetManager.notifyAppWidgetViewDataChanged(ids, R.id.widgetListView);

        } else {
            super.onReceive(context, intent);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.collection_widget);

        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, WidgetRemoteViewService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            intent.setAction(AppWidget.ADD_REMINDER_TO_DB);


            remoteViews.setRemoteAdapter(appWidgetId, R.id.widgetListView, intent);
            remoteViews.setEmptyView(R.id.widgetListView, R.id.empty_view);

            //Add new Reminder
            Intent intentadd = new Intent(context, AddReminderActivity.class);
            PendingIntent pendingIntentadd = PendingIntent.getActivity(context, 0, intentadd, 0);
            remoteViews.setOnClickPendingIntent(R.id.btn_add, pendingIntentadd);
            //end add new Reminder


            //click to item list view
            Intent intentitem = new Intent(context, AddReminderActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentitem,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            intentitem.setAction(AppWidget.DETAIL_REMINDER_ACTION);
            intentitem.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intentitem.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            remoteViews.setPendingIntentTemplate(R.id.widgetListView, pendingIntent);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
        Log.e(Const.UPDATE_WIDGET, "onUpdate: ");
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }
}
