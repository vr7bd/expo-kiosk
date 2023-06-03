import ExpoKioskModule from './ExpoKioskModule';

// Get the native constant value.

export function killApp(): void {
  console.log('kill called');
  return ExpoKioskModule.killApp();
}

export function exitApp(): void {
  console.log('exit called');
  return ExpoKioskModule.exitApp();
}