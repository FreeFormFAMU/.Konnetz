import React, {Component} from 'react';
import axios from "axios"






function ListLayout({posts, title, description}) {


            <div className="col-12">
                <div className="row">
                    <div className="col-12">
                        <h1 className="mt-3 display-3">{title}</h1>
                        {
                            description != " " ?
                                <p className="mb-2">
                                    <small className="text-muted">{description}</small>
                                </p>
                                : ""
                        }
                    </div>
                </div>
                {posts ? (
                    posts.map((post) => {
                        <PostSummaryCard key={post.postId} post={post}/>
                    })
                ) : (
                    "Loading..."
                )}
            </div>


}

export default ListLayout;