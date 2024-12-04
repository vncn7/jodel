import React from "react";
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';

const styles = theme => ({



	kommentar: {
		marginLeft: "0%",
		width: "85%",
		padding: "2%",
		boxSizing: "borderBox",
		background: "#85BB5E",
		marginTop: "0%",
		fontFamily: "cursive",
		color: "black",
		fontSize: "40%",
	},

	kommentar2: {
		width: "94%",
		padding: "3%",
		boxSizing: "borderBox",
		background: "#B5E196",
		marginTop: "1%",
	},
	button:{
		align: "left",
		marginTop: '100px',
	}

});

class CommentForm extends React.Component {

	constructor(props) {
		super(props);
	    this.state = {
			currentUser: this.props.currentUser,
			comment: this.props.comment,
		};
	}


	upvote(){}
	downvote(){}

	render() {
		const { classes } = this.props;
		return (
		<div className={classes.kommentar}>
			{this.state.comment.text}
		</div>

		);
	}

}

export default withStyles(styles)(CommentForm);

