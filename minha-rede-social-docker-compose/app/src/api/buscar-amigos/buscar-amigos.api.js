import { instanceAxios } from "../_base/axios.instance";

export async function buscarAmigosDoUsuarioApi(idAmizade) {
  let response = await instanceAxios.get("/amigos");
  return response.data.content;
}
