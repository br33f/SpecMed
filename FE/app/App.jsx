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
import {MedicalEmployeeList} from "./modules/employee/MedList.jsx";
import {MedicalEmployeeEdit} from "./modules/employee/MedEdit.jsx";
import {PrescriptionEdit} from "./modules/prescription/Edit.jsx";

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
                    <Route exact path='/employee/Medlist' component={MedicalEmployeeList}/>
                    <Route exact path='/employee/Mednew' component={MedicalEmployeeEdit}/>
                    <Route exact path='/employee/Mededit/:employeeId' component={MedicalEmployeeEdit}/>
                    <Route exact path='/prescription/new' component={PrescriptionEdit}/>
                    <Route exact path='/prescription/edit/:prescriptionId' component={PrescriptionEdit}/>
                </Switch>
            </div>
        );
    }
}