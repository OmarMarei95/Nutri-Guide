package network;

import java.util.List;

import model.MealOfTheDay;

public class MealOfTheDayResponse {

    private List<MealOfTheDay> meals = null;

    public List<MealOfTheDay> getMeals() {
        return meals;
    }

    public void setMeals(List<MealOfTheDay> meals) {
        this.meals = meals;
    }
}
