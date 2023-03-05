import { instanceAxios } from "../_base/axios.instance";

export async function rejeitarPedidoDeAmizadeApi(idAmizade) {
  let response = await instanceAxios.post(
    `/amigos/pedidos/rejeitar/${idAmizade}`
  );
  return response.data;
}
