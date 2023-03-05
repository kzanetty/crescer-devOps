import createGlobalState from "react-create-global-state";

const timeout_KEY = "timeout";
const stateInStorage = localStorage.getItem(timeout_KEY);
const initialState = stateInStorage ? JSON.parse(stateInStorage) : 0;
const [_useGlobaltimeout, Provider] = createGlobalState(initialState);

function useGlobalTimeout() {
  const [_timeout, _setTimeout] = _useGlobaltimeout();

  function setTimeout(timeout) {
    _setTimeout(timeout);
    localStorage.setItem(timeout_KEY, JSON.stringify(timeout));
  }

  return [_timeout, _setTimeout];
}

export const GlobalTimeoutProvider = Provider;
export default useGlobalTimeout;
