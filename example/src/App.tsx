import { View, StyleSheet, Button, Text } from "react-native";
import RNRestart from "react-native-restart-newarch";
const isTurboModulesEnabled = global.__turboModuleProxy !== undefined;

export default function App() {
  return (
    <View style={styles.container}>
      <Button title="Restart" onPress={() => RNRestart.restart()} />
      <Text>
        {isTurboModulesEnabled
          ? "Turbo Modules are enabled"
          : "Turbo Modules are disabled"}
      </Text>
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
