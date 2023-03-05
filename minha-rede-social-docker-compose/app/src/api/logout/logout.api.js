import { instanceAxios } from "../_base/axios.instance";

export async function logoutApi(email, senha) {
  const response = await instanceAxios.post("/logout", {}, {});
  return response.data;
}
