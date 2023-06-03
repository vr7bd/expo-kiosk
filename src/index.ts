import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ExpoKiosk.web.ts
// and on native platforms to ExpoKiosk.ts
import ExpoKioskModule from './ExpoKioskModule';
import ExpoKioskView from './ExpoKioskView';
import { ChangeEventPayload, ExpoKioskViewProps } from './ExpoKiosk.types';

// Get the native constant value.
export const PI = ExpoKioskModule.PI;

export function hello(): string {
  return ExpoKioskModule.hello();
}

export async function setValueAsync(value: string) {
  return await ExpoKioskModule.setValueAsync(value);
}

const emitter = new EventEmitter(ExpoKioskModule ?? NativeModulesProxy.ExpoKiosk);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { ExpoKioskView, ExpoKioskViewProps, ChangeEventPayload };
