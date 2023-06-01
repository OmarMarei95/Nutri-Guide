package network;

import java.util.List;

import model.Meal;
import model.MealCategory;

public interface MealDetailNetworkDelegate {
    void onSuccessResult(List<Meal> meal);
    void onFailureResult(String errorMsg);
}
