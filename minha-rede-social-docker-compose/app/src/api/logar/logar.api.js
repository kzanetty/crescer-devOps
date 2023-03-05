import { instanceAxios } from "../_base/axios.instance";

export async function LogarApi(email, senha) {
  let response = await instanceAxios.post(
    "/login",
    {},
    {
      auth: {
        username: email,
        password: senha,
      },
    }
  );
  return response.data;
}
