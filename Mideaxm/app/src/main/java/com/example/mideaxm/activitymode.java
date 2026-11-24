package com.example.mideaxm;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class activitymode extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private Button btnSetMode, btnBack;
    private final String[] tabTitles = {"First", "Second", "Third"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitymode);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        btnBack = findViewById(R.id.btnBack);
        btnSetMode = findViewById(R.id.btnSetMode);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        btnBack.setOnClickListener(v -> navigateBackToMain());

        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragmenttab.newInstance(tabTitles[position]);
            }

            @Override
            public int getItemCount() {
                return tabTitles.length;
            }
        });

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
                tab.setText(tabTitles[position])
        ).attach();

        btnSetMode.setOnClickListener(v -> showModePopupMenu());

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem == 1 || currentItem == 2) {
                    viewPager.setCurrentItem(0, true);
                } else {
                    navigateBackToMain();
                }
            }
        });
    }

    private void showModePopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, btnSetMode);
        popupMenu.getMenu().add("Light");
        popupMenu.getMenu().add("Night");
        popupMenu.getMenu().add("System");

        popupMenu.setOnMenuItemClickListener(item -> {
            String title = item.getTitle().toString();
            if ("Light".equals(title)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                return true;
            } else if ("Night".equals(title)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                return true;
            } else if ("System".equals(title)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                return true;
            }
            return false;
        });

        popupMenu.show();
    }

    private void navigateBackToMain() {
        Intent intent = new Intent(activitymode.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigateBackToMain();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
