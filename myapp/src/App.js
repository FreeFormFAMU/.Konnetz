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
import YourIndividualPost from "./Webpages/pages/YourIndividualPost";
import ManagePosts from "./Webpages/pages/ManagePosts";
import CategoryPosts from "./Webpages/pages/CategoryPosts"




function App()
{
  return(
      <Router>
          <div className="container">
              <Menu />
        <Routes>
          <Route path="/" element={<Home />} />
            <Route path="/posts/category/:slug" element={<CategoryPosts />}/>
            <Route path="/:slug" element={<Category />}  />
          <Route path="/posts/:postId" element={<IndividualPost />} />
            <Route path="/yourposts/:postId" element={<YourIndividualPost/>}/>
            <Route path="/posts/create" element={<CreatePost />}/>
            <Route path="/posts/test" element={<ManagePosts />}/>
          <Route element={<NotFound/>} path="*" />
        </Routes>
              </div>
          <Footer />
      </Router>
  );
}

export default App;