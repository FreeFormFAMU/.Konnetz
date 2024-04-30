import React, {Component} from 'react';
import {Card, Form, Button, Row, Col} from "react-bootstrap";
import {AuthContext} from "./context/AuthContext";

class Login extends Component {

    static contextType = AuthContext;
    constructor(props) {
        super(props);
        this.usernameRef = React.createRef();
        this.passwordRef = React.createRef();
        this.handleSubmit = this.handleSubmit.bind(this);

    }
    componentDidMount() {
        let user = JSON.parse(localStorage.getItem("user"));
        if(user && user.length > 0)
            this.props.history.push("/home");
    }

    async handleSubmit(evt){

        evt.preventDefault();
        const {signIn} = this.context
        await signIn(this.usernameRef.current.value, this.passwordRef.current.value);
        this.props.history.push("/home");
    }

    render() {

        return (
            <Row>
                <Col>
                    <Card>
                        <Card.Body>
                            <h2 className="text-center mb-4">Sign In</h2>
                            <Form onSubmit={this.handleSubmit}>
                                <Form.Group id="username" className="mb-3">
                                    <Form.Label>Username</Form.Label>
                                    <Form.Control type="text" required ref={this.usernameRef} />
                                </Form.Group>
                                <Form.Group id="password" className="mb-3">
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control type="password" required ref={this.passwordRef} />
                                </Form.Group>

                                <Button className=" w-100" type="submit">Sign In</Button>
                            </Form>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>

        );
    }
}

export default Login;