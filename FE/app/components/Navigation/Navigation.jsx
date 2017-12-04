import React from 'react';
import { Link } from 'react-router-dom';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink } from 'reactstrap';
import './navigation.scss';

export class Navigation extends React.Component {
    constructor(props) {
        super(props);

        this.toggle = this.toggle.bind(this);
        this.state = {
            isOpen: false
        };
    }
    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }
    render() {
        return (
            <div>
                <Navbar color="faded" light className="navbar-expand-md">
                    <img className="navbarLogo" src={require('../../assets/images/logo.png')} alt={this.props.appTitle} />
                    <NavbarBrand tag={Link} to="/">
                        <span>{this.props.appTitle}</span>
                    </NavbarBrand>
                    <NavbarToggler onClick={this.toggle} />
                    <Collapse isOpen={this.state.isOpen} navbar>
                        <Nav className="ml-auto" navbar>
                            <NavItem>
                                <NavLink tag={Link} to="/">Strona główna</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink tag={Link} to="/employee/list">Lista lekarzy</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink tag={Link} to="/employee/add">+Pracownik</NavLink>
                            </NavItem>
                        </Nav>
                    </Collapse>
                </Navbar>
            </div>
        );
    }
}