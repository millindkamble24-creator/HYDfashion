import '../styles/HomePage.css';
import {Link} from 'react-router-dom';
import {useEffect,useState} from 'react';

function HomePage(){
    const[products,setProducts]=useState([]);
    const[page,setPage]=useState(0);
    const[totalPages,setTotalPages]=useState(0);
const API_URL = import.meta.env.VITE_API_BASE_URL;

useEffect(() => {
    const fetchProducts = async () => {
        try {
            const response = await fetch(
                `${API_URL}/api/products?page=${page}&size=8`
            );

            const data = await response.json();

            setProducts(data.content);
            setTotalPages(data.totalPages);
        } catch (error) {
            console.error("Failed to fetch products:", error);
        }
    };
    fetchProducts();
}, [page,API_URL]);


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
             <div className="pagination">

                                        <button disabled={page===0}
                                        onClick={()=>setPage(page-1)}
                                        >
                                        Previous
                                        </button>
                                        <span>
                                            Page{page+1}of{totalPages}
                                        </span>
                                        <button
                                        disabled={page===totalPages-1}
                                        onClick={()=>setPage(page+1)}
                                        >
                                        Next
                                        </button>
                                        </div>
        </div>
        );
    }

export default HomePage