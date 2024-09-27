import React from 'react';
import CommentLoadingCard from "./CommentLoadingCard"

function PostLoadingCard(props) {
    return (
        <div className="row mt-3">
            <div className="row">
                <div className="col">
                    <h2  className="placeholder col-4"></h2>
                    <small className="text-muted">
                        <p className="mb-1 placeholder col-1 "></p>
                        <p className="placeholder col-4"></p>
                    </small>
                </div>
            </div>

            <div className="row">
                <div className="col">
                    <div className="card shadow-lg p-3 mb-4 placeholder-glow" >
                        <div className="card-text mb-3">
                            <span className="placeholder col-12"></span>
                            <span className="placeholder col-12"></span>
                            <span className="placeholder col-12"></span>
                            <span className="placeholder col-12"></span>
                            <span className="placeholder col-12"></span>
                            <span className="placeholder col-12"></span>
                            <span className="placeholder col-12"></span>

                        </div>
                        <p className="mb-1"><small>Category: <span className="placeholder col-2"></span>
                            </small></p>
                        <p className="mb-1"><small>tags: <span className="placeholder col-2"></span>
                            </small></p>
                    </div>
                </div>
            </div>
            <div className="row">
                <div className="col">

                    <CommentLoadingCard />

                </div>

            </div>

        </div>
    );
}

export default PostLoadingCard;