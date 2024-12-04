import React from "react";
import { withStyles } from '@material-ui/core/styles';

import Login from './Login';
import Posts from './Posts'

const styles = theme => ({
	center: {
		display: 'flex',
		justifyContent: 'center',
		alignItems: 'center',
		minHeight: '100vh',

	},
});

const theUrl ="http://localhost:8085/";

class App extends React.Component {

	constructor(props) {
		super(props);
	    this.state = {
			currentUser: [],
			loggedIn: false,
		};
	}

	authorized = (User) => {
		this.setState({currentUser: User})
		this.setState({loggedIn: true});
	}

	render() {
		if (this.state.loggedIn && this.state.currentUser !== undefined) {
			return (
				console.log(this.state.loggedIn),
				console.log( "Benutzer wurde übergeben:" + this.state.currentUser.username),
			<Posts currentUser={this.state.currentUser} url={theUrl} ></Posts>
			);
		} else {
			return (
			<Login url={theUrl} authorized = {this.authorized}></Login> 
			);
		}
	}
}

export default withStyles(styles)(App);

