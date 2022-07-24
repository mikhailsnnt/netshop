import React from "react";
import './App.css';
import MenuBar from "./components/MenuBar/MenuBar";
import {BrowserRouter as Router} from "react-router-dom";



function App() {
    // const {token, setToken} = useToken()
    // if(!token)
    //     return <Login setToken={setToken}/>

    return (
        <div className="app-wrapper">
            <Router>
                <div className="menuBar">
                    <MenuBar/>
                </div>
            </Router>
        </div>
);
}

export default App;
