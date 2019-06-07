import React from 'react';
import './App.css';
import Main from './components/main/Main';
import Login from './components/login/Login'
import { Switch, Route } from 'react-router-dom'

export default class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      isAuthenticated:null
    };
  }

  componentDidMount(){
    fetch("http://localhost:8080/persons/authenticated",{
      credentials: 'include'
    }).then((response) => {
      return response.json();
    }).then((res) => {
      this.setIsAuthenticated(res);
    });
  }

  setIsAuthenticated = (auth) => {
    this.setState({
      isAuthenticated:auth
    });
  }


  render(){
    return (
      <div className="App">
        <div>
          <Switch>
            <Route exact path='/login' render={(props) => <Login login={this.login} setIsAuthenticated={this.setIsAuthenticated} {...props}/>}/>
            <Route path='/' render={(props) => <Main isAuthenticated={this.state.isAuthenticated} logout={this.logout} setIsAuthenticated={this.setIsAuthenticated} {...props}/>}/>
          </Switch>
        </div>
      </div>
    );
  }

}
