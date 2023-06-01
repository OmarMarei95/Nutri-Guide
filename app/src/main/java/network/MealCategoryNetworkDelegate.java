package network;

import java.util.List;

import model.MealCategory;

public interface MealCategoryNetworkDelegate {
    void onSuccessResult(List<MealCategory> categories);
    void onFailureResult(String errorMsg);
}
