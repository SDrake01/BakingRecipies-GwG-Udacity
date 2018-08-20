package stevendrake.bakingrecipes.Data;

public class RecipeObject {

    private static String id;
    private static String name;
    private static String ingredients;
    private static String steps;
    private static String servings;
    private static String image;

    public void setId(String newId){id = newId;}
    public void setName(String newName){name = newName;}
    public void setIngredients(String newIngredients){ingredients = newIngredients;}
    public void setSteps(String newSteps){steps = newSteps;}
    public void setServings(String newServings){servings = newServings;}
    public void setImage(String newImage){image = newImage;}

    public String getId(){return id;}
    public String getName() {return name;}
    public String getIngredients() {return ingredients;}
    public String getSteps() {return steps;}
    public String getServings() {return servings;}
    public String getImage() {return image;}
}
