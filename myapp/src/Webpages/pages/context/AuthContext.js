import React, {Component} from 'react';
import axios from "axios";

export const AuthContext = React.createContext({
    currentUser: {},
    setCurrentUser: () => {},
    signIn: () =>{},
    signOut: () =>{}
})

export class AuthProvider extends Component {

    state = {
        currentUser: {},
        setCurrentUser: user =>{
            this.setState({currentUser: user})
        },
        signIn: async (username, password)=>{

            const data = {
                username: username,
                password: password
            }
            console.log(data);
            await axios.post("http://localhost:4000/login", data).then((res) =>{

                this.state.setCurrentUser(res.data.response);
                localStorage.setItem("user", JSON.stringify(this.state.currentUser));

            }).catch(err => console.log(err))
        },
        signOut: async ()=>{
            await axios.get("http://localhost:4000/logout").then( res =>{
                this.state.setCurrentUser({});
                localStorage.removeItem("user");
            }).catch(err => console.log(err));
        }
    }


    render() {

        const { children } = this.props
        const {currentUser, setCurrentUser, signIn } = this.state

        return (
            <AuthContext.Provider value={{currentUser, setCurrentUser, signIn}}>
                {children}
            </AuthContext.Provider>
        );
    }
}

export const AuthConsumer = AuthContext.Consumer;