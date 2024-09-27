import React, { useState, useEffect} from 'react';
import axios from "axios";
import ListLayout from "./fragments/ListLayout";
import './styles/Form.css'

const CreatePost = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const[category, setCategory] = useState('');

    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const myStyle = {
        width: '100%',
        padding: '10px',
        border: '1px solid #ccc',
        borderRadius: '4px',
        fontSize: '16px',
    };

    const newPost = () => {
        const now = new Date();



        let data = {
            content: content,
            title: title,
            slug: category,
            post_id: null,
            updated_at: now,
            created_at: now,
            user_id: "Jwill"
        }

        axios.post("http://localhost:8080/api/posts/", data)
            .then((response) => {
                alert("Success")



            }).catch(e => {
            alert("Error")
            console.log(e);
        })
    }

    const handleContentChange = (event) => {
        setContent(event.target.value);
    };

    const handleCategoryChange = (event) => {
        setCategory(event.target.value);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        newPost();
        // Here, you might want to send the post data to a server
        console.log('Submitting', { title, content });
        alert(`Post created!\nTitle: ${title}\nContent: ${content}`);

        // Clear the form
        setTitle('');
        setContent('');
        setCategory('');
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
                <br/>
                <div></div>
                <div>
                    <label>
                        Content:
                        <textarea style={myStyle}
                                  value={content}
                                  onChange={handleContentChange}
                        />
                    </label>
                </div>

                <div>
                    <label>
                        Category:
                        <textarea style={myStyle}
                                  value={category}
                                  onChange={handleCategoryChange}
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
