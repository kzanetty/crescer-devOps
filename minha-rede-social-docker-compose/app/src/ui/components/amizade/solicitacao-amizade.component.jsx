import { Link } from "react-router-dom"
import { UsuarioComponent } from ".."
import { aceitarPedidoDeAmizadeApi, rejeitarPedidoDeAmizadeApi } from "../../../api"
import { ButtonComponent } from "../button/button.component"
import "./solicitacao-amizade.component.css"


export function SolicitacoesDeAmizadeComponent({ solicitacoes }) {

    async function aceitarPedidoDeAmizade(idAmizade) {
        await aceitarPedidoDeAmizadeApi(idAmizade)
        window.location.reload(true);
    }

    async function rejeitarPedidoDeAmizade(idAmizade) {
        await rejeitarPedidoDeAmizadeApi(idAmizade)
        window.location.reload(true);
    }

    return (
        <div className="container-card-amizade">
            {
                solicitacoes?.map(solicitacao =>
                    <div key={solicitacao.id} className="card-amizade">
                        <UsuarioComponent
                            key={solicitacao.id}
                            usuario={solicitacao.solicitante}
                        />
                        <div className="buttons-amizade">
                            <ButtonComponent onClick={() => aceitarPedidoDeAmizade(solicitacao.id)} texto="aceitar" />
                            <ButtonComponent onClick={() => rejeitarPedidoDeAmizade(solicitacao.id)} texto="Rejeitar" />
                        </div>

                    </div>
                )
            }
        </div>
    )
}