function CommentCard({comment})
{
    const md5 = require("blueimp-md5");
    const hash = md5(comment.authorId.email);

    const imgUrl = "http://www.gravatar.om/avatar/" + hash + "?d=identicon"

    return (<div className="card mb-3">
        <div className="row g-0">
            <div className="col-md-2 bg-light ">
                <div className="text-center mt-5">
                    <img className="rounded-circle" src={imgUrl} alt="user icon"/>
                    <p>{comment.authorId.username}</p>
                    <small className="text-muted">
                        {new Date(comment.publishedAt.seconds * 1000).toDateString()}
                    </small>
                </div>

            </div>
            <div className="col-md-10">
                <div className="card-body">
                    <h5 className="card-title">{comment.title}</h5>
                    <p className="card-text">{comment.content}</p>
                </div>
            </div>
        </div>
    </div>)
}
export default CommentCard;