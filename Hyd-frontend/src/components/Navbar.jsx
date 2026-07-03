import "./Navbar.css"
import { useCategory } from "../context/CategoryContext";

export function Navbar(){

    const {setCategory}=useCategory();
    const NAV_CATEGORIES=[
        "T-shirt",
        "Jeans",
        "Shirts"
        ];

    return(
        <div className="nav-main">
            <ul className="nav-ul">
                    {NAV_CATEGORIES.map(category => (
                        <li className="nav-li" key={category}>
                        <button
                            className="nav-div"
                            onClick={()=>setCategory(category)}
                        >
                            {category}
                        </button>
                        </li>
                    ))}
            </ul>
        </div>
        );
    }