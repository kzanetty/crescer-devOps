import { instanceAxios } from "../_base/axios.instance";

export async function curtiPublicacaoApi(idPostagem) {
  let response = await instanceAxios.post(`/postagens/curtir/${idPostagem}`);
  return response.data;
}
