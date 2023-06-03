package expo.modules.kiosk

import android.content.Intent
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class ExpoKioskModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("ExpoKiosk")

    Function("killApp") {
      android.util.Log.d("BD", "killing")
      android.os.Process.killProcess(android.os.Process.myPid());
    }
    Function("exitApp") {
      android.util.Log.d("BD", "exiting")
      val sharingIntent = Intent(Intent.ACTION_MAIN)
      sharingIntent.addCategory(Intent.CATEGORY_HOME)
      sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

      val chooserIntent = Intent.createChooser(sharingIntent, "Go to")
      chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      appContext.reactContext?.startActivity(chooserIntent)
    }
  }
}
