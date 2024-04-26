import {useParams} from "react-router-dom";
import {useState, useRef,useEffect} from "react";
import axios from "axios"
import CommentCard from "./fragments/CommentCard"
import HTMLReactParser from "html-react-parser";



function IndividualPost() {
    const {postId} = useParams();
    const [post, setPost] = useState(null);
    const [publishedAt, setPublishedAt] = useState("")
    const [user, setUsers] = useState(null)
    const [comments, setComments] = useState(null)
    const commentText = useRef("");

    const postComment = () => {
        const now = new Date();

        console.log(commentText.current.value)
        let data = {
            comment_text: commentText.current.value,
            comments_id: null,
            commented_at: /*now.toISOString()*/null,
            post_id: null,
            user_id: null
        }

        console.log(data);

        axios.post("http://localhost:8080/api/comments/", data)
            .then((response) => {
                alert("Success")
                commentText.current.value = ""

                getComments().then(null)


            }).catch(e => {
            alert("Error")
            console.log(e);
        })
    }

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
    const getComments = async () => {
        await axios.get("http://localhost:8080/api/comments/").then(response => {
            setComments(response.data.comments);
        }).catch(e => console.log(e))
    }//Not defined in useEffect to access later

    const getUser = async(id) =>
    {
        await axios.get("http://localhost:8080/api/users/" + id).then(response =>
        {
            console.log("running get users")
            setUsers(response.data.user)
            console.log("user is " + response.data.user)
        }).catch(e => console.log(e))
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

        getComments().then(null);

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
                            <div className="row">
                                <div className="col">
                                    <h2>{post.content}</h2>
                                    <small className="text-muted">
                                        <p className="mb-1 ">&mdash; by {user?.username}</p>
                                        <p>Published: {publishedAt} </p>
                                    </small>
                                </div>
                            </div>

                            <div className="row">
                                <div className="col">
                                    <div className="card shadow-lg p-3 mb-4">
                                        <div className="card-text mb-3"> {HTMLReactParser(post.content)}</div>


                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col">
                                    <h4>Comments</h4>
                                    {
                                        (post.allowComments && comments) ?
                                            comments.map(comment => {

                                                return (
                                                    <CommentCard comment={comment} key={comment.commentId}/>
                                                )
                                            })
                                            : ""
                                    }

                                </div>

                            </div>

                        </div>
                        <div className="row mt-2">
                            <div className="col-12">
                                <h3>Tell me what you think</h3>
                            </div>
                            <div className="row">
                                <div className="col-12">
                                    <textarea className="form-control"
                                              rows="5" style={{resize: "none"}}
                                              placeholder="Enter your comment here..." id="comment" ref={commentText}/>
                                </div>
                                <div className="col offset-10 mt-4 text-end">
                                    <button type="button" className="btn btn-primary btn-rounded"
                                            onClick={postComment}>Post Comment
                                    </button>
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

export default IndividualPost;
