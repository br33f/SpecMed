import React from 'react';
import { Link } from 'react-router-dom';
import {Popper} from 'react-popper';
import {
    Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink, UncontrolledDropdown,
    DropdownToggle, DropdownMenu, DropdownItem
} from 'reactstrap';
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
                                <NavLink tag={Link} to="/visit">Wizyta</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink tag={Link} to="/medicalPackage">Pakiet medyczny</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink tag={Link} to="/medicalOrder">Zlecenie badania</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink tag={Link} to="/unit/new">Placówka</NavLink>
                            </NavItem>
                            <UncontrolledDropdown>
                                <DropdownToggle nav caret>
                                    Pracownik
                                </DropdownToggle>
                                <DropdownMenu right={true}>
                                    <DropdownItem tag={Link} to="/employee/list">
                                        Lista pracowników
                                    </DropdownItem>
                                    <DropdownItem tag={Link} to="/employee/new">
                                        Dodaj pracownika
                                    </DropdownItem>
                                    <DropdownItem tag={Link} to="/employee/Medlist">
                                        Lista pracowników medycznych
                                    </DropdownItem>
                                    <DropdownItem tag={Link} to="/employee/Mednew">
                                        Dodaj pracownika medycznego
                                    </DropdownItem>
                                </DropdownMenu>

                                <NavItem>
                                    <NavLink tag={Link} to="/prescription/new">Recepta</NavLink>
                                </NavItem>
                            </UncontrolledDropdown>
                            <UncontrolledDropdown>
                                <DropdownToggle nav caret>
                                    Ubezpieczenie
                                </DropdownToggle>
                                <DropdownMenu right={true}>
                                    <DropdownItem tag={Link} to="/insurance/list">
                                        Lista ubezpieczeń
                                    </DropdownItem>
                                    <DropdownItem tag={Link} to="/insurance/new">
                                        Dodaj ubezpieczenie
                                    </DropdownItem>
                                </DropdownMenu>
                            </UncontrolledDropdown>
                            <UncontrolledDropdown>
                                <DropdownToggle nav caret>
                                    Wizyta
                                </DropdownToggle>
                                <DropdownMenu right={true}>
                                    <DropdownItem tag={Link} to="/visit/new">
                                        Dodaj wizytę
                                    </DropdownItem>
                                </DropdownMenu>
                            </UncontrolledDropdown>
                        </Nav>
                    </Collapse>
                </Navbar>
            </div>
        );
    }
}