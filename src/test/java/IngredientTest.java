import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(Parameterized.class)
public class IngredientTest {
    public IngredientType ingredientType;
    public String ingredientName;
    public float ingredientPrice;

    public IngredientTest(IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {IngredientType.SAUCE, "Кетчуп", 10},
                {IngredientType.FILLING, "1000 островов", 1000},
        };
    }

    @Test
    public void ingredientHasGivenProperties() {
        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);
        Assert.assertEquals(ingredientType, ingredient.getType());
        Assert.assertEquals(ingredientName, ingredient.getName());
        Assert.assertEquals(ingredientPrice, ingredient.getPrice(), 0.001);
    }
}

