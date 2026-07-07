import '../components/header.css';
import {useSearch} from "../context/SearchContext"

export function Header(){
   const {search,setSearch,setSearchTrigger}=useSearch();
   const handleSearch=()=>{
       setSearchTrigger(prev => prev+1);
       };
    return(
        <header className="header">
            <h1>HYDfashion</h1>

            <input
             type="search"
             value={search}
             onChange={(e) => setSearch(e.target.value)}
             placeholder="Search for your fav"/>
             <button onClick={handleSearch}>
                 Search
                 </button>
        </header>
        );
    }