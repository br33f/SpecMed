import React from 'react';
import {Component} from 'react';
import {Input} from 'reactstrap';

export class BindedInput extends Component {
    constructor(props){
        super(props);

        this.Form = this.props.form;
    }

    render() {
        return (
          <div>
              <Input type={this.props.type}
                     id={this.props.id}
                     name={this.props.name}
                     placeholder={this.props.placeholder}
                     value={this.props.value || this.Form.state.model.get(this.props.name)}
                     className={this.Form.isValid(this.props.name) ? '' : 'is-invalid'}
                     onChange={this.Form.bindValueToModel}
              >
                  {this.props.children}
              </Input>
              {this.Form.getValidationFeedbackFor(this.props.name)}
          </div>
        );
    }
}