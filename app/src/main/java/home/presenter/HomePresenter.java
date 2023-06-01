package home.presenter;

import java.util.List;

import home.view.HomeViewInterface;
import model.MealOfTheDay;
import model.RepositoryInterface;
import network.MealOfTheDayNetworkDelegate;

public class HomePresenter implements HomePresenterInterface, MealOfTheDayNetworkDelegate {
    RepositoryInterface repo;
    HomeViewInterface homeView;

    public HomePresenter(RepositoryInterface repo, HomeViewInterface homeView) {
        this.repo = repo;
        this.homeView = homeView;
    }

    @Override
    public void getMealOfTheDay() {
        repo.getMealOfTheDay(this);
    }

    @Override
    public void getTrendingMeals() {
        repo.getTrendingMeals(this);
    }

    @Override
    public void getAllMeals() {
        repo.getAllMeals(this);
    }

    @Override
    public void onSuccessResult(List<MealOfTheDay> meals) {
        homeView.showMealsForTheDay(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
    }

    @Override
    public void onSuccessResultTrending(List<MealOfTheDay> meals) {
        homeView.showTrendingMeals(meals);
    }

    @Override
    public void onFailureResultTrending(String errorMsg) {
    }

    @Override
    public void onSuccessResultAll(List<MealOfTheDay> meals) {
        homeView.showAllMeals(meals);
    }

    @Override
    public void onFailureResultAll(String errorMsg) {
    }
}
