import React from 'react';
import {Component} from 'react';
import {Layout} from './components/Layout.jsx';
import {Layout2} from './components/Layout2.jsx';
import {Switch, Route} from 'react-router-dom';

export class App extends Component {
    render() {
        return (
          <Switch>
              <Route exact path='/' component={Layout}/>
              <Route exact path='/test' component={Layout}/>
          </Switch>
        );
    }
}