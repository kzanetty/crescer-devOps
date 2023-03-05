import './button.component.css'

export function ButtonComponent({ texto, onClick }) {
    return (
        <div className="box-buton-component">
            <button className="button-component" onClick={onClick}>{texto}</button>
        </div>
    )
}