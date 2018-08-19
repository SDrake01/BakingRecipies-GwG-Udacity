package stevendrake.bakingrecipies.Data;

public class RecipeObject {

    private static int id;
    private static String name;
    private static String ingredients;
    private static String steps;
    private static int servings;
    private static String image;

    public void setId(int newId){id = newId;}
    public void setName(String newName){name = newName;}
    public void setIngredients(String newIngredients){ingredients = newIngredients;}
    public void setSteps(String newSteps){steps = newSteps;}
    public void setServings(int newServings){servings = newServings;}
    public void setImage(String newImage){image = newImage;}

    public static int getId(){return id;}
    public static String getName() {return name;}
    public static String getIngredients() {return ingredients;}
    public static String getSteps() {return steps;}
    public static int getServings() {return servings;}
    public static String getImage() {return image;}
}
