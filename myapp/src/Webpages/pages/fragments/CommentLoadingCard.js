import React from 'react';

function CommentLoadingCard({comment}) {
    const imgUrl = "https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp"

    return (
        <div className="card mb-3 placeholder-glow">
            <div className="row g-0">
                <div className="col-md-2 bg-light ">
                    <p className="text-center mt-5">
                        <img className="rounded-circle" src={imgUrl} alt="user icon"/><br/>
                        <span className="placeholder col-4"></span><br/>
                        <span className="placeholder col-4"></span>

                    </p>

                </div>
                <div className="col-md-10">
                    <div className="card-body">
                        <span className="placeholder col-4"></span>
                        <span className="placeholder col-9"></span>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CommentLoadingCard;
