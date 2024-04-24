import React from "react";

function PostSummaryCard({post})
{
    return
    const categories = post.categoryId?.map((category, idx) => category.title); // Use optional chaining

    return (
        <div className="card shadow-lg p-3 mb-4" key={post.postId}>
            <div className="card-body">
                <h5 className="card-title">{post.title}</h5>
                <h3>Testing</h3>
                <h6 className="card-subtitle mb-2 text-muted">by {post.authorId.username}</h6>
                <p className="card-text">{post.summary}</p>
                <p className="mb-1"><small>Category: {categories.join(", ")}</small></p>
                <p className="mb-1"><small>tags: {post.tags.map((tag, idx) => (
                    <span className="badge bg-secondary me-2" key={idx}>{tag}</span>
                ))}</small></p>
            </div>
        </div>
    );


}