import {useParams} from "react-router-dom";
import React, {useState, useRef,useEffect} from "react";
import axios from "axios"
import CommentCard from "./fragments/CommentCard"
import HTMLReactParser from "html-react-parser";
import '../pages/styles/Home.css';

let idnum = 0;

function YourIndividualPost() {
    const {postId} = useParams();
    const [post, setPost] = useState(null);
    const [publishedAt, setPublishedAt] = useState("")
    const [user, setUsers] = useState(null)
    const [comments, setComments] = useState(null)
    const commentText = useRef("");
    const contentText = useRef("");
    const titlecontent = useRef("");


    /*const getComments = async () => {
        await axios.get("http://localhost:8080/api/comments/" + postId).then(response => {
            setComments(response.data.comments);
        }).catch(e => console.log(e))
    }//Not defined in useEffect to access later
*/

    /*<p className="mb-1"><small>tags: {post.tags.map((tag, idx) => {
                                            return <span className="badge bg-secondary me-2" key={idx}>Test said tag in brackets before</span>
                                        })
                                        }</small></p>*/

    //Asynchronous function to fetch comments


    const getUser = async(id) =>
    {
        await axios.get("http://localhost:8080/api/users/" + id).then(response =>
        {
            console.log("running get users")
            setUsers(response.data.user)
            console.log("user is " + response.data.user)
        }).catch(e => console.log(e))
    }

    const removePost = (id) =>
    {
        axios.delete("http://localhost:8080/api/posts/remove/" + id)
            .then((response) => {
                alert("Success")

                console.log(id)

            }).catch(e => {

            console.log(e);
        })
    }

    const updatePost = (id) =>
    {
        let data = {
            content: contentText.current.value,
            title: titlecontent.current.value,

        }
        axios.put("http://localhost:8080/api/posts/"+id, data).then((response) =>{
            alert("Success")
            console.log(id)

    }).catch(e => {

        console.log(e);
    })
}

    useEffect(() => {
        const getPost = async () => {
            await axios.get("http://localhost:8080/api/posts/" + postId).then((response) => {
                setPost(response.data.post);
                console.log("Individual Log is " + response.data.post)
                console.log("User id is " + response.data.post.user_id)
                getUser(response.data.post.user_id)
                setPublishedAt(new Date(response.data.post.created_at.seconds * 1000).toDateString());

            }).catch(e => {
                console.log(e)
            })
        }

        getPost().then(null);

        getUser()

    }, [postId])



    /*let categories = post ? post.categoryId.map((category, idx) =>{
        return category.title
    }) : []*/

    /*< p
    className = "mb-1" > < small > Category
:
    {
        categories.join(", ")
    }
</small></p>*/


    return (
        <>

            {


                post ?
                    <>

                        <div className="row mt-3">
                            <div className="col">
                                <h2>{post.title}</h2>
                                <small className="text-muted">
                                    <p className="mb-1">&mdash; by {user?.username}</p>
                                    <p>Published: {publishedAt}</p>
                                </small>
                            </div>

                            <div className="col">
                                <div className="card shadow-lg p-3 mb-4">
                                    {/* Post Content Section */}
                                    <div className="card-text mb-3">{HTMLReactParser(post.content)}</div>

                                    {/* Post Actions Section (buttons and text areas) */}
                                    <div className="d-flex justify-content-end mt-4">
                                        <button
                                            type="button"
                                            className="btn btn-primary btn-rounded me-2"
                                            onClick={() => removePost(post.id)}
                                        >
                                            Remove Post
                                        </button>
                                        <button
                                            type="button"
                                            className="btn btn-primary btn-rounded"
                                            onClick={() => updatePost(post.id)}
                                        >
                                            Update Post
                                        </button>
                                    </div>
                                    <div className="mt-3">
                <textarea
                    className="form-control"
                    rows="5"
                    style={{resize: "none"}}
                    placeholder="Enter your post content here..."
                    id="content"
                    ref={contentText}
                />
                                        <textarea
                                            className="form-control mt-2"
                                            rows="3"
                                            style={{resize: "none"}}
                                            placeholder="Enter your post title here..."
                                            id="title"
                                            ref={titlecontent}
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>

                    </>
                    :
                    "No Individual Post"
            }
        </>
    );

}

export default YourIndividualPost;
