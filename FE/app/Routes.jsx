import React from 'react';
import {Component} from 'react';
import {Switch, Route} from 'react-router-dom';

/* Components */

// Main page
import {Main} from './modules/main/Main.jsx';

// Employee
import {EmployeeList} from './modules/employee/List.jsx'
import {EmployeeEdit} from './modules/employee/Edit.jsx'

// Insurance
import {InsuranceList} from './modules/insurance/List.jsx'
import {InsuranceEdit} from './modules/insurance/Edit.jsx'

// Medical employee
import {MedicalEmployeeList} from "./modules/medical-employee/MedList.jsx";
import {MedicalEmployeeEdit} from "./modules/medical-employee/MedEdit.jsx";

// Unit
import {UnitEdit} from "./modules/unit/Edit.jsx";

// Opinion
import {OpinionAdd} from "./modules/opinion/Add.jsx";

// Customer
import {CustomerEdit} from "./modules/customer/Edit.jsx";

// Visit
import {VisitAdd} from "./modules/visit/Add.jsx";
import {VisitAppoint} from "./modules/visit/Appoint.jsx";

export class Routes extends Component {
    render() {
        return (
            <div>
                {this.commonRoutes()}
                {this.employeeRoutes()}
                {this.insuranceRoutes()}
                {this.medicalEmployeeRoutes()}
                {this.unitRoutes()}
                {this.opinionRoutes()}
                {this.customerRoutes()}
                {this.visitRoutes()}
            </div>
        );
    }

    commonRoutes() {
        return (
            <Switch>
                <Route exact path='/' component={Main}/>
            </Switch>
        );
    }

    employeeRoutes() {
        return (
            <Switch>
                <Route exact path='/employee/list' component={EmployeeList}/>
                <Route exact path='/employee/new' component={EmployeeEdit}/>
                <Route exact path='/employee/edit/:employeeId' component={EmployeeEdit}/>
                <Route exact path='/employee/edit' component={EmployeeEdit}/>
            </Switch>
        );
    }

    insuranceRoutes() {
        return (
            <Switch>
                <Route exact path='/insurance/list' component={InsuranceList}/>
                <Route exact path='/insurance/new' component={InsuranceEdit}/>
                <Route exact path='/insurance/edit/:insuranceId' component={InsuranceEdit}/>
            </Switch>
        );
    }

    medicalEmployeeRoutes() {
        return (
            <Switch>
                <Route exact path='/medical-employee/list' component={MedicalEmployeeList}/>
                <Route exact path='/medical-employee/new' component={MedicalEmployeeEdit}/>
                <Route exact path='/medical-employee/edit/:employeeId' component={MedicalEmployeeEdit}/>
            </Switch>
        );
    }

    unitRoutes() {
        return (
            <Switch>
                <Route exact path='/unit/new' component={UnitEdit}/>
                <Route exact path='/unit/edit/:unitId' component={UnitEdit}/>
            </Switch>
        );
    }

    opinionRoutes() {
        return (
            <Switch>
                <Route exact path='/visit/rate' component={OpinionAdd}/>
            </Switch>
        );
    }

    customerRoutes() {
        return (
            <Switch>
                <Route exact path='/customer/new' component={CustomerEdit}/>
            </Switch>
        );
    }

    visitRoutes() {
        return (
            <Switch>
                <Route exact path='/visit/add' component={VisitAdd}/>
                <Route exact path='/visit/appoint' component={VisitAppoint}/>
            </Switch>
        );
    }
}