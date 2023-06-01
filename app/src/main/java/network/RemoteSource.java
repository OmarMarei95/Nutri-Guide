package network;

public interface RemoteSource {
    void mealCategoryEnqueue(MealCategoryNetworkDelegate mcNetworkDelegate);
    void mealOfTheDayEnqueue(MealOfTheDayNetworkDelegate modNetworkDelegate);
    void trendingMealsEnqueue(MealOfTheDayNetworkDelegate modNetworkDelegate);
    void allMealsEnqueue(MealOfTheDayNetworkDelegate modNetworkDelegate);
    void mealDetailEnqueue(MealDetailNetworkDelegate mdNetworkDelegate, int id);
}
