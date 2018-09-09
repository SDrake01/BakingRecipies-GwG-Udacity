package stevendrake.bakingrecipes.AppWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import stevendrake.bakingrecipes.Data.WidgetData;
import stevendrake.bakingrecipes.R;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetTitle;
        CharSequence widgetBody;

        if (WidgetData.getWidgetTitle() == null || WidgetData.getWidgetTitle().isEmpty()) {
            widgetTitle = context.getString(R.string.app_name);
            widgetBody = context.getString(R.string.ingredients_title);
        } else {
            widgetTitle = WidgetData.getWidgetTitle();
            widgetBody = WidgetData.getWidgetIngredients();
        }
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        views.setTextViewText(R.id.tv_app_widget_title, widgetTitle);
        views.setTextViewText(R.id.tv_app_widget_body, widgetBody);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

