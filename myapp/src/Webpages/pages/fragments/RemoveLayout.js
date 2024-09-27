import React, {Component} from 'react';
import PostSummaryCard from "./PostSummaryCard";
import '../styles/Home.css'
import RemovePostCard from "./RemovePostCard";








// ListLayout.js

function RemoveLayout({ posts, title, description, isLoading, error }) {
    if (isLoading) {
        return <p>Loading posts...</p>;
    }

    if (error) {
        return <p>Error fetching posts: {error.message}</p>;
    }


    return (
        <div className="col-12">
            <div className="row">
                <div className="col-12">
                    <h1 className="mt-2 display-3">{title}</h1>
                    {description && (
                        <p className="mb-2">
                            <small className="text-muted">{description}</small>
                        </p>
                    )}
                </div>
            </div>
            {posts ? (
                posts.map((post) => (
                    <RemovePostCard key={post.id} post={post} />
                ))
            ) : (
                <p>No posts found.</p>
            )}
        </div>
    );
}

export default RemoveLayout;