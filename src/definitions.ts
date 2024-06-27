export interface InstallerPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
