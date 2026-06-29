import '../styles/HomePage.css';
import {Link} from 'react-router-dom';
import {useEffect,useState} from 'react';

function HomePage(){
    const[products,setProducts]=useState([]);
    const [cursor,setCursor]=useState(null);
    const[hasNext,setHasNext]=useState(true);
    //const[page,setPage]=useState(0);
    //const[totalPages,setTotalPages]=useState(0);
const API_URL = import.meta.env.VITE_API_BASE_URL;

useEffect(() => {
    const fetchProducts = async () => {
        let url=`${API_URL}/api/products`;
        try {
            const response = await fetch(url);

            const data = await response.json();
            console.log(data);
            console.log(data.nextCursor);
            console.log(data.hasNext);

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

//load more products when scroll function
  const loadMore = async () => {

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