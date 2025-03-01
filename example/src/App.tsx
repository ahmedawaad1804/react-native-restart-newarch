import { View, StyleSheet, Button } from "react-native";
import RNRestart from "react-native-restart-newarch";

export default function App() {
  return (
    <View style={styles.container}>
      <Button title="Restartnew" onPress={() => RNRestart.Restart()} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
});
