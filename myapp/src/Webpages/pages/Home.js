import React, {Component} from 'react';
import axios from "axios"
import ListLayout from "./fragments/ListLayout";



class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {posts: null};
    }

    componentDidMount = async() => {
    const url = "http://localhost:8080/api/post/"
    try{
        const response = await axios.get(url);
        this.setState({posts: response.data.posts });
    } catch(err) {
    console.error(err);
    }
}
    render() {
        return(
        <ListLayout posts={this.posts} title="Latest Posts" description="" />);
    }
}

export default Home;