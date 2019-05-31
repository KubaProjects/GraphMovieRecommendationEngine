import React from 'react';
import MovieItem from './MovieItem';
import Pagination from 'rc-pagination';

export default class MoviesList extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      current: 1,
      total:0
    };
  }

  onChange = (page) => {
    this.setState({
      current: page,
      movies:null
    });
    this.loadMovies(page);
  }

  componentDidMount(){
    this.loadMovies(1);
  }

  loadMovies = (page) => {
    const currentPage = page - 1;
    const url = 'http://localhost:8080/movies?size=20&page='+currentPage;

    fetch(url)
    .then((response) => {
      return response.json();
    })
    .then((page) => {
      this.setState({movies:page.content, total:page.totalPages})
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
          {movies ? movies.map(this.renderMoviesPerPage) : "" }
        </ul>
        <div className="row justify-content-center">
          <Pagination onChange={this.onChange} current={this.state.current} total={this.state.total} />
        </div>
      </div>
    );
  }
}
