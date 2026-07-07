import {createContext,useContext,useState} from "react"

const SearchContext=createContext();

export function SearchProvider({children}){

    const [search,setSearch]=useState("");
    const [searchTrigger,setSearchTrigger]=useState(0);


    return(
        <SearchContext.Provider
        type="search"
        value={{search,setSearch,searchTrigger,setSearchTrigger}}>
            {children}
        </SearchContext.Provider>
        );
}
export function useSearch(){
    return useContext(SearchContext)
    }