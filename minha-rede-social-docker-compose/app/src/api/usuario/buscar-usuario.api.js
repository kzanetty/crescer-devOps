import { instanceAxios } from "../_base/axios.instance";

export async function buscarUsuarioPorIdApi(idUsuario) {
  const response = await instanceAxios.get(`/vikings/detalhar/${idUsuario}`);
  return response.data;
}
