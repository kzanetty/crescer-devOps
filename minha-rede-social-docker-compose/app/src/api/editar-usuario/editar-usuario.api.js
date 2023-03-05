import { instanceAxios } from "../_base/axios.instance";

export async function atualizarUsuarioApi(nome, apelido, imagem) {
  let response = await instanceAxios.post("vikings/editar", {
    nome,
    apelido,
    imagemUrl: imagem,
  });
  return response.data;
}
