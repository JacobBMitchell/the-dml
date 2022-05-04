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
        <nav className='nav'>
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>

                {user?.user ? (
                    <li><button onClick={handleLogout}>Logout {user.user.sub}</button></li>
                    // Update with real link later
                ) : (
                    <li>
                        <Link to="/login">Login</Link>
                    </li>
                )}

                {user?.user ? (
                    <li>
                        <Link to="/characters">Characters</Link>
                    </li>
                ) : <></>}

                {user?.user ? (
                    <li>
                        <Link to="/campaigns">Campaigns</Link>
                    </li>
                ) : <></>}
                <li>
                    <Link to="/resources">Resources</Link>
                </li>
            </ul>
        </nav>
    )
}

export default Nav;