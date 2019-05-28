import React from 'react';
import Navigation from './navigation/Navigation'
import MoviesList from './content/movies_list/MoviesList'
import {Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, Button} from 'reactstrap';

export default class Main extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
    };
  }

  render() {
    return (
      <div>
        <Navigation/>
        <MoviesList/>
      </div>
    );
  }
}
