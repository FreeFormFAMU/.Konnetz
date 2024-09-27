import React, { useState } from 'react';
import { createUserWithEmailAndPassword } from "firebase/auth";
import { auth } from "./firebase";
import { useNavigate } from 'react-router-dom';

function Signup() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate(); // Setup useNavigate

    const handleSignup = async () => {
        try {
            await createUserWithEmailAndPassword(auth, email, password);
            console.log("Account created!");
            navigate('/Menu'); // Navigate to the menu or home page
        } catch (error) {
            console.error(error.message);
        }
    };

    return (
        <div>
            <input type="email" value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" />
            <input type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Password" />
            <button onClick={handleSignup}>Sign Up</button>
        </div>
    );
}

export default Signup;
