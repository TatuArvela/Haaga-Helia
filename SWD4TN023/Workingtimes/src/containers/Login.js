import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

import AppBar from 'material-ui/AppBar';

import LoginForm from '../components/LoginForm';

import loginInput from '../actions/loginInput';
import login from '../actions/login';

import theme from '../styles/theme';

class Login extends Component {
  constructor(props) {
    super(props);

    this.login = login.login.bind(this);

    this.handleChange = loginInput.handleChange.bind(this);

    this.state = {
      username: null,
      password: null
    }

    this.title = "Työajan kirjaus | Sisäänkirjaus"
  }

  componentWillMount() {
    document.title = this.title;
  }

  render() {
    return (
      <MuiThemeProvider muiTheme={theme}>
        <div id="view">
          <AppBar
            title={this.title}
            showMenuIconButton={false}
            className="title"
          />
          <div className="container">
            <LoginForm
              handleChange={this.handleChange}
              login={this.login}
            />
          </div>
        </div>
      </MuiThemeProvider>
    );
  }
}

export default Login;