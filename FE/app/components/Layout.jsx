import React from 'react';
import {Component} from 'react';
import {Navigation} from './Navigation.jsx';
import { Jumbotron, Button } from 'reactstrap';

export class Layout extends Component {
    render() {
        return (
            <div>
                <Navigation />
                <Jumbotron>
                    <h1>Hello world!</h1>
                    <Button color="success">Gratulacje!</Button>
                    <div class="row">
                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                            test1
                        </div>
                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                            test2
                        </div>
                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                            test3
                        </div>
                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                            test4
                        </div>
                    </div>
                </Jumbotron>
            </div>
        );
    }
}