import React from 'react';
import Navigation from './navigation/Navigation'
import MoviesList from './content/movies_list/MoviesList'
import MoviePage from './content/movie_page/MoviePage'
import Survey from './content/survey/Survey';
import GenreRecommendation from './content/recommendation/GenreRecommendation';
import KnnRecommendation from './content/recommendation/KnnRecommendation';
import {Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, Button} from 'reactstrap';
import { Switch, Route, Redirect } from 'react-router-dom'

export default class Main extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
    };
  }


  render() {
    return (
      <div>
        <Navigation isAuthenticated={this.props.isAuthenticated} setIsAuthenticated={this.props.setIsAuthenticated}/>
        <Switch>
          <Route exact path='/' component={MoviesList}/>
          <Route exact path='/movies' component={MoviesList}/>
          <Route exact path='/recommendation/by-genre' component={GenreRecommendation}/>
          <Route exact path='/recommendation/by-knn' component={KnnRecommendation}/>
          <Route exact path='/movies/:id' component={MoviePage}/>
        </Switch>
      </div>
    );
  }
}
