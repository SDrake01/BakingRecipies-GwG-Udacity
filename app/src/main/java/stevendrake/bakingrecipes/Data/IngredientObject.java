package stevendrake.bakingrecipes.Data;

public class IngredientObject {

    private String quantity;
    private String measure;
    private String ingredient;
    private static String ingredientString;

    public void setQuantity(String newQuantity){quantity = newQuantity;}
    public void setMeasure(String newMeasure){measure = newMeasure;}
    public void setIngredient(String newIngredient){ingredient = newIngredient;}
    public static void setIngredientString(String newIngredientString){ingredientString = newIngredientString;}

    public String getQuantity(){return quantity;}
    public String getMeasure() {return measure;}
    public String getIngredient() {return ingredient;}
    public static String getIngredientString(){return ingredientString;}
}
