import {useParams} from "react-router-dom";
import {useState, useRef,useEffect} from "react";
import axios from "axios"
import CommentCard from "./fragments/CommentCard"
import HTMLReactParser from "html-react-parser";



function IndividualPost() {
    const {postId} = useParams();
    const [post, setPost] = useState(null);
    const [publishedAt, setPublishedAt] = useState("")
    const [comments, setComments] = useState(null)
    const commentText = useRef("");

    const postComment = ()=>{
        const now = new Date();

        console.log(commentText.current.value)
        let data = {
            content: commentText.current.value,
            title: "",
            published: true,
            createdAt: now.toISOString(),
            publishedAt: now.toISOString(),
            postId: postId,
            authorId:`YOUR_AUTHOR_ID`
        }

        axios.post("http://localhost:8080/api/comment/", data)
            .then((response)=>{
                alert("Succes")
                commentText.current.value = ""

                getComments().then(null)


            }).catch(e => {
            alert("Error")
            console.log(e);
        })
    }

    //Asynchronous function to fetch comments
    const getComments = async () => {
        await axios.get("http://localhost:8080/api/comments/" + postId).then(response => {
            setComments(response.data.comments);
        }).catch(e => console.log(e))
    }//Not defined in useEffect to access later

    useEffect(() => {
        const getPost = async () => {
            await axios.get("http://localhost:8080/api/post/" + postId).then((response) => {
                setPost(response.data.post);
                setPublishedAt(new Date(response.data.post.publishedAt.seconds * 1000).toDateString());
            }).catch(e => {
                console.log(e)
            })
        }

        getPost().then(null);

        getComments().then(null);
    }, [postId])

    let categories = post ? post.categoryId.map((category, idx) =>{
        return category.title
    }) : []

    return (
        <>

            {
                post  ?
                    <>
                        <div className="row mt-3">
                            <div className="row">
                                <div className="col">
                                    <h2>{post.title}</h2>
                                    <small className="text-muted">
                                        <p className="mb-1 ">&mdash; by {post.authorId.username}</p>
                                        <p>Published: {publishedAt} </p>
                                    </small>
                                </div>
                            </div>

                            <div className="row">
                                <div className="col">
                                    <div className="card shadow-lg p-3 mb-4" >
                                        <div className="card-text mb-3"> {HTMLReactParser(post.content)}</div>
                                        <p className="mb-1"><small>Category: { categories.join(", ")
                                        }</small></p>
                                        <p className="mb-1"><small>tags: {post.tags.map((tag, idx) =>{
                                            return <span className="badge bg-secondary me-2" key={idx}>{tag}</span>
                                        })
                                        }</small></p>
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
                                              rows="5" style={{resize:"none"}}
                                              placeholder="Enter your comment here..." id="comment" ref={commentText} />
                                </div>
                                <div className="col offset-10 mt-4 text-end">
                                    <button type="button" className="btn btn-primary btn-rounded" onClick={postComment}>Post Comment</button>
                                </div>
                            </div>
                        </div>
                    </>
                    :
                    ""
            }
        </>
    );

}

export default IndividualPost;
