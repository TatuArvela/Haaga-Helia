import React, { Component } from 'react';
import injectTapEventPlugin from 'react-tap-event-plugin';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

import dataMapper from '../utils/dataMapper'

import Admin from '../containers/Admin';
import Login from '../containers/Login';

// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();

class App extends Component {
  constructor(props) {
    super(props);

    // Authentication setter
    this.setAuth = dataMapper.setAuth.bind(this);

    // Initialization
    this.state = {
      token: false
    }
  }

  render() {
    return (
      <div id="app">
        {!this.state.token &&
          <Login
            setAuth = {this.setAuth}
          />
        }

        {(this.state.token && this.state.admin) &&
          <Admin
            token = {this.state.token}
          />
        }
      </div>
    );
  }
}

export default App;
