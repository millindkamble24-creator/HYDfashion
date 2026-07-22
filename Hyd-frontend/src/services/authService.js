import axiosInstance from "../api/axiosConfig";

export const registerUser=async(userData)=>{
    const response=await axiosInstance.post("/auth/register",userData);
    return response.data;
};