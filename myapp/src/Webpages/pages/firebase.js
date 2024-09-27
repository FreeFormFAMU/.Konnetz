// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import {
    getAuth,
} from 'firebase/auth';

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyCCEbuDQm9gMzp4TbH14vaHZZo1ZYNgJ_8",
    authDomain: "connetz.firebaseapp.com",
    projectId: "connetz",
    storageBucket: "connetz.appspot.com",
    messagingSenderId: "1016796380716",
    appId: "1:1016796380716:web:41a7b5cacd8caa0b825b89",
    measurementId: "G-M65XYB63RN"
};

const app = initializeApp(firebaseConfig);

const auth = getAuth(app);

export { auth, app };