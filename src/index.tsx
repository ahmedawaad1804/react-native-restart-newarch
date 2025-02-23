import Restart from './NativeRestart';
import type { RNRestartModule } from './types';

export function restart(): void {
  return Restart.restart();
}

const RNRestart: RNRestartModule = {
  restart,
};

export default RNRestart;
