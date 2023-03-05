import { createBrowserRouter } from "react-router-dom";
import {
  LoginScreen,
  CreateAccountScreen,
  PerfilScreen,
  UsuarioScreen,
  BuscarUsuariosScreen,
  AmigosScreen,
  EditarPerfilScreen,
} from "../ui/screens";
import { PrivateRoute } from "./private-router.component";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <LoginScreen />,
  },
  {
    path: "/create",
    element: <CreateAccountScreen />,
  },
  {
    path: "/profile",
    element: <PrivateRoute Screen={PerfilScreen} />,
  },
  {
    path: "/friends",
    element: <PrivateRoute Screen={AmigosScreen} />,
  },
  {
    path: "/vikings/detalhar/:id",
    element: <PrivateRoute Screen={UsuarioScreen} />,
  },
  {
    path: "/search",
    element: <PrivateRoute Screen={BuscarUsuariosScreen} />,
  },
  {
    path: "/edit",
    element: <PrivateRoute Screen={EditarPerfilScreen} />,
  },
]);
