import React, { Component } from 'react';

import Paper from 'material-ui/Paper';
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';

class LoginForm extends Component {
  render() {
    return (
      <Paper
        className="login"
      >
        <form onSubmit={this.props.login}>
          <TextField
            hintText="Käyttäjätunnus"
            name="username"
            onChange={this.props.handleChange}
          /><br />
          <br />
          <TextField
            hintText="Salasana"
            type="password"
            name="password"
            onChange={this.props.handleChange}
          /><br />
          <br />
          <RaisedButton
            className="button"
            label="Kirjaudu sisään"
            primary
            type="submit"
          /><br />
        </form>
        <RaisedButton
          className="button"
          label="Salasana unohtui"
        />
        <RaisedButton
          className="button"
          label="Rekisteröidy"
        />
      </Paper>
    )
  }
}

export default LoginForm;