import '../components/header.css';
import { useParams } from "react-router-dom";

export function Header(){
    return(
        <header className="header">
            <h1>HYDfashion</h1>

            <input
             type="search"
             placeholder="Search for your fav"></input>
        </header>
        );
    }