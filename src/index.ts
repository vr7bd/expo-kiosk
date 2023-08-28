import ExpoKioskModule from "./ExpoKioskModule";

// Get the native constant value.

export function disableKioskMode(): void {
  console.log("ExpoKiosk: Disable Kiosk Mode called");
  return ExpoKioskModule.disableKioskMode();
}

export function leaveApp(): void {
  console.log("ExpoKiosk: Leave app called");
  return ExpoKioskModule.leaveApp();
}
