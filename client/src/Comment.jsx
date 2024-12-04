import React from "react";
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import CommentForm from './CommentForm'

const styles = theme => ({
	center: {
		display: 'flex',
		justifyContent: 'center',
		alignItems: 'center',
		minHeight: '10px',
	},

	button:{
		justifyContent: 'right',
		alignItems: 'right',
		minHeight: '10px',
		marginRight: '0px',
		style: "right",
		marginLeft: "10%",
		},

	authorForm: {
		marginLeft: "0%",
		width: "85%",
		padding: "2%",
		boxSizing: "borderBox",
		background: "#85BB5E",
		fontFamily: "cursive",
		fontSize: "10px",
		color: "black",
		fontSize: "35%",
	},

	author: {
		width: "96%",
		padding: "2%",
		boxSizing: "borderBox",
		background: "#85BB5E",
		marginTop: "2%",
	},
});

class Comment extends React.Component {

	constructor(props) {
		super(props);
	    this.state = {
			user: this.props.currentUser,
			comment: this.props.comment,
			like: [],
			dislike:[],
			ausgabe:0,
			author:[],
		};
	}


	upvote = ( event ) => {
		fetch( "http://localhost:8080/voteComment/upvote?commentId=" + this.state.comment.id +
			"&authorId=" + this.state.user.id, {
			headers: {
				'Content-Type': 'application/json',
				'Accept': 'application/json',
			},
			method: 'post',
		})
			.then( this.status )
			.then( function(response) { return response.json() } )
			.then( data => this.setState({ausgabe: data }))
			.catch( function( error ) {
				console.log( 'Request failed', error );
			});
	}



	downvote = ( event ) => {
		fetch( "http://localhost:8080/voteComment/downvote?commentId=" + this.state.comment.id +
			"&authorId=" + this.state.user.id, {
			headers: {
				'Content-Type': 'application/json',
				'Accept': 'application/json',
			},
			method: 'post',
		})
			.then( this.status )
			.then( function(response) { return response.json() } )
			.then( data => this.setState({ausgabe: data }))
			.catch( function( error ) {
				console.log( 'Request failed', error );
			});
	}


	componentDidMount()
	{
		fetch( "http://localhost:8080/Users/getUser?userId=" + this.state.comment.authorId)
			.then( function(response) { return response.json() } )
			.then( data => this.setState({author: data }))
		this.setState({loading: false})

		fetch( "http://localhost:8080/voteComment/getLikes?commentId=" + this.state.comment.id)
			.then( function(response) { return response.json() } )
			.then( data => this.setState({like: data }))

		fetch( "http://localhost:8080/voteComment/getDislikes?commentId=" + this.state.comment.id)
			.then( function(response) { return response.json() } )
			.then( data => this.setState({dislike: data }))
	}




	render() {
		const { classes } = this.props;
		return (
		<div className={classes.autor}>
			<div>



				<CommentForm currentUser={this.state.user} comment = {this.state.comment}></CommentForm>
				<div className={classes.authorForm}>
                	 {this.state.author.username} am {this.state.comment.postedat.slice(0,10).replace("-",".").replace("-",".") } um {this.state.comment.postedat.slice(11,16)}
                </div>



			</div>


			<div className={classes.button}>
				<Button variant="contained" color="primary" onClick={() => this.upvote()}>

					{this.state.like.length} likes
				</Button>
				<Button

				variant="contained" color="secondary" onClick={() => this.downvote()}>

					{this.state.dislike.length} dislikes
				</Button>
		</div>

			<div>

		</div>
	    </div>
		);
	}
}

export default withStyles(styles)(Comment);

