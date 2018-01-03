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
                     hidden={this.props.hidden}
                     value={this.props.value || this.Form.state.model.get(this.props.name)}
                     className={this.Form.isValid(this.props.name) ? '' : 'is-invalid'}
                     onChange={this.props.onChange || this.Form.bindValueToModel}
                     multiple={this.props.multiple}
              >
                  {this.props.children}
              </Input>
              {this.Form.getValidationFeedbackFor(this.props.name)}
          </div>
        );
    }
}