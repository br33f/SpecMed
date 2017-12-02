import React from 'react';
import {Component} from 'react';
import {Navigation} from './components/Navigation.jsx';
import {Jumbotron} from 'reactstrap';
import {Switch, Route} from 'react-router-dom';

import {Main} from './modules/main/Main.jsx';
import {EmployeeList} from './modules/employee/List.jsx'

export class App extends Component {
    render() {
        return (
            <div>
                <Navigation/>
                <Jumbotron>
                    <Switch>
                        <Route exact path='/' component={Main}/>
                        <Route exact path='/employee/list' component={EmployeeList}/>
                    </Switch>
                </Jumbotron>
            </div>
        );
    }
}