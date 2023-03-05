import { instanceAxios } from "../_base/axios.instance";

export async function enviarSolicitacaoDeAmizadeApi(idUsario) {
  let response = await instanceAxios.post(`/amigos/enviar/${idUsario}`);
  return response.data;
}
