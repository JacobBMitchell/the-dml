function Alert({ errors }) {

    function parseMessages() {
        return errors.map(err => <li className="alert alert-warning">{err}</li>)
    }

    return (<>
        <div key="alert" className="alert alert-warning" role="alert">
            <button type="button" className="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            <ul className="alert alert-warning">
                {parseMessages()}
            </ul>
        </div>
    </>)
}
export default Alert;