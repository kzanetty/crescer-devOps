import { instanceAxios } from "../_base/axios.instance";

export async function carregarPostagemDeUsuarioPorIdApi(idUsuario) {
  let response = await instanceAxios.get(`/postagens/${idUsuario}`);
  return response.data.content;
}
