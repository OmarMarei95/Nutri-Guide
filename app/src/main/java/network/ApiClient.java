package network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements RemoteSource {
    ApiService apiService;
    private static ApiClient apiClient = null;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private ApiClient() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static synchronized ApiClient getInstance(){
        if (apiClient == null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }


    @Override
    public void mealCategoryEnqueue(MealCategoryNetworkDelegate mcNetworkDelegate) {
        Call<MealCategoryResponse> call = apiService.getMealCategories();
        call.enqueue(new Callback<MealCategoryResponse>() {
            @Override
            public void onResponse(Call<MealCategoryResponse> call, Response<MealCategoryResponse> response) {
                mcNetworkDelegate.onSuccessResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealCategoryResponse> call, Throwable t) {
                mcNetworkDelegate.onFailureResult(t.toString());
            }
        });
    }

    @Override
    public void mealOfTheDayEnqueue(MealOfTheDayNetworkDelegate modNetworkDelegate) {
        Call<MealOfTheDayResponse> call = apiService.getMealOfTheDay();
        call.enqueue(new Callback<MealOfTheDayResponse>() {
            @Override
            public void onResponse(Call<MealOfTheDayResponse> call, Response<MealOfTheDayResponse> response) {
                modNetworkDelegate.onSuccessResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealOfTheDayResponse> call, Throwable t) {
                modNetworkDelegate.onFailureResult(t.getMessage().toString());
            }

        });
    }

    @Override
    public void trendingMealsEnqueue(MealOfTheDayNetworkDelegate modNetworkDelegate) {
        Call<MealOfTheDayResponse> call = apiService.getTrendingMeals();
        call.enqueue(new Callback<MealOfTheDayResponse>() {
            @Override
            public void onResponse(Call<MealOfTheDayResponse> call, Response<MealOfTheDayResponse> response) {
                modNetworkDelegate.onSuccessResultTrending(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealOfTheDayResponse> call, Throwable t) {
                modNetworkDelegate.onFailureResult(t.getMessage().toString());
            }

        });
    }

    @Override
    public void allMealsEnqueue(MealOfTheDayNetworkDelegate modNetworkDelegate) {
        Call<MealOfTheDayResponse> call = apiService.getAllMeals();
        call.enqueue(new Callback<MealOfTheDayResponse>() {
            @Override
            public void onResponse(Call<MealOfTheDayResponse> call, Response<MealOfTheDayResponse> response) {
                modNetworkDelegate.onSuccessResultAll(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealOfTheDayResponse> call, Throwable t) {
                modNetworkDelegate.onFailureResultAll(t.getMessage().toString());
            }

        });

    }

    @Override
    public void mealDetailEnqueue(MealDetailNetworkDelegate mdNetworkDelegate, int id) {
        Call<MealDetailResponse> call = apiService.getMealDetail(id);
        call.enqueue((new Callback<MealDetailResponse>() {
            @Override
            public void onResponse(Call<MealDetailResponse> call, Response<MealDetailResponse> response) {
                Log.d("MuhammadDebug", call.request().url().toString());
                mdNetworkDelegate.onSuccessResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealDetailResponse> call, Throwable t) {
                mdNetworkDelegate.onFailureResult(t.getMessage().toString());
            }
        }));
    }
}


