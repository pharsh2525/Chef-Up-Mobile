package com.example.chef_up.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chef_up.adapers.CategoryAdapter;
import com.example.chef_up.databinding.FragmentCategoryBinding;
import com.example.chef_up.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {

    private FragmentCategoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadCategories();
    }

    private void loadCategories() {
        binding.rvCategories.setAdapter(new CategoryAdapter());
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("1","breakfast","breakfast"));
        categories.add(new Category("2","Lunch","lunch"));
        categories.add(new Category("3","Dinner","dinner"));
        CategoryAdapter adapter = (CategoryAdapter) binding.rvCategories.getAdapter();
        if(adapter != null){
            adapter.setCategoryList(categories);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}