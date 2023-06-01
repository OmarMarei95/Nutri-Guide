package search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutriguide.R;
import com.example.nutriguide.databinding.FragmentSearchViewBinding;

import java.util.ArrayList;
import java.util.List;

import db.LocalSource;
import model.MealCategory;
import model.Repository;
import network.ApiClient;
import search.presenter.SearchPresenter;

public class SearchView extends Fragment implements SearchViewInterface, OnCategoryClickListener {
    FragmentSearchViewBinding fragment;
    MealCategoryAdapter adapter;
    SearchPresenter presenter;
    LocalSource localSource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment = FragmentSearchViewBinding.inflate(inflater, container, false);
        return fragment.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new SearchPresenter(Repository.getInstance(ApiClient.getInstance(), localSource), this);
        adapter = new MealCategoryAdapter(this,new ArrayList<MealCategory>());
        LinearLayoutManager manager = new LinearLayoutManager(requireActivity().getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragment.categoryRecycler.setLayoutManager(manager);
        fragment.categoryRecycler.setAdapter(adapter);
        presenter.getCategories();
    }

    @Override
    public void showData(List<MealCategory> categories) {
        adapter.setCategories(categories);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(String id) {
        System.out.println(id);
    }
}