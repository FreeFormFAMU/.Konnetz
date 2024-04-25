import React from "react";
import {Link} from "react-router-dom";

//Testing, will be using post.slug in actual project
let test = "5IvxgfV9IMqkCIy5o9L2"

function PostSummaryCard({post}) {

    const categories = post.categoryId?.map((category, idx) => category.title); // Use optional chaining
    //<p className="mb-1"><small>./Category: {categories.join(", ")}</small></p>
    //<h6 className="card-subtitle mb-2 text-muted">by {post.user_id.username}</h6>
    return (
        //<div className="card shadow-lg p-3 mb-4" key={post.post_id}>

        <div className="card-body">
            <h5 className="card-title"><Link to={`/post/5IvxgfV9IMqkCIy5o9L2`}>{post.content}</Link></h5>

            <p className="card-text">{post.content}</p>


        </div>
        //</div>
    );


}

export default PostSummaryCard;