package com.example.chef_up.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chef_up.databinding.FragmentProfileBinding;
import com.example.chef_up.models.User;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadProfile();
    }

    private void loadProfile() {
        User user = new User();
        user.setFirstName("Harsh");
        user.setLastName("Patel");
        user.setEmail("p.harsh2525@gmail.com");
        user.setImage("recipe1");

        binding.tvFullName.setText(user.getFirstName() + " " + user.getLastName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}