import React from 'react';
import {Link} from 'react-router-dom';
import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink,
    UncontrolledDropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem
} from 'reactstrap';
import './navigation.scss';

import menuTree from './menu.json';

/**
 * Klasa odpowiedzialana za generowanei oraz edycje menu aplikacji
 */
export class Navigation extends React.Component {
    constructor(props) {
        super(props);

        // hotfix warning xD
        this.menuIdx = 0;

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

    generateMenu() {
        return (
            <Nav className="ml-auto" navbar>
                {
                    menuTree.map(menuItem =>
                        this.generateMenuItem(menuItem)
                    )
                }
            </Nav>
        );
    }

    generateMenuItem(menuItem) {
        if (!SM.Auth.isPermitted(menuItem.permitWith)) return;
        if (menuItem.children) {
            let childrenMenuItems = menuItem.children.filter(item => SM.Auth.isPermitted(item.permitWith));
            return (
                <UncontrolledDropdown key={this.menuIdx++}>
                    <DropdownToggle nav caret>
                        {menuItem.label}
                    </DropdownToggle>
                    <DropdownMenu right={true}>
                        {childrenMenuItems.map(childItem =>
                            <DropdownItem tag={Link} to={childItem.uri} key={this.menuIdx++}>
                                {childItem.label}
                            </DropdownItem>
                        )}
                    </DropdownMenu>
                </UncontrolledDropdown>
            );
        } else {
            if (menuItem.uri) {
                return (
                    <NavItem key={this.menuIdx++}>
                        <NavLink tag={Link} to={menuItem.uri}>{menuItem.label}</NavLink>
                    </NavItem>
                );
            } else {
                return (
                    <NavItem key={this.menuIdx++}>{menuItem.label}</NavItem>
                );
            }

        }
    }

    render() {
        return (
            <div>
                <Navbar color="faded" light className="navbar-expand-md">
                    <img className="navbarLogo" src={require('../../assets/images/logo.png')}
                         alt={this.props.appTitle}/>
                    <NavbarBrand tag={Link} to="/">
                        <span>{this.props.appTitle}</span>
                    </NavbarBrand>
                    <NavbarToggler onClick={this.toggle}/>
                    <Collapse isOpen={this.state.isOpen} navbar>
                        {this.generateMenu()}
                    </Collapse>
                </Navbar>
            </div>
        );
    }
}