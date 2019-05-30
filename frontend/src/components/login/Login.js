import React, { Component } from "react";
import {Button, FormGroup, Input , Label} from 'reactstrap';
import "./Login.css";
import { Link, Redirect } from 'react-router-dom';

export default class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
      login: "",
      password: ""
    };
  }

  login = (login, password) => {
    const formData = new FormData();

    formData.append("username", login);
    formData.append("password", password);

    console.log("login:"+login)
    console.log("password:"+password)

    fetch("http://localhost:8080/login", {
      credentials: 'include',
      method: 'POST',
      body: formData
    }).then(response => {
      console.log(response.status);
      response.status === 200 ? this.props.setIsAuthenticated(true) : this.props.setIsAuthenticated(false);
    }).catch(e => console.log(e));

    this.props.history.push('/');
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = event => {
    event.preventDefault();
    this.login(this.state.login, this.state.password);
  }

  render() {
    return (
      <div className="Login">
        <div className="text-center">
          <Link to={'/'} style={{ textDecoration: 'none', color: '#000000' }}>
            <h1>Movies</h1>
          </Link>
        </div>
        <form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label>Login</Label>
            <Input type="text" name="login" id="login" placeholder="login" onChange={this.handleChange}/>
          </FormGroup>
          <FormGroup >
            <Label>Password</Label>
            <Input type="password" name="password" id="password" placeholder="password" onChange={this.handleChange}/>
          </FormGroup>
          <Button
            block
            onClick={this.handleSubmit}
            type="submit"
          >
            Login
          </Button>
        </form>
      </div>
    );
  }
}
