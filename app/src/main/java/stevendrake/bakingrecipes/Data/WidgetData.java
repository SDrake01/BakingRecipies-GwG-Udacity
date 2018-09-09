package stevendrake.bakingrecipes.Data;

public class WidgetData {

    private static String widgetTitle;
    private static String widgetIngredients;

    public static void setWidgetTitle(String newTitle){widgetTitle = newTitle;}

    public static String getWidgetTitle() {
        return widgetTitle;
    }

    public static void setWidgetIngredients(String newIngredients){widgetIngredients = newIngredients;}

    public static String getWidgetIngredients(){
        return widgetIngredients;
    }
}
