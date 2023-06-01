package network;

import java.util.List;

import model.MealCategory;
import model.MealOfTheDay;

public interface MealOfTheDayNetworkDelegate {
    void onSuccessResult(List<MealOfTheDay> meals);
    void onFailureResult(String errorMsg);

    void onSuccessResultTrending(List<MealOfTheDay> meals);
    void onFailureResultTrending(String errorMsg);

    void onSuccessResultAll(List<MealOfTheDay> meals);
    void onFailureResultAll(String errorMsg);
}
