import {Header} from './Header';
import {Navbar} from './Navbar';
import {Outlet} from 'react-router-dom';

export function Layout(){
    return(
        <>
        <Header />
        <Navbar />
        <main>
        <Outlet />
        </main>
        </>
        );
    }