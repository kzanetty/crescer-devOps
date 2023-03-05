import { instanceAxios } from "../_base/axios.instance";

export async function timelineApi() {
  let response = await instanceAxios.get("/postagens/timeline");
  return response.data.content;
}
