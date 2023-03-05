import { NavListComponent } from "../../components";
import { AmigosComponent } from "../../components/amizade/amigos-usuario-autenticado.component";
import './amigos.screen.css';

export function AmigosScreen() {
    return (
        <>
            <NavListComponent />
            <div className="container-amigos-screen">
                <div className="container-cards-amigos">
                    <AmigosComponent />
                </div>
            </div>
        </>
    )
}