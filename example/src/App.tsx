import { useState } from "react";
import { View, StyleSheet, Button, Text } from "react-native";
import RNRestart from "react-native-restart-newarch";

export default function App() {
  const [count, setCount] = useState(0);

  const restartRepeatedly = () => {
    for (let request = 0; request < 3; request += 1) {
      RNRestart.restart();
    }
  };

  return (
    <View style={styles.container}>
      <Text>Count: {count}</Text>
      <Button
        title="Increment"
        testID="increment"
        onPress={() => setCount((value) => value + 1)}
      />
      <Button title="Restart app" testID="restart" onPress={RNRestart.restart} />
      <Button
        title="Request restart three times"
        testID="restart-repeatedly"
        onPress={restartRepeatedly}
      />
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
