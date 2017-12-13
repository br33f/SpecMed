import React from 'react';
import ReactDOM from 'react-dom';
import {App} from './App.jsx';
import {BrowserRouter} from 'react-router-dom';
import './include/bootstrap';
import './assets/styles/app.scss';
import "react-widgets/lib/scss/react-widgets.scss";

ReactDOM.render(
    <BrowserRouter>
        <App/>
    </BrowserRouter>
    , document.getElementById('root'));