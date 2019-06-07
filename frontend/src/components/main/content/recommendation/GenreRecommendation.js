import React from 'react';
import {Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, Button} from 'reactstrap';
import MovieItem from '../movies_list/MovieItem';
import Pager from 'react-pager';
import Pagination from 'rc-pagination';

export default class GenreRecommendation extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      movies:null
    };
  }


  componentDidMount(){
    this.loadMovies(1);
  }

  loadMovies = (page) => {
    const currentPage = page - 1;
    const url = 'http://localhost:8080/movies/by-genre';

    fetch(url,{
    credentials: 'include'})
    .then((response) => {
      return response.json();
    })
    .then((res) => {
      this.setState({movies:res, total:20})
    });
  }

  renderMoviesPerPage = (movie,i) => {
    return <MovieItem movie={movie} key={movie.id}/>;
  }

  render() {
    const movies = this.state.movies;

    return (
      <div className="container text-center">
        <ul className="text-center" style={{'listStyleType': 'none'}}>
          {movies ? movies.map(this.renderMoviesPerPage) : <i className="fas fa-refresh fa-spin mt-5" style={{'fontSize': '40px'}}></i> }
        </ul>
      </div>
    );
  }
}
