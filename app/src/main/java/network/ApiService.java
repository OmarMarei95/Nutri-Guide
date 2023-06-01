package network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("list.php?c=list")
    Call<MealCategoryResponse> getMealCategories();

    @GET("filter.php")
    Call<MealCategoryResponse> getMealsByCategory(@Query("c") String category);

    @GET("random.php")
    Call<MealOfTheDayResponse> getMealOfTheDay();

    @GET("filter.php?a=Canadian")
    Call<MealOfTheDayResponse> getTrendingMeals();

    @GET("search.php?f=")
    Call<MealOfTheDayResponse> getAllMeals();

    @GET("lookup.php")
    Call<MealDetailResponse> getMealDetail(@Query("i") int id);
}
