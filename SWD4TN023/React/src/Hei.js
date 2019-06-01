import React, {Component} from 'react';

class Hei extends Component {

	constructor(props) {
		super(props);
		this.state = {
			name: ''
		};

		this.handleChange = this.handleChange.bind(this);
	}

	componentDidMount() {
		this.setState({
			name: "Tatu"
		})
	}

	handleChange(event) {
		this.setState({name: event.target.value});
	}

	render() {
		return (
			<div>
				<h2>
				Hei {this.state.name}
				</h2>
				<input type="text" value={this.state.name} onChange={this.handleChange}/>
			</div>
		);
	}
}

export default Hei;