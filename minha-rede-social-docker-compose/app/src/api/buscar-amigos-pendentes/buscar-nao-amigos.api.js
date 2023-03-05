import { instanceAxios } from "../_base/axios.instance";

export async function buscarSolicitacoesDeAmizadePendentesApi() {
  let response = await instanceAxios.get("/amigos/pendentes");
  return response.data.content;
}
