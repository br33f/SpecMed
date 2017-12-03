import React from 'react';
import {Component} from 'react';
import {Navigation} from './components/Navigation.jsx';
import {Jumbo} from './components/Jumbo.jsx';

import {Switch, Route} from 'react-router-dom';

import {Main} from './modules/main/Main.jsx';
import {EmployeeList} from './modules/employee/List.jsx'

export class App extends Component {
   constructor() {
       super();

       this.appTitle = "SpecMed";
   }

    render() {
        return (
            <div>
                <Navigation appTitle={this.appTitle}/>
                <Jumbo appTitle={this.appTitle} />
                <Switch>
                    <Route exact path='/' component={Main}/>
                    <Route exact path='/employee/list' component={EmployeeList}/>
                </Switch>
            </div>
        );
    }
}