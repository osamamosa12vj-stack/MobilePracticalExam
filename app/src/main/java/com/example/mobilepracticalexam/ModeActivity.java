package com.example.mobilepracticalexam;

import android.os.Bundle;
import android.view.Menu;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private MaterialButton btnSetMode;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private static final int MODE_LIGHT = 1;
    private static final int MODE_NIGHT = 2;
    private static final int MODE_SYSTEM = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        initializeViews();
        setupToolbar();
        setupViewPager();
        setupModeButton();
        setupBackButton();
    }

    // Initialize Views
    private void initializeViews() {

        toolbar = findViewById(R.id.toolbar);
        btnSetMode = findViewById(R.id.btnSetMode);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    // Toolbar Configuration
    private void setupToolbar() {

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.mode_dashboard);
        }

        toolbar.setNavigationOnClickListener(
                v -> getOnBackPressedDispatcher().onBackPressed()
        );
    }

    // ViewPager Configuration
    private void setupViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {

                    switch (position) {

                        case 0:
                            tab.setText(R.string.tab_first);
                            break;

                        case 1:
                            tab.setText(R.string.tab_second);
                            break;

                        case 2:
                            tab.setText(R.string.tab_third);
                            break;
                    }

                }).attach();

        // Disable unnecessary page transitions
        viewPager.setOffscreenPageLimit(2);
    }

    // Set Mode Button
    private void setupModeButton() {

        btnSetMode.setOnClickListener(v -> showModeMenu());
    }

    // Display Mode Menu
    private void showModeMenu() {

        PopupMenu popupMenu = new PopupMenu(this, btnSetMode);

        popupMenu.getMenu().add(
                Menu.NONE, MODE_LIGHT, Menu.NONE, R.string.mode_light
        );

        popupMenu.getMenu().add(
                Menu.NONE, MODE_NIGHT, Menu.NONE, R.string.mode_night
        );

        popupMenu.getMenu().add(
                Menu.NONE, MODE_SYSTEM, Menu.NONE, R.string.mode_system
        );

        popupMenu.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {

                case MODE_LIGHT:

                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_NO
                    );

                    break;

                case MODE_NIGHT:

                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_YES
                    );

                    break;

                case MODE_SYSTEM:

                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    );

                    break;

                default:
                    return false;
            }

            Toast.makeText(
                    this,
                    R.string.mode_changed,
                    Toast.LENGTH_SHORT
            ).show();

            return true;
        });

        popupMenu.show();
    }

    // Back Button Management
    private void setupBackButton() {

        getOnBackPressedDispatcher().addCallback(
                this,
                new OnBackPressedCallback(true) {

                    @Override
                    public void handleOnBackPressed() {

                        if (viewPager.getCurrentItem() > 0) {

                            viewPager.setCurrentItem(0, true);

                        } else {

                            finish();
                        }
                    }
                }
        );
    }
}