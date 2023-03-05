import { useEffect, useState } from "react"
import { buscarAmigosDoUsuarioApi, desfazerAmizadeApi } from "../../../api"
import useGlobalUsuario from "../../../context/usuario/usuario.context";
import { ButtonComponent } from "../button/button.component";
import { UsuarioComponent } from "../usuario/card-usuario.component"
import './amigos-usuario-autenticado.component.css'

export function AmigosComponent() {
    const [amigos, setAmigos] = useState([])
    const [usuario, setUsuario] = useGlobalUsuario();

    async function desfazerAmizade(idAmizade) {
        await desfazerAmizadeApi(idAmizade)
        window.location.reload(true);
    }

    useEffect(() => {
        async function buscarAmigos() {
            const response = await buscarAmigosDoUsuarioApi()
            setAmigos(response)
        }

        buscarAmigos()
    }, [])

    return (
        <>
            {
                amigos?.map(amigo => {
                    return (usuario.email === amigo.solicitante.email) ?
                        <div key={amigo.id} className="card-amigo-screen-amigo">
                            <UsuarioComponent usuario={amigo.recebedor} />
                            <ButtonComponent onClick={() => desfazerAmizade(amigo.id)} texto="Desfazer Amizade" />
                        </div>
                        :
                        <div key={amigo.id} className="card-amigo-screen-amigo">
                            <UsuarioComponent usuario={amigo.solicitante} />
                            <ButtonComponent onClick={() => desfazerAmizade(amigo.id)} texto="Desfazer Amizade" />
                        </div>
                })
            }

        </>
    )
}