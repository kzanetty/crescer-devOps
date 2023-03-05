import { useEffect, useState } from "react"
import useGlobalUsuario from "../../../context/usuario/usuario.context";
import { timelineApi } from '../../../api'
import { CardPostagem } from "./CardPostagem.component";
import './timeline.component.css'

export function TimelineComponent() {
    const [postagens, setPostagens] = useState([])
    const [usuario, setUsuario] = useGlobalUsuario();

    async function carregarPostagens() {
        const response = await timelineApi()
        setPostagens(response)
    }

    useEffect(() => {
        carregarPostagens()
    }, [])

    return (
        <div className="container-timeline">
            <h1>TIMELINE</h1>
            <div className="cards">
                {
                    postagens?.map(postagem =>
                        <CardPostagem key={postagem.id} postagem={postagem} />
                    )
                }
            </div>
        </div>
    )
}