package expo.modules.kiosk;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import android.view.View;
import android.view.WindowInsets;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;


public class MyLibReactActivityLifecycleListener implements ReactActivityLifecycleListener {
    @Override
    public void onCreate(Activity activity, Bundle savedInstanceState) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(activity.getWindow(), activity.getWindow().getDecorView());
        // Configure the behavior of the hidden system bars.
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.getWindow().getDecorView().getWindowInsetsController().hide(
                    WindowInsets.Type.statusBars()
            );
        } else {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(hideSystemBars());
            decorView.setOnSystemUiVisibilityChangeListener
                    (new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            // Note that system bars will only be "visible" if none of the
                            // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(hideSystemBars());
                            }
                        }
                    });

        }

        // Add a listener to update the behavior of the toggle fullscreen button when
        // the system bars are hidden or revealed.
        activity.getWindow().getDecorView().setOnApplyWindowInsetsListener((view, windowInsets) -> {
            // You can hide the caption bar even when the other system bars are visible.
            // To account for this, explicitly check the visibility of navigationBars()
            // and statusBars() rather than checking the visibility of systemBars().
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (windowInsets.isVisible(WindowInsetsCompat.Type.navigationBars())
                        || windowInsets.isVisible(WindowInsetsCompat.Type.statusBars())) {
                    // Hide both the status bar and the navigation bar.
                    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
                }
            }
            return view.onApplyWindowInsets(windowInsets);
        });
    }
    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
    }

    @Override
    public void onPause(Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(activity.getTaskId(), 0);
    }

   @Override
   public boolean onBackPressed() {
       return true;
   }
}
