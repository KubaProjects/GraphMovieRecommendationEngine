import React from 'react';
import './App.css';
import Main from './components/main/Main';
import Login from './components/login/Login'
import { Switch, Route, Redirect } from 'react-router-dom'

export default class App extends React.Component {
  constructor(props) {
    super(props);

    fetch("http://localhost:8080/logout", {
      credentials: 'include',
      method: 'POST',
    }).catch(e => console.log(e));

    this.state = {
      isAuthenticated:false
    };
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
