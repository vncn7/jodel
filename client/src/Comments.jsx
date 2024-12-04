import React from "react";
import { withStyles } from '@material-ui/core/styles';
import Post from "./Post";
import Comment from './Comment'
import PostList from "./PostList";


const styles = theme => ({
	center: {
		width: "96%",
		padding: "2%",
		boxSizing: "borderBox",
		bordertop: "double",
		background: "#B5E196",
		marginTop: "2%",
		fontFamily: "cursive",
	},

	kommentare: {
		fontWeight: "500",
		fontSize: "120%",
		align: "center",
		fontSize: "65%",
	},
});

class Comments extends React.Component {

	constructor(props) {
		super(props);
	    this.state = {
			currentUser: this.props.currentUser,
			comments: this.props.comments,
			post: this.props.post,
            ownPosts: false,
            noPosition: false,
            loadingPosts: true
		};
	}

componentDidMount()
    	{

    		fetch( "http://localhost:8080/Comments/getComments?postId=" + this.state.post.id)
    			.then( function(response) { return response.json() } )
    			.then( data => this.setState({comments: data }));
    		console.log("übergeben")
    		console.log(this.state.post.id)
    		console.log(this.state.comments.authorId)
  		    {this.state.comments.map((text, index) => (console.log(text.text)))}
    	}


	render() {
		const { classes } = this.props;
		return (

		<div className={classes.center}>
			<div className={classes.kommentare}>
				Kommentare:
			</div>
			{this.state.comments.map((comment, index) => (
			<Comment currentUser={this.state.currentUser} comment={comment}></Comment>

			))}

		</div>
		);
	}
}

export default withStyles(styles)(Comments);

