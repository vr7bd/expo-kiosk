import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

import { ExpoKioskViewProps } from './ExpoKiosk.types';

const NativeView: React.ComponentType<ExpoKioskViewProps> =
  requireNativeViewManager('ExpoKiosk');

export default function ExpoKioskView(props: ExpoKioskViewProps) {
  return <NativeView {...props} />;
}
