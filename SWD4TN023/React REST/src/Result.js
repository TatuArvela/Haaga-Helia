import React, { Component } from 'react';
import ResultItem from './ResultItem';

class Result extends Component {
  render() {
  var rows = this.props.data.map(item =>
    <ResultItem item={item}/>
  );
    return (
      <table>
        <thead>
          <tr>
            <th>Nimi</th>
            <th>www-osoite</th>
          </tr>
        </thead>
        <tbody>{rows}</tbody>
      </table>
    );
  }
}

export default Result;
