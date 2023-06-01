package home.view;

import java.util.List;

import model.MealOfTheDay;

public interface HomeViewInterface {
    void showMealsForTheDay(List<MealOfTheDay> meals);
    void showTrendingMeals(List<MealOfTheDay> meals);
    void showAllMeals(List<MealOfTheDay> meals);
}
