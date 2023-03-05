import { instanceAxios } from "../_base/axios.instance";

export async function adicionarComentarioApi(
  idUsuario,
  idPostagem,
  comentario
) {
  let response = await instanceAxios.post(`/postagens/comentar/${idPostagem}`, {
    idUsuario,
    idPostagem,
    comentario,
  });
  return response.data;
}
