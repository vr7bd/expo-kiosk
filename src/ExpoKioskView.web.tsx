import * as React from 'react';

import { ExpoKioskViewProps } from './ExpoKiosk.types';

export default function ExpoKioskView(props: ExpoKioskViewProps) {
  return (
    <div>
      <span>{props.name}</span>
    </div>
  );
}
