import React from 'react';
import MovieItem from '../movies_list/MovieItem';

const API_BASE_URL = 'http://localhost:8080';

export default class Survey extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      step: 0,
      movies: [],
      selectedMovieIds: []
    };
  }

  componentDidMount(){
    fetch(API_BASE_URL + '/survey')
      .then(response => response.json())
      .then(movies => this.setState({movies}))
      .catch(error => console.log(error))
  }

  saveAnswer = (e, movieId) => {
    e.preventDefault();
    this.state.selectedMovieIds.push(movieId);
    const fetchOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({movieIds: this.state.selectedMovieIds})
    };
    if (this.state.selectedMovieIds.length < 10) {
      fetch(API_BASE_URL + '/survey', fetchOptions)
        .then(response => response.json())
        .then(movies => this.setState({movies}))
        .catch(error => console.log(error))
    } else {
      fetch(API_BASE_URL + '/survey/result', fetchOptions)
        .then(response => response.json())
        .then(movies => this.setState({movies}))
        .catch(error => console.log(error))
    }
  };

  render() {
    const movies = this.state.movies;

    return(
      <div className="container">
        <h1 className="text-center">{this.state.selectedMovieIds.length >= 10 ? "Rekomendacje" : ""}</h1>
        <ul style={{'listStyleType': 'none'}}>
          {movies.map(movie => <MovieItem movie={movie} key={movie.id} onSelect={this.saveAnswer}/>)}
        </ul>
      </div>
    );
  }
}
