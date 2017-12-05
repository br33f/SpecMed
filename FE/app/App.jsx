import React from 'react';
import axios from 'axios';
import {Component} from 'react';
import {Navigation} from './components/navigation/Navigation.jsx';
import {Jumbo} from './components/jumbo/Jumbo.jsx';

import Utils from './components/helpers/Utils';
import DictionaryManager from './components/helpers/DictionaryManager';

import {Switch, Route} from 'react-router-dom';

import {Main} from './modules/main/Main.jsx';
import {EmployeeList} from './modules/employee/List.jsx'
import {EmployeeEdit} from './modules/employee/Edit.jsx'
import {VisitEdit} from './modules/visit/Edit.jsx'
import {MedicalPacakgeBuy} from './modules/medicalPackage/Buy.jsx'
import {InsuranceList} from './modules/insurance/List.jsx'
import {InsuranceEdit} from './modules/insurance/Edit.jsx'
import {PrescriptionNew} from './modules/prescription/New.jsx'
import {MedicalPacketNew} from './modules/medicalPacket/New.jsx'

export class App extends Component {
   constructor() {
       super();

       this.addGlobalFunctions();
       this.configureAxis();

       this.appTitle = "SpecMed";
   }

   addGlobalFunctions() {
       window.SM = {
           Utils: Utils,
           DictionaryManager: DictionaryManager
       };
   }

   configureAxis() {
       axios.defaults.baseURL = 'http://localhost:8080';
   }

    render() {
        return (
            <div>
                <Navigation appTitle={this.appTitle}/>
                <Jumbo appTitle={this.appTitle} />
                <Switch>
                    <Route exact path='/' component={Main}/>
                    <Route exact path='/employee/list' component={EmployeeList}/>
                    <Route exact path='/employee/new' component={EmployeeEdit}/>
                    <Route exact path='/employee/edit/:employeeId' component={EmployeeEdit}/>
                    <Route exact path='/visit/edit/:visitId' component={VisitEdit}/>
                    <Route exact path='/visit' component={VisitEdit}/>
                    <Route exact path='/medicalPackage' component={MedicalPacakgeBuy}/>
                    <Route exact path='/insurance/list' component={InsuranceList}/>
                    <Route exact path='/insurance/new' component={InsuranceEdit}/>
                    <Route exact path='/insurance/edit/:insuranceId' component={InsuranceEdit}/>
                    <Route exact path='/prescription/new' component={PrescriptionNew}/>
                    <Route exact path='/medicalPacket/new' component={MedicalPacketNew}/>
                </Switch>
            </div>
        );
    }
}