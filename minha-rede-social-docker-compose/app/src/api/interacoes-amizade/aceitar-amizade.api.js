import { instanceAxios } from "../_base/axios.instance";

export async function aceitarPedidoDeAmizadeApi(idAmizade) {
  let response = await instanceAxios.post(
    `/amigos/pedidos/aceitar/${idAmizade}`
  );
  return response.data;
}
