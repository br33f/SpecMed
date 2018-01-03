import React from 'react';
import axios from 'axios';
import {Component} from 'react';
import {Navigation} from './components/navigation/Navigation.jsx';
import {Jumbo} from './components/jumbo/Jumbo.jsx';
import {Footer} from './components/footer/Footer.jsx';

import Utils from './components/helpers/Utils';
import DictionaryManager from './components/helpers/DictionaryManager';
import Auth from './components/helpers/Auth';

import Moment from 'moment'
import momentLocalizer from 'react-widgets-moment';

import {Routes} from './Routes.jsx';

export class App extends Component {
   constructor() {
       super();

       Moment.locale('pl');
       momentLocalizer();

       this.addGlobalFunctions();
       this.configureAxis();

       this.appTitle = "SpecMed";
   }

   addGlobalFunctions() {
       window.SM = {
           Utils: Utils,
           DictionaryManager: DictionaryManager,
           Auth: Auth
       };
   }

   configureAxis() {
       // REMOTE
       // axios.defaults.baseURL = 'https://specmed-be.herokuapp.com';

       // LOCAL
       axios.defaults.baseURL = 'http://localhost:8080';

       Utils.setAuthorizationHeader();
   }

    render() {
        return (
            <div>
                <Navigation appTitle={this.appTitle}/>
                <Jumbo appTitle={this.appTitle} />
                <Routes />
                <Footer />
            </div>
        );
    }
}