import RestartNewArch from './NativeRestart';
import type { RNRestartModule } from './types';

export function restart(): void {
  return RestartNewArch.restart();
}

export function Restart(): void {
  return RestartNewArch.restart();
}

const RNRestart: RNRestartModule = {
  restart,
  Restart,
};

export default RNRestart;
