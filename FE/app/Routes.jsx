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
import {UnitList} from "./modules/unit/List.jsx";


// Opinion
import {OpinionAdd} from "./modules/opinion/Add.jsx";

// Customer
import {CustomerEdit} from "./modules/customer/Edit.jsx";
import {CustomerList} from "./modules/customer/List.jsx";
import {CustomerSearch} from "./modules/customer/Search.jsx";

// Visit
import {VisitAdd} from "./modules/visit/Add.jsx";
import {VisitAppoint} from "./modules/visit/Appoint.jsx";

// Communication
import {CommunicationWithDoctor} from "./modules/communication-with-doctor/Add.jsx";
import {Conversations} from "./modules/communication-with-doctor/Conversations.jsx";
import {Conversation} from "./modules/communication-with-doctor/Conversation.jsx";

// User
import {Register} from "./modules/user/Register.jsx";
import {Login} from "./modules/user/Login.jsx";
import {Logout} from "./modules/user/Logout.jsx";
import {BindAccount} from "./modules/user/BindAccount.jsx";

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
                {this.messageRoutes()}
                {this.userRoutes()}
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
                <Route exact path='/unit/list' component={UnitList}/>
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
                <Route exact path='/customer/edit/:customerId' component={CustomerEdit}/>
                <Route exact path='/customer/list' component={CustomerList}/>
                <Route exact path='/customer/search' component={CustomerSearch}/>
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

    messageRoutes() {
        return (
            <Switch>
                <Route exact path='/contact/send' component={CommunicationWithDoctor}/>
                <Route exact path='/contact/list' component={Conversations}/>
                <Route exact path='/contact/conversation/:doctorId' component={Conversation}/>
            </Switch>
        )
    }

    userRoutes() {
        return (
            <Switch>
                <Route exact path='/register' component={Register}/>
                <Route exact path='/login' component={Login}/>
                <Route exact path='/logout' component={Logout}/>
                <Route exaxt path='/bind-account/:customerId' component={BindAccount}/>
            </Switch>
        );
    }
}