function Alert({ errors, setErrors, setShowErrors }) {

    function parseMessages() {
        return errors.map(err => <li className="alert alert-warning">{err}</li>)
    }

    function closeHandler() {
        setErrors([]);
        setShowErrors(false);
    }

    return (<>
        <div key="alert" className="alert alert-warning" role="alert">
            <button type="button" className="btn-close" onClick={closeHandler} aria-label="Close"></button>
            <ul className="alert alert-warning">
                {parseMessages()}
            </ul>
        </div>
    </>)
}
export default Alert;