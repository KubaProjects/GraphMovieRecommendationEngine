import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'font-awesome/css/font-awesome.min.css';
import 'rc-pagination/assets/index.css';
import { BrowserRouter } from 'react-router-dom';

ReactDOM.render((<BrowserRouter><App/></BrowserRouter>), document.getElementById('root'));

serviceWorker.unregister();
