import {useState} from "react";
import axios from "axios";
import "./Productuploadpage.css"
function UploadImage(){

    const[name,setName]=useState("");
    const[description,setDescription]=useState("");
    const[price,setPrice]=useState("");
    const[image,setImage]=useState(null)
    const [status,setStatus]=useState('idle');
    const [message,setMessage]=useState('');


   //Capture file from input
   const handleFileChange= (e)=>{
       setImage(e.target.files[0]);
       setMessage('');
       setStatus('idle');

   };

   //Send the file via FormData through backend API
    const handleSubmit=async()=>{

        const formData=new FormData();

        formData.append("name",name);
        formData.append("description",description);
        formData.append("price",price);
        formData.append("image",image);

        try{
            setStatus('uploading');
            setMessage('');

            const response=await axios.post(
                "http://localhost:8080/api/upload/productdetails",
                formData
                );
            console.log(response.data);
        setStatus('success');
        setMessage('File uploaded successfully');

        }catch (error){
                setStatus('error');
                setMessage(error.message || 'something went wrong.');
                }
        };
        return(
            <div>
                <h3>Upload Product Detials form</h3>

                <input type="text" placeholder="Product Name"
                value={name}
                onChange={(e)=>setName(e.target.value)}
                />

                <textarea placeholder="Description"
                value={description}
                onChange={(e)=>setDescription(e.target.value)}
                />
                <input
                    type="number"
                    placeholder="Price"
                    value={price}
                    onChange={(e)=>setPrice(e.target.value)}
                />
            <input
            type="file"
            onChange={handleFileChange}
            />
            <button
                onClick={handleSubmit}
                style={{marginTop:'15px', display:'block', padding: '8px 16px'}}
            >
                {status==='uploading' ? 'uploading...' : 'Upload File'}
            </button>
            {message && (
                <p style={{marginTop:'15px', color: status==='error' ? 'red' : 'green'}}>
                    {message}
                </p>
                )}
            </div>
            );
    }
export default UploadImage;