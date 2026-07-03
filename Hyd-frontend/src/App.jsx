import {Routes,Route} from 'react-router-dom'
import { useState } from 'react'
import UploadImage  from "./pages/Productuploadform"
import  HomePage  from "./pages/HomePage"
import { Layout } from './components/Layout';
import ProductPage from './pages/ProductPage';
import { CategoryProvider } from './context/CategoryContext';

function App() {



  return (
      <CategoryProvider>
      <Routes>
          <Route path="/" element={<Layout />}>
          <Route index element={<HomePage />}/>
          <Route path="product/:id" element={<ProductPage />}/>
          </Route>
          <Route path="/upload" element={<UploadImage />}/>

      </Routes>
      </CategoryProvider>
  );
}
export default App
