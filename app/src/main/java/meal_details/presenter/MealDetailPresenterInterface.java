package meal_details.presenter;

import model.Meal;

public interface MealDetailPresenterInterface {
    void getMealDetail(int id);
    void insertMeal(Meal meal);
    void deleteMeal(Meal meal);
}
