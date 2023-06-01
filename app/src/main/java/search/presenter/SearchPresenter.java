package search.presenter;

import java.util.List;

import model.MealCategory;
import model.RepositoryInterface;
import network.MealCategoryNetworkDelegate;
import search.view.SearchViewInterface;

public class SearchPresenter implements SearchPresenterInterface, MealCategoryNetworkDelegate {
    RepositoryInterface repo;
    SearchViewInterface searchView;

    public SearchPresenter(RepositoryInterface repo, SearchViewInterface searchView) {
        this.repo = repo;
        this.searchView = searchView;
    }

    @Override
    public void onSuccessResult(List<MealCategory> categories) {
        searchView.showData(categories);
    }

    @Override
    public void onFailureResult(String errorMsg) {
    }

    @Override
    public void getCategories() {
        repo.getCategory(this);
    }
}
