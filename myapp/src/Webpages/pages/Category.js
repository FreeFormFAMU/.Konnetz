import { useParams, useSearchParams,} from "react-router-dom";
import {Component, useEffect, useState} from "react";
import ListLayout from "./fragments/ListLayout";
import axios from "axios"




function Category()
{





        const {slug} = useParams();
        const [searchParams, setSearchParams] = useSearchParams();
        const title = searchParams.get("t")
        const description = searchParams.get("c")
        const [posts, setPosts] = useState(null);



        useEffect(() => {
            const getPosts = async () => {
                await axios.get("http://localhost:8080/api/post/c/" + slug).then((response) => {
                    setPosts(response.data.posts);

                }).catch((err) => {
                    console.log(err);
                })
            }
            getPosts().then(null);
        }, [slug])


        return (<ListLayout posts={this.posts} title={title} description={description}/>);

}

export default Category;

