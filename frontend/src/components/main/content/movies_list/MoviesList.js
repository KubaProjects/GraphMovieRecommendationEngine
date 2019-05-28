import React from 'react';
import {Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, Button} from 'reactstrap';
import MovieItem from './MovieItem';
import Pager from 'react-pager';
import 'bootstrap/dist/css/bootstrap.min.css';

export default class MoviesList extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      total: 20,
      current: 0,
      visiblePage: 3
    };
  }

  handlePageChanged = (newPage) => {
    this.setState({ current : newPage });
  }


  render() {
    return (
      <div className="container">
        <ul className="text-center" style={{'listStyleType': 'none'}}>
          <MovieItem/>
          <MovieItem/>
          <MovieItem/>
          <MovieItem/>
        </ul>
        <div className="text-center">
          <Pager
              total={this.state.total}
              current={this.state.current}
              visiblePages={this.state.visiblePage}
              titles={{ first: '<|', last: '>|' }}
              className="page-link"
              onPageChanged={this.handlePageChanged}/>
        </div>
      </div>
    );
  }
}
