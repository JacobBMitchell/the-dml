import { Routes, Route, BrowserRouter } from "react-router-dom";
import { useState } from "react";
import AuthContext from "./AuthContext";
import HomePage from "./HomePage";
import Nav from "./Nav";
import Login from "./Login";

function App() {
  const [user, setUser] = useState(null);
  return (
    <BrowserRouter>
      <AuthContext.Provider value={[user, setUser]}>
        <div className="App">
          <Nav />
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<Login/>}/>
          </Routes>
        </div>
      </AuthContext.Provider>
    </BrowserRouter>
  );
}

export default App;
