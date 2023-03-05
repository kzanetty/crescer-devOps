import { instanceAxios } from "../_base/axios.instance";

export async function desfazerAmizadeApi(idAmizade) {
  let response = await instanceAxios.post(`/amigos/desfazer/${idAmizade}`);
  return response.data;
}
