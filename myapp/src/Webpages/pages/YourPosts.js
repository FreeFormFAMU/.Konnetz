import React, {Component, useEffect, useRef, useState} from 'react';
import {useParams} from "react-router-dom";
import ListLayout from "./fragments/ListLayout";
import axios from 'axios'








// ListLayout.js

function YourPosts() {
    const [posts, setPosts] = useState(null);

    // Fetch data on component mount
    useEffect(() => {
        const fetchData = async () => {
            const url = "http://localhost:8080/api/posts/users/" + "Jwill";//Will be replaced when authentication is added to be autheticated userID
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
    }, []); //



    return (
        <ListLayout posts={posts} title="Here are your Posts:" description="" />
    );
}

export default YourPosts;