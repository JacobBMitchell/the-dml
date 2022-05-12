import { Link } from 'react-router-dom';
import { useContext } from 'react';
import AuthContext from './AuthContext';
import { useNavigate } from "react-router-dom";

function Nav() {
    const [user, setUser] = useContext(AuthContext);
    const navigate = useNavigate();

    function handleLogout() {
        localStorage.removeItem("token");
        setUser(null);
        navigate("/");
    }

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className='container-fluid'>
                <Link className="navbar-brand" to="/">THE-DML</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">

                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link className="nav-link" to="/">Home</Link>
                        </li>

                        {user?.user ? (
                            <li className="nav-item"><button className="btn btn-secondary" onClick={handleLogout}>Logout {user.user.sub}</button></li>
                        ) : (
                            <li className="nav-item">
                                <Link className="nav-link" to="/login">Login</Link>
                            </li>
                        )}

                        {(user?.user && (user.user.authorities.includes("ROLE_PLAYER") || user.user.authorities.includes("ROLE_ADMIN"))) ? (
                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/characters">Characters</Link>
                                </li>
                            </>
                        ) : <></>}

                        {(user?.user && user.user.authorities.includes("ROLE_PLAYER")) ? (
                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/create">Add a Character</Link>
                                </li>
                            </>
                        ) : <></>}

                        {(user?.user && (user.user.authorities.includes("ROLE_DM") || user.user.authorities.includes("ROLE_ADMIN"))) ? (
                            <>
                            <li className="nav-item">
                                <Link className="nav-link" to="/campaigns">Campaigns</Link>
                            </li>
                            </>

                        ) : <></>}
                        
                        {(user?.user && user.user.authorities.includes("ROLE_DM")) ? (
                            <>
                            <li className="nav-item">
                                <Link className='nav-link' to="/addcampaign">Add Campaign</Link>
                            </li>
                            </>

                        ) : <></>}
                         
                        <li className="nav-item">
                            <Link className="nav-link" to="/resources">Resources</Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}

export default Nav;