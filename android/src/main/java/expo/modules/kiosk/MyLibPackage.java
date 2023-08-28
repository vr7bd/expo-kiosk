package expo.modules.kiosk;

import android.content.Context;
import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;

import java.util.Collections;
import java.util.List;

public class MyLibPackage implements Package {
    @Override
    public List<? extends ReactActivityLifecycleListener> createReactActivityLifecycleListeners(Context activityContext) {
        return Collections.singletonList(new MyLibReactActivityLifecycleListener());
    }
}