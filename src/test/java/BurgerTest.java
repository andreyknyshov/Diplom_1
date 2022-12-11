import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private static final float BUN_PRICE = 100F;
    private static final String BUN_NAME = "булочка";

    private static final float KETCHUP_PRICE = 10F;
    private static final String KETCHUP_NAME = "кетчуп";
    private static final IngredientType KETCHUP_TYPE = IngredientType.SAUCE;

    private static final float PORK_PRICE = 50F;
    private static final String PORK_NAME = "свинина";
    private static final IngredientType PORK_TYPE = IngredientType.FILLING;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredientKetchup;
    @Mock
    private Ingredient ingredientPork;




    @Test
    public void burgerCanSetBun() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void burgerCanAddIngredients() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientKetchup);
        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertEquals(ingredientKetchup, burger.ingredients.get(0));
        burger.addIngredient(ingredientPork);
        Assert.assertEquals(2, burger.ingredients.size());
        Assert.assertEquals(ingredientPork, burger.ingredients.get(1));
    }

    @Test
    public void burgerCanRemoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientKetchup);
        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertEquals(ingredientKetchup, burger.ingredients.get(0));
        burger.removeIngredient(0);
        Assert.assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void burgerCanMoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientKetchup);
        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertEquals(ingredientKetchup, burger.ingredients.get(0));
        burger.addIngredient(ingredientPork);
        Assert.assertEquals(2, burger.ingredients.size());
        Assert.assertEquals(ingredientPork, burger.ingredients.get(1));

        burger.moveIngredient(0, 1);
        Assert.assertEquals(2, burger.ingredients.size());
        Assert.assertEquals(ingredientPork, burger.ingredients.get(0));
        Assert.assertEquals(ingredientKetchup, burger.ingredients.get(1));
    }

    @Test
    public void burgerCanGetPrice() {
        Mockito.when(bun.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(ingredientKetchup.getPrice()).thenReturn(KETCHUP_PRICE);
        Mockito.when(ingredientPork.getPrice()).thenReturn(PORK_PRICE);

        Burger burger = new Burger();
        burger.addIngredient(ingredientKetchup);
        burger.addIngredient(ingredientPork);
        burger.setBuns(bun);

        float totalPrice = burger.getPrice();
        Mockito.verify(bun, Mockito.times(1)).getPrice();
        Mockito.verify(ingredientKetchup, Mockito.times(1)).getPrice();
        Mockito.verify(ingredientPork, Mockito.times(1)).getPrice();

        float expectedPrice = BUN_PRICE * 2 + KETCHUP_PRICE + PORK_PRICE;
        Assert.assertEquals(expectedPrice, totalPrice, 0.001);
    }

    @Test
    public void burgerCanGetRecipe() {
        Mockito.when(bun.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(bun.getName()).thenReturn(BUN_NAME);
        Mockito.when(ingredientKetchup.getPrice()).thenReturn(KETCHUP_PRICE);
        Mockito.when(ingredientKetchup.getName()).thenReturn(KETCHUP_NAME);
        Mockito.when(ingredientKetchup.getType()).thenReturn(KETCHUP_TYPE);
        Mockito.when(ingredientPork.getPrice()).thenReturn(PORK_PRICE);
        Mockito.when(ingredientPork.getName()).thenReturn(PORK_NAME);
        Mockito.when(ingredientPork.getType()).thenReturn(PORK_TYPE);

        Burger burger = new Burger();
        burger.addIngredient(ingredientKetchup);
        burger.addIngredient(ingredientPork);
        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        Mockito.verify(bun, Mockito.times(2)).getName();
        Mockito.verify(ingredientKetchup, Mockito.times(1)).getName();
        Mockito.verify(ingredientKetchup, Mockito.times(1)).getType();
        Mockito.verify(ingredientPork, Mockito.times(1)).getName();
        Mockito.verify(ingredientPork, Mockito.times(1)).getType();

        float expectedPrice = BUN_PRICE * 2 + KETCHUP_PRICE + PORK_PRICE;
        String expectedReceipt =
                String.format("(==== %s ====)\n" + "= sauce %s =\n" + "= filling %s =\n" + "(==== %s ====)\n\n" +
                                      "Price: %f\n", BUN_NAME, KETCHUP_NAME, PORK_NAME, BUN_NAME, expectedPrice);
        Assert.assertEquals(expectedReceipt, receipt);
    }
}
