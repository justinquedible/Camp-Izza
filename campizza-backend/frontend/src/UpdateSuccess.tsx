// Page that users see when they updated their password successfully

import React from 'react';

import {Redirect} from 'react-router-dom';
import {FormControl, Form, Button} from 'react-bootstrap';
import './Login.css';
import AuthService from './services/auth-service';


interface loginDetailsProp {
    email: string,
    password: string
}

const UpdateSuccess: React.FC<loginDetailsProp> = () => {
    const [values, setValues] = React.useState({
        loginEmail: '',
        password: ''
    });
    const handleSubmit = async (e: { preventDefault: () => void; }) => {
        e.preventDefault();
        const {loginEmail, password} = values;
        await AuthService.login(loginEmail, password);
        // const login = {loginEmail, password};
        // await axios.post(URL + '/parentLogins/add', login);
        window.location.href="/#/parent"
        window.location.reload();

    };
    const handleChange = (name: string) => (e: { target: { value: any; }; }) => {
        setValues({...values, [name]: e.target.value});
    };

    return (
        <div className={"login"}>

            <div className={"login-form"}>
                <h3>
                    Password Updated
                </h3>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId={"Email"}>

                        <Form.Control type={"email"} placeholder={"Email"} className={"login-form-input"}
                                      onChange={handleChange('loginEmail')}/>
                    </Form.Group>
                    <Form.Group controlId={"Password"}>
                        <span className={"rounded-pill"}>

                        <Form.Control type={"password"} placeholder={"Password"} className={"login-form-input"}
                                      onChange={handleChange('password')}/>
                        </span>
                    </Form.Group>

                    <Button variant="outline-primary" className="login-button"  type="submit">
                        Sign In
                    </Button>
                </Form>
                <br />

                <Button className="newuser-button" href="/#/signup">
                    New User
                </Button>
                <br />
                <Button className="password-button" href="/#/resetpassword">
                    Forgot Password?
                </Button>

            </div>

        </div>
    );
}

export default UpdateSuccess;