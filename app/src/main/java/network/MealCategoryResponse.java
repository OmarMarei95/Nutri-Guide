package network;

import java.util.List;

import model.MealCategory;

public class MealCategoryResponse {

    private List<MealCategory> meals = null;

    public List<MealCategory> getMeals() {
        return meals;
    }

    public void setMeals(List<MealCategory> meals) {
        this.meals = meals;
    }
}
