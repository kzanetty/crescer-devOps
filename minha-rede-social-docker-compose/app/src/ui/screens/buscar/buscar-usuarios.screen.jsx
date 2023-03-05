import { useEffect, useState } from "react"
import { buscarUsuariosQueNaoSaoMeusAmigosApi, enviarSolicitacaoDeAmizadeApi, buscarSolicitacoesDeAmizadePendentesApi } from "../../../api"
import { NavListComponent, UsuarioComponent } from "../../components"
import { useNavigate } from "react-router-dom";
import { ButtonComponent } from "../../components/button/button.component";
import './buscar-usuarios.screen.css'

export function BuscarUsuariosScreen() {
    const [usuarios, setUsuarios] = useState([])
    const [pendentes, setPendentes] = useState([])
    const [pedidoAmizado, setPedidoAmizade] = useState(null)
    const navigate = useNavigate();

    async function enviarSolicitacaoDeAmizade(idUsario) {
        const response = await enviarSolicitacaoDeAmizadeApi(idUsario)
        setPedidoAmizade(response)
    }

    async function buscarUsuariosQueNaoSaoMeusAmigos() {
        const response = await buscarUsuariosQueNaoSaoMeusAmigosApi()
        setUsuarios(response)
    }

    async function buscarSolicitacoesDeAmizadePendentes() {
        const response = await buscarSolicitacoesDeAmizadePendentesApi()
        setPendentes(response)
    }

    useEffect(() => {
        buscarUsuariosQueNaoSaoMeusAmigos()
        buscarSolicitacoesDeAmizadePendentes()
    }, [])

    return (
        <div className="container-screen-buscar">
            <NavListComponent />
            <div className="conteudo-screen-buscar">

                <div className="container-usuarios-disponiveis">
                    {
                        usuarios.map(usuario =>
                            <div key={usuario.id} className="cards-usuarios-screen-buscar">
                                <UsuarioComponent usuario={usuario} />
                                <ButtonComponent onClick={() => enviarSolicitacaoDeAmizade(usuario.id)} texto="Enviar solicitação de amizade" />
                            </div>
                        )
                    }
                </div>

                <button onClick={() => navigate(-1)}>Voltar</button>
            </div>
        </div>
    )
}