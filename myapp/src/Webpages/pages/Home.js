import React, { useState, useEffect } from 'react';
import axios from "axios";
import ListLayout from "./fragments/ListLayout";

const Home = () => {
    // State to hold posts data
    const [posts, setPosts] = useState(null);

    // Fetch data on component mount
    useEffect(() => {
        const fetchData = async () => {
            const url = "http://localhost:8080/api/posts/";
            console.log("Hi guys")
            try {

                const response = await axios.get(url);
                console.log("Test:", response.data);
                console.log("Fetched posts:", response.data.posts); // Log fetched posts
                //setPosts(response.data.posts);
                setPosts(response.data.posts);
            } catch (err) {
                console.error(err);
            }
        };
        fetchData();
    }, []); // Empty dependency array to run only once

    return (
        <ListLayout posts={posts} title="Latest Posts" description="" />
    );
};

export default Home;
