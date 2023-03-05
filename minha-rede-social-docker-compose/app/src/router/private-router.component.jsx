import { Navigate } from "react-router-dom";
import useGlobalUsuario from "../context/usuario/usuario.context";

export function PrivateRoute({ Screen }) {
    const [usuario] = useGlobalUsuario();

    if (usuario) {
        return <Screen />;
    }

    return <Navigate to={"/"} />;
}
