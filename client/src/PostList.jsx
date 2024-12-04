import React from "react";
import { withStyles } from '@material-ui/core/styles';

import Post from './Post'

const styles = theme => ({
	center: {
		marginLeft: "5%",
		width: "90%",
		padding: "0%",
		boxSizing: "borderBox",
		background: "#FFFFFF",

}
});

class PostList extends React.Component {
	help;

	constructor(props) {
		super(props);
	    this.state = {
			currentUser: this.props.currentUser,
            posts: [],
            ownPosts: false,
            noPosition: false,
            loadingPosts: true,
			hilfe: this.props.hilfe,
			lon:  20,
			lat: 20,
		};
	}
	/*
  componentDidMount()
  	{
  		fetch( "http://localhost:8080/Post/getAllPosts")
  			.then( function(response) { return response.json() } )
  			.then( data => this.setState({posts: data }));
  		this.setState({loadingPosts: false});
  	}
*/
	componentDidMount()
	{
		fetch( "http://localhost:8080/Post/getPosts?lat=" + this.state.lat +
			"&lon=" + this.state.lon)
			.then( function(response) { return response.json() } )
			.then( data => this.setState({posts: data }));
		this.setState({loadingPosts: false});
	}

	componentDidUpdate()
	{
		console.log("Hilfe ist:" + this.props.hilfe);
		if(this.props.hilfe){

			fetch( "http://localhost:8080/Post/getPosts?lat=" + this.state.lat +
				"&lon=" + this.state.lon)
				.then( function(response) { return response.json() } )
				.then( data => this.setState({posts: data }));
			this.setState({loadingPosts: false});
	}
	}

	render() {
		const { classes } = this.props;
		console.log( "Benutzer ist in PostList:" + this.state.currentUser.username)
		return (
		<div className={classes.center}>

			{this.state.posts.map((posts, index) => (
			<Post currentUser={this.state.currentUser} post={posts} author={posts.authorId} key={index} currentUser={this.props.currentUser}></Post>
			))}
	    </div>
		);
	}
}

export default withStyles(styles)(PostList);

