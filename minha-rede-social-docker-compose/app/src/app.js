import "./app.css";
import { RouterProvider } from "react-router-dom";
import { router } from "./router/index";
import { GlobalToastrProvider } from "./context/toastr/toastr.context";
import { Toastr } from "./ui/components/toastr/toastr.component";
import { GlobalUsuarioProvider } from "./context/usuario/usuario.context";

function App() {
  return (
    <div className="App">
      <GlobalUsuarioProvider>
        <GlobalToastrProvider>
          <RouterProvider router={router} />
          <Toastr />
        </GlobalToastrProvider>
      </GlobalUsuarioProvider>
    </div>
  );
}

export default App;
