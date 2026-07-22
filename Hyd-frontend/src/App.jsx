import {Routes,Route} from 'react-router-dom';
import UploadImage  from "./pages/Productuploadform";
import  HomePage  from "./pages/HomePage"
import { Layout } from './components/Layout';
import ProductPage from './pages/ProductPage';
import { CategoryProvider } from './context/CategoryContext';
import { SearchProvider } from './context/SearchContext';
import RegisterPage from './pages/RegisterPages';

function App() {

  return (
      <SearchProvider>
      <CategoryProvider>
      <Routes>
          <Route path="/" element={<Layout />}>
          <Route index element={<HomePage />}/>
          <Route path="product/:id" element={<ProductPage />}/>
          </Route>
          <Route path="/upload" element={<UploadImage />}/>
          <Route path="/register" element={<RegisterPage />}/>
      </Routes>
      </CategoryProvider>
      </SearchProvider>
  );
}
export default App
