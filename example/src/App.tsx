import { View, StyleSheet, Button } from 'react-native';
import RNRestart from 'react-native-restart';

export default function App() {
  return (
    <View style={styles.container}>
      <Button title="Restart" onPress={() => RNRestart.restart()} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
