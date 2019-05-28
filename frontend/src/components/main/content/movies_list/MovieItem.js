import React from 'react';
import sample_movie_img from './sample_movie_img.jpeg'
import {Card, CardTitle, CardText} from 'reactstrap';
import StarRatings from 'react-star-ratings';
import 'font-awesome/css/font-awesome.min.css';
import './MovieItem.css'

export default class MoviesItem extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <li className="row col-md-5 col-sm-8 p-2 m-2 d-inline-block">
        <div className="card">
          <div className="text-center">
            <Card inverse style={{ backgroundColor: '#333', borderColor: '#333' }}>
              <CardTitle><h2 className="mt-3">Pulp Fiction</h2></CardTitle>
            </Card>
            <img className="img-fluid p-2" src={sample_movie_img} alt="Movie image"/>
            <div className="my-3">
              <StarRatings
                rating={2.403}
                starDimension="25px"
                starSpacing="5px"
                numberOfStars={5}
              />
            </div>
          </div>
          <div className="text-left px-2">
            <p> <i className="far fa-clock text-left fa-lg my-1"></i> <b>Length: </b> 2:20 </p>
            <p> <i className="far fa-calendar-alt text-left fa-lg my-1"></i> <b>Year: </b> 2015 </p>
            <p> <i className="fas fa-film fa-lg my-1"></i> <b>Genres: </b> Crime, Horror </p>
            <p> <i className="fas fa-video fa-lg my-1"></i> <b>Directors: </b> Quentin Tarantino </p>
            <p> <i className="fas fa-user-friends fa-lg my-1"></i> <b>Actors: </b> Idont Know </p>
            <p> <i className="fas fa-marker fa-lg my-1"></i> <b>Writers: </b> Writer Best </p>
            <p> <i className="fas fa-edit fa-lg my-1"></i> <b>Editors: </b> Editor Best </p>
            <p> <i className="fab fa-product-hunt fa-lg my-1"></i> <b>Producer: </b> Producer Best </p>
            <p> <i className="fas fa-music fa-lg my-1"></i> <b>Music composer: </b> Musican Best </p>
          </div>
        </div>
      </li>
    );
  }
}
