import React from 'react';
import axios from 'axios';
import { useEffect, useState } from 'react';
import YourPosts from "../YourPosts"
import '../styles/Menu.css'



import {Link} from "react-router-dom";
import logo from "../images/logo.png"
import PostSummaryCard from "./PostSummaryCard";
import Category from "../Category";
import CategoryCard from "./CategoryCard";

function Menu(props)
{


    const [categories, setCategories] = useState([]);
    const [username, setUsername] = useState("Jwill");

//use Effect added below variables
    useEffect(() =>{
        const getCategories = async () => {
            await axios.get("http://localhost:8080/api/category/").then((response) => {
                setCategories(response.data.categories);
            }).catch((err) => {
                console.log(err);
            })
        }

        getCategories().then(r => {
            console.log(r);
        });

    },[])//empty array included so request only runs once*/

   /* let content = encodeURIComponent(category.content);
    return (
        <li className="nav-item me-2 me-lg-0 d-none d-md-inline-block" key={id}>
            <Link className="nav-link"
                  to={`/${category.slug}?t=${category.slug}&c=${content}`}>{category.slug}</Link>
        </li>
    )
})*/
    return(
        <header>
            <div className="p-3 text-center bg-white border-bottom">
                <div className="container">
                    <div className="row">

                        <div className="col-md-4 d-flex justify-content-center justify-content-md-start mb-3 mb-md-0">
                            <Link className="ms-md-2" to="/">
                                <img src={logo} height="55" alt="logo"/>
                            </Link>
                        </div>


                        <div className="col-md-4">
                            <form className="d-flex input-group w-auto my-auto mb-3 mb-md-0">
                                <input autoComplete="off" type="search" className="form-control rounded"
                                       placeholder="Search"/>
                                <span className="input-group-text border-0 d-none d-lg-flex"><i
                                    className="fas fa-search"></i></span>
                            </form>
                        </div>


                        <div
                            className="col-md-4 d-flex justify-content-center justify-content-md-end align-items-center">
                            <div className="d-flex">

                                <div className="dropdown">
                                    <a className="text-reset dropdown-toggle d-flex align-items-center hidden-arrow"

                                       id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown"
                                       aria-expanded="false">
                                        {username}
                                    </a>
                                    <ul className="dropdown-menu dropdown-menu-end"
                                        aria-labelledby="navbarDropdownMenuLink">
                                        <li><a className="dropdown-item"><Link to={`/posts/create`}> Posts</Link></a></li>
                                        <li><a className="dropdown-item"><Link to={'/posts/Jwill'}>My profile</Link></a></li>
                                        <li><a className="dropdown-item"><Link to={'/Signup'}>Logout</Link></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <nav className="navbar navbar-expand-lg bg-body-tertiary">

                <div className="container justify-content-center justify-content-md-between">

                    <ul className="navbar-nav flex-row">
                        {categories ? (
                            categories.map((category) => (
                                <CategoryCard key={category.id} category={category} />

                            ))
                        ) : (
                            <p>No posts found.</p>
                        )}

                    </ul>
                </div>
            </nav>
        </header>
    );
}

export default Menu;