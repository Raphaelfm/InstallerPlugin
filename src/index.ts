import { registerPlugin } from '@capacitor/core';

import type { InstallerPluginPlugin } from './definitions';

const InstallerPlugin = registerPlugin<InstallerPluginPlugin>('InstallerPlugin', {
  web: () => import('./web').then(m => new m.InstallerPluginWeb()),
});

export * from './definitions';
export { InstallerPlugin };
