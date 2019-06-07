import React from 'react';
import './Navigation.css';
import {Collapse, Navbar, NavbarToggler, Nav, NavItem, Button} from 'reactstrap';
import { Link, Redirect } from 'react-router-dom'

export default class Navigation extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      isOpen: false
    };
  }

  toggle = () => {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  logout = (cb) => {
      fetch("http://localhost:8080/logout", {
        credentials: 'include',
        method: 'POST',
      })
        .then(e => this.props.setIsAuthenticated(false))
        .catch(e => console.log(e));

      return (<Redirect to={{ pathname: "/" }} />);
    }


  render() {
    const isAuthenticated = this.props.isAuthenticated;

    return (
      <div>
        <Navbar color="dark" dark expand="sm">
          <NavbarToggler onClick={this.toggle} />
          <Link to={'/'} style={{ textDecoration: 'none', color: '#ffffff' }}>Movies</Link>
          <Collapse isOpen={this.state.isOpen} navbar>
            <Nav className="ml-auto" navbar>
              <NavItem className="mr-2">
                <Link to={'/survey'} style={{ textDecoration: 'none', color: '#000000' }}><Button color="warning">Survey</Button></Link>
              </NavItem>
              <NavItem className="mr-2">
                <Link to={'/recommendation/by-genre'} style={{ textDecoration: 'none', color: '#000000' }}><Button color="secondary">Genre recommendation</Button></Link>
              </NavItem>
              <NavItem className="mr-2">
                <Link to={'/recommendation/by-knn'} style={{ textDecoration: 'none', color: '#000000' }}><Button color="success">KNN recommendation</Button></Link>
              </NavItem>
              <NavItem className="ml-2">
                {isAuthenticated ?
                  <Button color="primary" onClick={this.logout}>Log out</Button> :
                  <Link to={'/login'} style={{ textDecoration: 'none', color: '#000000' }}><Button color="primary">Log in</Button></Link>
                }
              </NavItem>
            </Nav>
          </Collapse>
        </Navbar>
      </div>
    );
  }
}
