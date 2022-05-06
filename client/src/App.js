import { Routes, Route, BrowserRouter } from "react-router-dom";
import { useState } from "react";
import AuthContext from "./AuthContext";
import HomePage from "./HomePage";
import Nav from "./Nav";
import Login from "./Login";
import CharacterPage from "./CharacterPage";
import CampaignPage from "./CampaignPage";
import UsefulLinks from "./UsefulLinks";
import Dice from "./Dice";

function App() {
  const [user, setUser] = useState(null);
  const [errors, setErrors] = useState([]);
  return (
    <BrowserRouter>
      <AuthContext.Provider value={[user, setUser]}>
        <div className="App">
          <Nav />
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<Login errors={errors} setErrors={setErrors}/>}/>
            <Route path="/characters" element={<CharacterPage/>}/>
            <Route path="/campaigns" element={<CampaignPage/>}/>
            <Route path="/resources" element={<UsefulLinks/>}/>
          </Routes>
          <Dice/>
        </div>
      </AuthContext.Provider>
    </BrowserRouter>
  );
}

export default App;
