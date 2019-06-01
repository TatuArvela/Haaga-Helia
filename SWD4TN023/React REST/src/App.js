import React, { Component } from 'react';
import Result from './Result';

class App extends Component {
	constructor() {
		super();
		this.handleChange = this.handleChange.bind(this);
		this.getLocation = this.getLocation.bind(this);
		this.fetchWeather = this.fetchWeather.bind(this);
		this.submitWeather = this.submitWeather.bind(this);
		this.componentWillMount = this.componentWillMount.bind(this);

		this.handleChange2 = this.handleChange2.bind(this);
		this.fetchLibraries = this.fetchLibraries.bind(this);
		this.state = {
			city: 'Espoo',
			city2: 'Espoo',
			weather: [],
			temp: 0,
			url: '',
			libraries: [],
		};
	}


/* Weather */

	handleChange(event) {
		this.setState({city: event.target.value});
	}

	getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(this.fetchWeather, this.fetchWeather);
		} else {
			this.fetchWeather();
		}
	}

	submitWeather(event) {
		this.fetchWeather();

		if (event != null) {
			event.preventDefault();
		}
	}

	fetchWeather = function(position) {
		var query = 'http://api.openweathermap.org/data/2.5/weather?q=' + this.state.city + '&APPID=46713cb5e31153739aee425dd2d14d5d&units=metric';

		if (position != null && position.latitude != null && position.longitude != null) {
			query = 'http://api.openweathermap.org/data/2.5/weather?lat=' + position.coords.latitude + '&lon=' + position.coords.longitude + '&APPID=46713cb5e31153739aee425dd2d14d5d&units=metric'
		}
		else {
			console.log("Geolocation error");
		}

		fetch(query)
		.then(result => result.json())
		.then(result => this.setState({
				city: result.name,
				weather: result.weather[0],
				temp: result.main.temp,
				url : 'http://openweathermap.org/img/w/' + result.weather[0].icon + '.png',
			})
		);
	}

	componentWillMount() {
		this.getLocation();
	}


/* Library */

	handleChange2(event) {
		this.setState(
			{city2: event.target.value}
		);
	}

	fetchLibraries = function(event) {
		this.setState({
			libraries: []
		})
		fetch('https://api.kirjastot.fi/v3/organisation?city.name=' + this.state.city2)
		.then(result => result.json())
		.then(result => {
			result.items.map(function(resultItem) {
				if (resultItem.type === "library"){
					let library = {
						name: resultItem.name.fi,
						address: resultItem.homepage.fi
					};
					
					let libraries = this.state.libraries.slice();

					this.setState({
						libraries: libraries.concat(library)
					});
				}
			}.bind(this))
		})

		if (event != null) {
			event.preventDefault();
		}
	}


/* Render */

	render() {
		return (
			<div>
			<div>
				<form onSubmit={this.submitWeather}><span>City: </span><input type="text" value={this.state.city} onChange={this.handleChange}/><input type="submit" value="Send"/></form>
				<p>Temperature: {this.state.temp} Celsius</p>
				<p>Weather:  {this.state.weather.main}</p>
				<img alt="weathericon" src={this.state.url}/>
			</div>
			<br/><hr/><br/>
			<div>
				<form onSubmit={this.fetchLibraries}><span>Kaupunki: </span><input type="text" value={this.state.city2} onChange={this.handleChange2}/><input type="submit" value="Hae kirjastot"/></form>
				<br/>
				<Result data={this.state.libraries}/>
			</div>
			</div>
		);
	}
}

export default App;
