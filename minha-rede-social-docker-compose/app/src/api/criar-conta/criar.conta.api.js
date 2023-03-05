import { instanceAxios } from "../_base/axios.instance";

export async function CriarContaApi({
  nome,
  email,
  apelido,
  dataNascimento,
  imageUrl,
  senha,
}) {
  let response = await instanceAxios.post("/usuarios", {
    nome: nome,
    email: email,
    apelido: apelido,
    dataNascimento: dataNascimento,
    imageUrl: imageUrl,
    senha: senha,
  });
  return response.data;
}
