package expo.modules.kiosk

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import kotlin.system.exitProcess

class ExpoKioskModule : Module() {
    companion object {
        var isInKioskMode: Boolean = true
    }

    override fun definition() = ModuleDefinition {
        Name("ExpoKiosk")

        Function("disableKioskMode") {
            isInKioskMode = false
            android.util.Log.d("expo-kiosk", "Disabling kiosk...")
        }

        Function("leaveApp") {
            // appContext.currentActivity?.finishAffinity();
            exitProcess(0);
        }
    }
}
