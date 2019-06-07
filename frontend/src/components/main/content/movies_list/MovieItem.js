import React from 'react';
import sample_movie_img from '../../../../sample_movie_img.jpeg';
import {Button} from 'reactstrap';
import StarRatings from 'react-star-ratings';
import { Link } from 'react-router-dom'

export default class MoviesItem extends React.Component {
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
    const genres = this.props.movie.genre;
    const onSelect = this.props.onSelect;
    const showButton = this.props.showButton;

    return (
      <li className="col-12 p-3 container">
        <Link to={'/movies/'+id} style={{ textDecoration: 'none', color: '#000000' }}>
          <div className="card">
            <div className="row">
              <div className="col-md-4">
                <img className="img-fluid py-5 px-2" src={sample_movie_img} alt="Movie image"/>
              </div>
              <div className="text-left col-md-8">
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
                <p> <i className="fas fa-film fa-lg my-1"></i> <b>Genres: </b> {genres ?  genres : "-"} </p>
                <p> <i className="fas fa-video fa-lg my-1"></i> <b>Directors: </b> {directors} </p>
              </div>
            </div>
            {showButton && <Button color="primary" onClick={e => onSelect(e, id)}>Wybierz film</Button>}
          </div>
        </Link>
      </li>
    );
  }
}
