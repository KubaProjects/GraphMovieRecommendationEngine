import React from 'react';
import Navigation from './navigation/Navigation'
import MoviesList from './content/movies_list/MoviesList'
import MoviePage from './content/movie_page/MoviePage'
import {Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, Button} from 'reactstrap';
import { Switch, Route } from 'react-router-dom'

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
        <Switch>
          <Route exact path='/' component={MoviesList}/>
          <Route exact path='/movies' component={MoviesList}/>
          <Route exact path='/movies/:id' component={MoviePage}/>
        </Switch>
      </div>
    );
  }
}
/**/
