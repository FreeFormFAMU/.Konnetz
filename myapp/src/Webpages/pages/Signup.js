import React, { useState } from 'react';
import firebase from './firebase-config';

function Signup() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSignup = async () => {
        try {
            await firebase.auth().createUserWithEmailAndPassword(email, password);
            console.log("Account created!");
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