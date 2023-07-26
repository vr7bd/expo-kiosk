import ExpoKioskModule from "./ExpoKioskModule";

// Get the native constant value.

export function disableKioskMode(): void {
  console.log("ExpoKiosk: Disable Kiosk Mode called");
  return ExpoKioskModule.disableKioskMode();
}
