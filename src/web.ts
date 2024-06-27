import { WebPlugin } from '@capacitor/core';

import type { InstallerPluginPlugin } from './definitions';

export class InstallerPluginWeb extends WebPlugin implements InstallerPluginPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
