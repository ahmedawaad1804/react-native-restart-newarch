// src/__tests__/index.test.ts
import RNRestart, { restart, Restart } from "../index";
import RestartNewArch from "../NativeRestart";

// Mock the native TurboModule
jest.mock("../NativeRestart", () => ({
  restart: jest.fn(),
}));

describe("RNRestart Module", () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  it("should call native restart when restart() is called", () => {
    restart();
    expect(RestartNewArch.restart).toHaveBeenCalledTimes(1);
  });

  it("should call native restart when Restart() (alias) is called", () => {
    Restart();
    expect(RestartNewArch.restart).toHaveBeenCalledTimes(1);
  });

  it("should expose restart and Restart in RNRestart object", () => {
    expect(typeof RNRestart.restart).toBe("function");
    expect(typeof RNRestart.Restart).toBe("function");
  });

  it("should call native restart when using RNRestart.restart()", () => {
    RNRestart.restart();
    expect(RestartNewArch.restart).toHaveBeenCalledTimes(1);
  });

  it("should call native restart when using RNRestart.Restart()", () => {
    RNRestart.Restart();
    expect(RestartNewArch.restart).toHaveBeenCalledTimes(1);
  });
});
