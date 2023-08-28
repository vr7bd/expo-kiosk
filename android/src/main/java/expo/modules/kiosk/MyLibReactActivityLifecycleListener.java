package expo.modules.kiosk;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import expo.modules.core.interfaces.ReactActivityLifecycleListener;

public class MyLibReactActivityLifecycleListener implements ReactActivityLifecycleListener {

    private Window window;
    private View decorView;
    @Override
    public void onCreate(Activity activity, Bundle savedInstanceState) {
        ReactActivityLifecycleListener.super.onCreate(activity, savedInstanceState);
        window = activity.getWindow();
        decorView = window.getDecorView();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
                Log.i("BD", "Unexpected crash");
                Intent intent = new Intent(activity, activity.getClass());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
                if (activity instanceof Activity) {
                    activity.finish();
                }
                Runtime.getRuntime().exit(0);
            }
        });
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    resetWindow();

                    final int uiOptions =
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

                    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    decorView.setSystemUiVisibility(uiOptions);

                    decorView.setOnFocusChangeListener(new View.OnFocusChangeListener()
                    {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus)
                        {
                            if (hasFocus)
                            {
                                decorView.setSystemUiVisibility(uiOptions);
                            }
                        }
                    });

                    decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            decorView.setSystemUiVisibility(uiOptions);
                        }
                    });

                }
                catch (Exception e)
                {
                    Log.e("BD", e.getMessage());
                }
            }
        });
    }

    @Override
    public void onPause(Activity activity) {
        ReactActivityLifecycleListener.super.onPause(activity);
        boolean isInKioskMode = ExpoKioskModule.Companion.isInKioskMode();
        if (isInKioskMode && activity.getPackageName().equals(findLauncherPackageName(activity))) {
            Log.d("BD", "onPause: Is In Kiosk");
            ActivityManager activityManager = (ActivityManager) activity.getApplicationContext()
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.moveTaskToFront(activity.getTaskId(), 0);
        } else {
            Log.d("BD", "onPause: Is Not In Kiosk");
        }
    }

    @Override
    public void onResume(Activity activity) {
        ReactActivityLifecycleListener.super.onResume(activity);
        ExpoKioskModule.Companion.setInKioskMode(true);
        Log.d("BD", "onResume: " + ExpoKioskModule.Companion.isInKioskMode());
    }

    private String findLauncherPackageName(Activity activity) {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        final ResolveInfo res = activity.getPackageManager().resolveActivity(intent, 0);
        return res.activityInfo.packageName;
    }
    protected void resetWindow()
    {
        decorView.setOnFocusChangeListener(null);
        decorView.setOnSystemUiVisibilityChangeListener(null);

        window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }
}
