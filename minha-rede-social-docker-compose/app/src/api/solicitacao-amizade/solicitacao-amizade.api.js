import { instanceAxios } from "../_base/axios.instance";

const USER_TOKEN = localStorage.getItem("usuario");
const AuthStr = "Bearer ".concat(USER_TOKEN);

// const token = Buffer.from(
//   `${"henriquemaster@cwi.com.br"}:${"123456"}`,
//   "utf8"
// ).toString("base64");

export async function buscarSolicitacoesDeAmizadeApi() {
  const response = await instanceAxios.get("/amigos/pedidos");
  return response.data;
}

export async function solicitacoesDeAmizadeApiUm() {
  const response = await instanceAxios.get("/amigos/pedidos", {
    headers: {
      Authorization: AuthStr,
    },
  });
  return response.data;
}

export async function solicitacoesDeAmizadeApiDois() {
  const response = await instanceAxios.get(
    "/amigos/pedidos",
    {},
    {
      headers: {
        // Authorization: `Basic ${token}`,
        Authorization: `Bearer ${USER_TOKEN}`,
      },
    }
  );

  return response.data;
}
