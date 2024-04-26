import React, { useState } from 'react';

import axios from 'axios';

function CreatePost() {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleContentChange = (event) => {
        setContent(event.target.value);
    };

    const handleCategoryChange = (event) => {
        setSelectedCategory(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        setIsSubmitting(true);
        setErrorMessage('');

        const now = new Date();
        let created_at = now;
        let updated_at = now;
        let user_id = "test"

        try {
            const response = await axios.post('http://localhost:8080/api/posts/', {
                title,
                content,
                created_at,
                updated_at,
                user_id
                // Add other post properties here (e.g., allowComments)
            });

            console.log('Post created successfully:', response.data);
            // Handle successful post creation (e.g., redirect to post list)
            alert('Your post has been created successfully!');

        } catch (error) {
            console.error('Error creating post:', error);
            setErrorMessage('An error occurred while creating your post. Please try again later.');
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="container mt-3">
            <h2>Create Post</h2>
            {errorMessage && <div className="alert alert-danger" role="alert">{errorMessage}</div>}
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="title" className="form-label">Title</label>
                    <input
                        type="text"
                        className="form-control"
                        id="title"
                        value={title}
                        onChange={handleTitleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="content" className="form-label">Content</label>
                    <textarea
                        className="form-control"
                        id="content"
                        rows="10"
                        value={content}
                        onChange={handleContentChange}
                        required
                    />
                </div>

                <button type="submit" className="btn btn-primary" disabled={isSubmitting}>
                    {isSubmitting ? 'Submitting...' : 'Create Post'}
                </button>
            </form>
        </div>
    );
}

export default CreatePost;
