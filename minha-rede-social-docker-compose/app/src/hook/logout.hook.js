import { useState } from "react";
import { logoutApi } from "../api/logout/logout.api";
import useGlobalUsuario from "../context/usuario/usuario.context";

export function useLogoutHook() {
  const [error, setError] = useState(false);
  const [, setUser] = useGlobalUsuario();

  async function logout() {
    try {
      const response = await logoutApi();
      setUser(null);
    } catch (error) {
      setError(error.response.data.message);
    }
  }

  return {
    error,
    logout
  };
}
