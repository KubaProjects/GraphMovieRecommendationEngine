import React from 'react';
import sample_movie_img from '../../../../sample_movie_img.jpeg';
import {Card, CardTitle, CardText} from 'reactstrap';
import StarRatings from 'react-star-ratings';
import { Link } from 'react-router-dom'

export default class MoviesItem extends React.Component {
  constructor(props) {
    super(props);
  }

  writeArray = (word, i) => {
    return i>0 ? ", "+word.name : word.name;
  }

  render() {
    const title = this.props.movie.title;
    const length = this.props.movie.length;
    const actors = this.props.movie.actors;
    const directors = this.props.movie.directors;
    const editors = this.props.movie.editors;
    const id = this.props.movie.id;
    const musicComposers = this.props.movie.musicComposers;
    const numVotes = this.props.movie.numVotes;
    const producers = this.props.movie.producers;
    const rating = this.props.movie.rating;
    const writers = this.props.movie.writers;
    const year = this.props.movie.year;
    const genres = this.props.movie.genres;

    return (
      <li className="col-12 p-3 container">
        <Link to={'/movies/'+id} style={{ textDecoration: 'none', color: '#000000' }}>
          <div className="card">
            <div className="row">
              <div className="col-4">
                <img className="img-fluid py-5 px-2" src={sample_movie_img} alt="Movie image"/>
              </div>
              <div className="text-left col-8">
                <div className="text-center3 my-3">
                  <h3 className="mt-">{title ? title : ""}</h3>
                </div>
                <div className="my-3">
                  <StarRatings
                    rating={rating ? rating/2 : 0}
                    starDimension="25px"
                    starSpacing="5px"
                    numberOfStars={5}
                  />
                </div>
                <p> <i className="far fa-clock text-left fa-lg my-1"></i> <b>Length: </b> {length ? length+" min" : "-"} </p>
                <p> <i className="fas fa-film fa-lg my-1"></i> <b>Genres: </b> {genres ? genres.map(this.writeArray) : "-"} </p>
                <p> <i className="fas fa-video fa-lg my-1"></i> <b>Directors: </b> {directors ? directors.map(this.writeArray) : "-"} </p>
              </div>
            </div>
          </div>
        </Link>
      </li>
    );
  }
}

/*

<li className="row col-md-5 col-sm-8 p-2 m-2 d-inline-block">
  <div className="card">
    <div className="text-center">
      <Card inverse style={{ backgroundColor: '#333', borderColor: '#333' }}>
        <CardTitle><h3 className="mt-3">{title ? (title.length>20 ? title.substring(0,20)+"..." : title) : ""}</h3></CardTitle>
      </Card>
      <img className="img-fluid p-2" src={sample_movie_img} alt="Movie image"/>
      <div className="my-3">
        <StarRatings
          rating={rating ? rating/2 : 0}
          starDimension="25px"
          starSpacing="5px"
          numberOfStars={5}
        />
      </div>
    </div>
    <div className="text-left px-2">
      <p> <i className="far fa-clock text-left fa-lg my-1"></i> <b>Length: </b> {length ? length+" min" : "-"} </p>
      <p> <i className="far fa-calendar-alt text-left fa-lg my-1"></i> <b>Year: </b> {year ? year : "-"} </p>
      <p> <i className="fas fa-film fa-lg my-1"></i> <b>Genres: </b> {genres ? genres.map(this.writeArray) : "-"} </p>
      <p> <i className="fas fa-video fa-lg my-1"></i> <b>Directors: </b> {directors ? directors.map(this.writeArray) : "-"} </p>
      <p> <i className="fas fa-user-friends fa-lg my-1"></i> <b>Actors: </b> {actors ? actors.map(this.writeArray) : "-"} </p>
      <p> <i className="fas fa-marker fa-lg my-1"></i> <b>Writers: </b> {writers ? writers.map(this.writeArray) : "-"} </p>
      <p> <i className="fas fa-edit fa-lg my-1"></i> <b>Editors: </b> {editors ? editors.map(this.writeArray) : "-"} </p>
      <p> <i className="fab fa-product-hunt fa-lg my-1"></i> <b>Producesr: </b> {producers ? producers.map(this.writeArray) : "-"} </p>
      <p> <i className="fas fa-music fa-lg my-1"></i> <b>Music composers: </b>{musicComposers ? musicComposers.map(this.writeArray) : "-"} </p>
    </div>
  </div>
</li>


*/
