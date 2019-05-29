import React from 'react';
import './Navigation.css';
import {Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, Button} from 'reactstrap';

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

  render() {
    return (
      <div>
        <Navbar color="dark" dark expand="sm">
          <NavbarToggler onClick={this.toggle} />
          <NavbarBrand href="/">Movies</NavbarBrand>
          <Collapse isOpen={this.state.isOpen} navbar>
            <Nav className="ml-auto" navbar>
              <NavItem className="mr-2">
                <Button color="secondary">Survey</Button>
              </NavItem>
              <NavItem className="ml-2">
                <Button color="primary">Login</Button>
              </NavItem>
            </Nav>
          </Collapse>
        </Navbar>
      </div>
    );
  }
}
