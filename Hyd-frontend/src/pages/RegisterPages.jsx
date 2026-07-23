import {useState} from "react";
import {registerUser} from "../services/authService";

function RegisterPage(){
const [formData,setFormData]=useState({
    firstName:"",
    middleName:"",
    lastName:"",
    email:"",
    password:"",
    confirmPassword:""
    });
const [message,setMessage]=useState("");

const handleChange=(event)=>{
    const {name,value}=event.target;
    setFormData({
        ...formData,
        [name]:value
        });
    };

const handleSubmit=async(event)=>{
    event.preventDefault();
    //check if passwords match
    if(formData.password !== formData.confirmPassword){
        setMessage("Password do not match");
        return
        }
    try{

        const message=await registerUser({
            firstName:formData.firstName,
            middleName:formData.middleName,
            lastName:formData.lastName,
            email:formData.email,
            password:formData.password,
            confirmPassword:formData.confirmPassword
            });
        setMessage(message);
        //Clear form after successful registration
        setFormData({
            firstName:"",
            middleName:"",
            lastName:"",
            email:"",
            password:"",
            confirmPassword:""
            });
        }catch(error){
            setMessage(
                error.response?.data|| "Registration Failed"
                );
            }
               };

    return (
        <div>
            <h2>HYDfashion REgistration</h2>
            <form onSubmit={handleSubmit}>
            <input
            type="text"
            name="firstName"
            placeholder="First Name"
            value={formData.firstName}
            onChange={handleChange}
            required
            />
            <br/><br/>

            <input
            type="text"
            name="middleName"
            placeholder="Middle Name"
            value={formData.middleName}
            onChange={handleChange}
            />
            <input
            type="text"
            name="lastName"
            placeholder="Last Name"
            value={formData.lastName}
            onChange={handleChange}
            required
            />
            <br/><br/>
            <input
            type="email"
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            required
            />
            <br/><br/>
            <input
            type="password"
            name="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
            required
            />
            <br/><br/>
            <input
            type="password"
            name="confirmPassword"
            placeholder="Confirm Password"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
            />
            <br/><br/>
            <button type="submit">
                Register
            </button>
            </form>
            {message && (
            <p>{message}</p>)}
        </div>
        );
    }

export default RegisterPage;