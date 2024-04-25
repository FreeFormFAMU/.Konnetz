import React, {Component} from 'react';









// ListLayout.js

function YourPosts({ posts, title, description, isLoading, error }) {
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
                    <h1 className="mt-3 display-3">{title}</h1>
                    {description && (
                        <p className="mb-2">
                            <small className="text-muted">{description}</small>
                        </p>
                    )}
                </div>
            </div>

        </div>
    );
}

export default YourPosts;