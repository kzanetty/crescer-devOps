import { instanceAxios } from "../_base/axios.instance";

export async function buscarUsuariosQueNaoSaoMeusAmigosApi() {
  let response = await instanceAxios.get("/amigos/disponiveis");
  return response.data.content;
}
