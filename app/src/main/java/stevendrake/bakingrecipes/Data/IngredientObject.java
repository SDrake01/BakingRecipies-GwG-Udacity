package stevendrake.bakingrecipes.Data;

public class IngredientObject {

    private static double quantity;
    private static String measure;
    private static String ingredient;

    public void setQuantity(double newQuantity){quantity = newQuantity;}
    public void setMeasure(String newMeasure){measure = newMeasure;}
    public void setIngredient(String newIngredient){ingredient = newIngredient;}

    public static double getQuantity(){return quantity;}
    public static String getMeasure() {return measure;}
    public static String getIngredient() {return ingredient;}
}
