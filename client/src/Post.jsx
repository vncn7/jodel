import React from "react";
import { withStyles } from '@material-ui/core/styles';

import Comments from './Comments';
import PostForm from './PostForm';
import FormControl from "@material-ui/core/FormControl";

const styles = theme => ({
	center: {
		width: "96%",
		padding: "2%",
		boxSizing: "borderBox",
		background: "#85BB5E",
		border: "double",
		marginTop: "2%",
		fontSize: "150%",
	},

	postForm: {
		marginLeft: "0%",
		width: "95%",
		padding: "2%",
		boxSizing: "borderBox",
		background: "#B5E196",
		marginTop: "0%",
		fontFamily: "cursive",
		color: "black",
		fontSize: "35%",
	},

	textForm: {
		marginLeft: "0%",
		width: "95%",
		padding: "2%",
		boxSizing: "borderBox",
		background: "#B5E196",
		//border: "double",
		marginTop: "2%",
		fontFamily: "cursive",
		color: "black",
	},
});

class Post extends React.Component {

	constructor(props) {
		super(props);
	    this.state = {
			currentUser: this.props.currentUser,
            post: this.props.post,
			author: [],
			comments: [],
		};
	}

	componentDidMount()
	{
		fetch( "http://localhost:8080/Users/getUser?userId=" + this.props.author)
			.then( function(response) { return response.json() } )
			.then( data => this.setState({author: data }));
		this.setState({loading: false});
	}


render() {
		const { classes } = this.props;
	console.log( "Benutzer ist in Post:" + this.state.currentUser.username)
		return (
		<div className={classes.center}>

            <font >

				<div className={classes.textForm}>
                					{this.state.post.text}
                </div>



				<div className={classes.postForm}>
					Post von {this.state.author.username} am {this.state.post.postedat.slice(0,10).replace("-",".").replace("-",".") } um {this.state.post.postedat.slice(11,16)}
				</div>



			</font>
            <PostForm post = {this.state.post} currentUser={this.state.currentUser}></PostForm>
			<Comments currentUser={this.state.currentUser} comments={this.state.comments} post={this.state.post}></Comments>

	    </div>
		);
	}
}

export default withStyles(styles)(Post);

