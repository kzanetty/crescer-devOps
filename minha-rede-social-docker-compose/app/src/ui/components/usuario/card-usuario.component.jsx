import { Link } from "react-router-dom";
import './card-usuario.component.css'

export function UsuarioComponent({ usuario }) {
    return (
        <div className="card-usuario">
            <Link to={`../vikings/detalhar/${usuario?.id}`}>
                <div className="card-informacoes-usuario">
                    <div className="informacoes-texto-card-usuario">
                        <p>{usuario?.nome}</p>
                        <p>Apelido: {usuario?.apelido}</p>
                        <p>{usuario?.email}</p>
                        <p>{usuario?.dataNascimento}</p>
                    </div>
                    <div className="imagem-usuario">
                        <img src={usuario?.imageUrl} alt="imagem de usuario" />
                    </div>
                </div>
            </Link>

        </div>
    )
}