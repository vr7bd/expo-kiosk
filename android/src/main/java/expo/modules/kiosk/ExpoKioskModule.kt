package expo.modules.kiosk

import android.content.Intent
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

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
    }
}
