import './nav-list.component.css'
import { NavLink } from 'react-router-dom'
import useGlobalUsuario from '../../../context/usuario/usuario.context'
import { useLogoutHook } from '../../../hook/logout.hook';

export function NavListComponent() {
  const [usuario, setUsuario] = useGlobalUsuario();
  const logout = useLogoutHook();

  async function logoutButton() {
    await logout.logout()
  }

  const activeStyle = {
    color: '#e0cd69',
    textDecoration: 'none',
  }

  return (
    <nav className="navlist_container">
      <ul>


        <li className="options_nav_list_component">
          <NavLink
            className="button_navlist"
            to="/profile"
            style={({ isActive }) => (isActive ? activeStyle : undefined)}
          >
            <p className="button_nav_list">Perfil</p>
          </NavLink>
        </li>
        <li className="options_nav_list_component">
          <NavLink
            className="button_navlist"
            to="/friends"
            style={({ isActive }) => (isActive ? activeStyle : undefined)}
          >
            <p className="button_nav_list">Amigos</p>
          </NavLink>
        </li>
        <li className="options_nav_list_component">
          <NavLink
            className="button_navlist"
            to="/search"
            style={({ isActive }) => (isActive ? activeStyle : undefined)}
          >
            <p className="button_nav_list">Procurar</p>
          </NavLink>
        </li>
        <li className="options_nav_list_component">
          <NavLink
            className="button_navlist"
            to="/edit"
            style={({ isActive }) => (isActive ? activeStyle : undefined)}
          >
            <p className="button_nav_list">Editar</p>
          </NavLink>
        </li>



        {
          (usuario) ? null :
            <li className="options_nav_list_component">
              <NavLink
                className="button_navlist"
                to="/"
                style={({ isActive }) => (isActive ? activeStyle : undefined)}
              >
                <p className="button_nav_list">login</p>
              </NavLink>
            </li>
        }

        <li className="options_nav_list_component">
          <NavLink
            className="button_navlist"
            to="/create"
            style={({ isActive }) => (isActive ? activeStyle : undefined)}
          >
            <p className="button_nav_list">Cadastro</p>
          </NavLink>
        </li>
        <li className="options_nav_list_component">
          <NavLink
            onClick={logoutButton}
            className="button_navlist"
            to="/"
            style={({ isActive }) => (isActive ? activeStyle : undefined)}
          >
            Logout
          </NavLink>
        </li>
      </ul>
    </nav>
  )
}
