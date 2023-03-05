import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { carregarPostagemDeUsuarioPorIdApi } from "../../../api/postagem/postagem-usuario-id.api";
import { buscarUsuarioPorIdApi } from "../../../api/usuario/buscar-usuario.api";
import { NavListComponent, UsuarioComponent } from "../../components"
import { CardPostagem } from "../../components/postagem/CardPostagem.component";
import './usuario.screen.css'

export function UsuarioScreen() {
    const { id } = useParams();
    const [usuario, setUsuario] = useState(null)
    const [postagens, setPostagens] = useState([])
    const navigate = useNavigate();

    useEffect(() => {
        async function buscarUsuario() {
            const response = await buscarUsuarioPorIdApi(id)
            setUsuario(response)
        }

        async function carregarPostagensDoUsuario() {
            const response = await carregarPostagemDeUsuarioPorIdApi(id)
            setPostagens(response)
        }

        carregarPostagensDoUsuario()
        buscarUsuario()
    }, [])

    return (
        <>
            <NavListComponent />
            <div className="container-screen-usuario">
                <div>
                    <UsuarioComponent usuario={usuario} />
                </div>
                <div className="cards">
                    {
                        postagens.map(postagem =>
                            <CardPostagem key={postagem.id} postagem={postagem} />
                        )
                    }
                </div>
            </div>
            <button onClick={() => navigate(-1)}>Voltar</button>
        </>
    )
}