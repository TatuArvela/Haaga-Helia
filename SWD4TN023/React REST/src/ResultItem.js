import React, { Component } from 'react';

class ResultItem extends Component {
  render() {
    return (
      <tr>
        <td>{this.props.item.name}</td>
        <td><a href={this.props.item.address}>{this.props.item.address}</a></td>
      </tr>
    );
  }
}

export default ResultItem;
