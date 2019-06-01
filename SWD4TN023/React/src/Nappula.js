import React, {Component} from 'react';

class Nappula extends Component {

	constructor(props) {
		super(props);
		this.state = {
			number: 0
		};

		this.handleClick = this.handleClick.bind(this);
	}

	handleClick(event) {
		this.setState({number: this.state.number + 1});
	}

	render() {
		return (
			<div>
				<h2>
					Painiketta klikattu {this.state.number} kertaa
				</h2>
				<input type="button" value="Click me" onClick={this.handleClick}/>
			</div>
		);
	}
}

export default Nappula;