interface WebWindow {
  location: {
    reload: () => void;
  };
}

declare const window: WebWindow;

export default {
  restart: () => {
    window.location.reload();
  },
};
