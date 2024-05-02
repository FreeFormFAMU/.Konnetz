import Home from "./Webpages/pages/Home"
import React from "react"
import Menu from "./Webpages/pages/fragments/Menu"
import Footer from "./Webpages/pages/fragments/Footer"
import './App.css'


import{
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom";


import Category from "./Webpages/pages/Category";
import IndividualPost from "./Webpages/pages/IndividualPost";
import NotFound from "./Webpages/pages/NotFound";
import CreatePost from "./Webpages/pages/CreatePost";
import YourPosts from "./Webpages/pages/YourPosts";
import Login from "./Webpages/pages/Login";
import Signup from "./Webpages/pages/Signup";
import ManagePosts from "./Webpages/pages/ManagePosts";
import YourIndividualPost from "./Webpages/pages/YourIndividualPost";





function App() {
    return (
        <Router>
            <div className="container">
                <Menu />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/signup" element={<Signup />} />
                    <Route path="/posts/category/:slug" element={<Category />} />
                    <Route path="/posts/:postId" element={<IndividualPost />} />
                    <Route path="/posts/create" element={<CreatePost />} />
                    <Route path="/posts/Jwill" element={<ManagePosts />} />
                    <Route path="/yourposts/:postId" element={<YourIndividualPost />}/>
                    <Route path="*" element={<NotFound />} />
                </Routes>
                <Footer />
            </div>
        </Router>
    );
}

export default App;
