import React, { Component } from 'react';
import Hei from './Hei';
import Nappula from './Nappula';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <Hei/>
        </div>
        <Nappula/>
      </div>
    );
  }
}

export default App;
