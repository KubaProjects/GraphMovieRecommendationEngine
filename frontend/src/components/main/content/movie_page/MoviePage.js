import React from 'react';
import sample_movie_img from '../../../../sample_movie_img.jpeg';
import StarRatings from 'react-star-ratings';

export default class MoviePage extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      movie:null,
      rating:null
    }
  }

  writeArray = (word, i) => {
    return i>0 ? ", "+word.name : word.name;
  }

  changeRating = ( newRating, name ) => {
    this.setState({
      rating: newRating*2
    });

    var data = {}
    data['rate']=newRating*2


    const url="http://localhost:8080/movies/rate/"+this.state.movie.id;


    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include',
        body: newRating*2
    })
    .then(response => response!==200 ? console.log(response) : "");

  }

  componentDidMount(){
    const url = 'http://localhost:8080/movies/'+this.props.match.params.id;

    fetch(url)
    .then((response) => {
      return response.json();
    })
    .then((movie) => {
      this.setState({movie:movie,
                      rating:movie.rating})
    });
  }

  render() {
    const movie = this.state.movie;
    const title = movie ? movie.title : null;
    const length = movie ? movie.length : null;
    const actors = movie ? movie.actors : null;
    const directors = movie ? movie.directors : null;
    const editors = movie ? movie.editors : null;
    const composers = movie ? movie.composers : null;
    const numVotes = movie ? movie.numVotes : null;
    const rating = this.state.rating;
    const writers = movie ? movie.writers : null;
    const cinematographers = movie ? movie.cinematographers : null;
    const year = movie ? movie.year : null;
    const genres = movie ? movie.genre : null;

    return (
      <div className="container-flow">
        <div className="text-center">
          <h3 className="mt-3">{title ? title : ""}</h3>
          <img className="img-fluid p-2" src={sample_movie_img} alt="Movie image"/>
          <div className="my-3">
            <StarRatings
              rating={rating ? rating/2 : 0}
              starRatedColor="#ed8a19"
              changeRating={this.changeRating}
              starDimension="25px"
              starSpacing="5px"
              numberOfStars={5}
            />
          </div>
        </div>
        <div className="col-8 offset-2">
          <div className="text-left px-2">
            <p> <i className="far fa-clock text-left fa-lg my-1"></i> <b>Length: </b> {length ? length+" min" : "-"} </p>
            <p> <i className="far fa-calendar-alt text-left fa-lg my-1"></i> <b>Year: </b> {year ? year : "-"} </p>
            <p> <i className="fas fa-film fa-lg my-1"></i> <b>Genres: </b> {genres ? genres : "-"} </p>
            <p> <i className="fas fa-video fa-lg my-1"></i> <b>Directors: </b> {directors ? directors : "-"} </p>
            <p> <i className="fas fa-user-friends fa-lg my-1"></i> <b>Actors: </b> {actors ? actors : "-"} </p>
            <p> <i className="fas fa-marker fa-lg my-1"></i> <b>Writers: </b> {writers ? writers : "-"} </p>
            <p> <i className="fab fa-product-hunt fa-lg my-1"></i> <b>Cinematographers: </b> {cinematographers ? cinematographers : "-"} </p>
            <p> <i className="fas fa-music fa-lg my-1"></i> <b>Composers: </b>{composers ? composers : "-"} </p>
          </div>
        </div>
      </div>
    );
  }
}
