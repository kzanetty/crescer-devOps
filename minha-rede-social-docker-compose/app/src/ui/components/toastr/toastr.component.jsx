import "./toastr.style.css";
import useGlobalToastr from "../../../context/toastr/toastr.context";
import { useEffect } from "react";

const TOASTR_DELAY = 3000;

export function Toastr() {
  const [message, setMessage] = useGlobalToastr();
  useEffect(() => {
    if (message) {
      setTimeout(() => {
        setMessage("");
      }, TOASTR_DELAY);
    }
  }, [message, setMessage]);

  if (!message) {
    return;
  }

  return (
    <div className="toastr">
      <p>{message}</p>
    </div>
  );
}
