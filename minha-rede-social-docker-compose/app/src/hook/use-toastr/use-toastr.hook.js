import useGlobalToastr from "../../context/toastr/toastr.context";

export function useToastr() {
  const [, setToastr] = useGlobalToastr();

  function showToastr(message) {
    setToastr(message);
  }

  function closeToastr() {
    setToastr(null);
  }

  return { showToastr, closeToastr };
}
