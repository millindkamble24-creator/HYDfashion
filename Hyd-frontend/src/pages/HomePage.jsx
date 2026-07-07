import '../styles/HomePage.css';
import {Link} from 'react-router-dom';
import {useEffect,useState} from 'react';
import {useCategory} from "../context/CategoryContext";
import {useSearch} from "../context/SearchContext";

function HomePage(){
    const [products,setProducts]=useState([]);
    const [cursor,setCursor]=useState(null);
    const [hasNext,setHasNext]=useState(false);
    const {category}=useCategory();
    const {search,searchTrigger}=useSearch();
    //const[page,setPage]=useState(0);
    //const[totalPages,setTotalPages]=useState(0);
const API_URL = import.meta.env.VITE_API_BASE_URL;

//Home page load all products
useEffect(() => {
    const fetchProducts = async () => {
        let url=`${API_URL}/api/products`;
        try {
            const response = await fetch(url);

            const data = await response.json();

            setProducts(data.products);
            setCursor(data.nextCursor);
            setHasNext(data.hasNext);
            //setTotalPages(data.totalPages);
        } catch (error) {
            console.error("Failed to fetch products:", error);
        }
    };
    fetchProducts();
}, [API_URL]);

//fetch products as per category type

useEffect(()=>{
const fetchCategory=async()=>{

    if(!category) return;
    const response=await fetch(
        `${API_URL}/api/products/category/${category}`
        );
    const data=await response.json();
    setProducts(data);
    setHasNext(false);
    };
fetchCategory();
},[category,API_URL]);

const loadMore=async()=>{
         if (!hasNext) return;

            try {

                const response = await fetch(
                    `${API_URL}/api/products?cursor=${cursor}`
                );

                const data = await response.json();

                setProducts(prev => [...prev, ...data.products]);

                setCursor(data.nextCursor);

                setHasNext(data.hasNext);

            } catch (error) {

                console.error(error);

            }
    };

    //working of search Enginee
    useEffect(()=>{
        if(search.trim()==="")return;
        let url= `${API_URL}/api/products/search?`;
        const params=[];
        if(search.trim()){
            params.push(`keyword=${encodeURIComponent(search)}`);
            }
        if(category && category.trim()){
            params.push(`category=${encodeURIComponent(category)}`);
            }
        url +=params.join("&");
        console.log(url);
        const fetchSearch=async()=>{
            const response=await fetch(url);
                const data=await response.json();
                setProducts(data);
                setHasNext(false);
            }
        fetchSearch();
        },[searchTrigger]);

    return(
        <div className="home-page">
            <div className="products-grid">
                {products.map((product)=>(
                    <Link
                    key={product.id}
                    to={`/product/${product.id}`}
                    className="product-link"
                    >
                    <div className="product-card">
                        <div className="product-image-container">

                            <img
                                src={product.mainImageUrl}
                                alt={product.name}
                                className="product-image"
                            />
                        </div>
                        <div className="product-info">
                            <h4>{product.name}</h4>
                            <p className="price">{product.price}</p>
                        </div>
                    </div>
                    </Link>
                    ))}
                </div>
                <div>
           {hasNext && (
               <button onClick={loadMore}>
               Load More
               </button>
               )}
           </div>
           </div>
        );
    }

export default HomePage