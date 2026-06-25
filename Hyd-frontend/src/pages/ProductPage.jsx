import '../styles/ProductPage.css';
import {useParams} from "react-router-dom";
import {useEffect,useState} from "react";

 function ProductPage(){
    const {id}=useParams();

    const[product,setProduct]=useState(null);

    useEffect(()=>{
        fetch(`http://localhost:8080/api/products/${id}`)
        .then((response)=> response.json())
        .then((data)=>setProduct(data))
        .catch((error)=>console.error(error));
        },[id]);
         if(!product){
                        return <h2>Loading...</h2>
                     }

    return (
        <div className="product-page">
            <div className="img-container">
            <img src={product.mainImageUrl}
             alt={product.name}
             className="product-page-image"
             />
             </div>
             <h2>{product.name}</h2>
             <h3>{product.price}</h3>
             <p>{product.description}</p>
        </div>
        );
    }
export default ProductPage;