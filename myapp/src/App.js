import Home from "./Webpages/pages/Home"
import React from "react"
import Menu from "./Webpages/pages/fragments/Menu"
import Footer from "./Webpages/pages/fragments/Footer"

import{
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom";
import Category from "./Webpages/pages/Category";


function App()
{
  return(
      <Router>
          <div className="container">
              <Menu />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/:slug" element={<Category />}  />
        </Routes>
              </div>
          <Footer />
      </Router>
  );
}

export default App;