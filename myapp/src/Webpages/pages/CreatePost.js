import React, { useState, useEffect} from 'react';
import axios from "axios";
import ListLayout from "./fragments/ListLayout";

const CreatePost = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleContentChange = (event) => {
        setContent(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        // Here, you might want to send the post data to a server
        console.log('Submitting', { title, content });
        alert(`Post created!\nTitle: ${title}\nContent: ${content}`);

        // Clear the form
        setTitle('');
        setContent('');
    };

    return (
        <div>
            <h1>Create Post</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>
                        Title:
                        <input
                            type="text"
                            value={title}
                            onChange={handleTitleChange}
                        />
                    </label>
                </div>
                <div>
                    <label>
                        Content:
                        <textarea
                            value={content}
                            onChange={handleContentChange}
                        />
                    </label>
                </div>
                <div>
                    <button type="submit">Create Post</button>
                </div>
            </form>
        </div>
    );
};

export default CreatePost;
