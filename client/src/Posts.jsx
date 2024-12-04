import React from "react";
import { withStyles } from '@material-ui/core/styles';

import clsx from "clsx";
import InputLabel from "@material-ui/core/InputLabel";
import Input from "@material-ui/core/Input";
import FormControl from "@material-ui/core/FormControl";
import Button from "@material-ui/core/Button";


import PostList from './PostList'

const styles = theme => ({
	center: {

		padding: "2%",
		boxSizing: "borderBox",
		background: "#FFFFFF",
		fontfamily: "cursive",
		marginTop: "2%",
	},
	titel: {

		padding: "0%",
		marginLeft: "38%",
		fontfamily: "cursive",
		fontSize: "300%",
		marginTop: "0%",
	},
    	post: {
			marginLeft: "5%",
			width: "86%",
			padding: "2%",
			boxSizing: "borderBox",
			background: "#FFFFFF",
			marginTop: "2%",
		},
    	textField: {
    		width: "75%",
    		padding: '1px',
    	},

    	buttonRightAlign: {
    		padding: '80px',
    		textAlign: 'right',
    	},
});

class Posts extends React.Component {

	constructor(props) {
		super(props);
	    this.state = {
			currentUser: this.props.currentUser,
            data: [],
            ownPosts: false,
            noPosition: false,
            loadingPosts: true,
            post: "",
            newPost: [],
			hilfe:false,
		};
	}

	handleChange = (event) => {
        	const post = this.state.post;
        	  if (event.target.id==="post") {
        		this.setState({post: event.target.value});
        		if (post !== null && post.length < 3) {
        			this.setState({postButtonDisabled: true });
        		} else {
        			this.setState({postButtonDisabled: false });
        		}
        	  }
        	};

	createPost = ( event ) => {
        console.log("Create New Post")
		console.log("übergebe hilfe")

		this.setState({hilfe:true})
		console.log("Hilfe wurde gesetzt auf den wert: " + this.state.hilfe)



		console.log("Verzögerung                          start")
		setTimeout(
			this.setState({hilfe: false},
			console.log("Verzögerung                      ende"),
			), 1000)
		console.log("Hilfe wurde gesetzt auf den wert: " + this.state.hilfe)



		console.log("Verzögerung                          start2")
		setTimeout(console.log("Verzögerung                      ende2"), 1000)




              fetch( "http://localhost:8080/Post/createPost?text="+ this.state.post+
              "&lon=20&lat=20&authorId="+ this.state.currentUser.id, {
                  headers: {
                      'Content-Type': 'application/json',
                      'Accept': 'application/json',
                  },
                  method: 'post',
             //     body: formdata
              })
                  .then( this.status )
                  .then( function(response) { return response.json() } )
                  .then( data => this.setState({newPost: data }) )


	.catch( function( error ) {
                      console.log( 'Request failed', error );

                  });

	}




	render() {
		const { classes } = this.props;
		return (
			console.log( "Benutzer ist in Post:" + this.state.currentUser.username),
		<div className={classes.center}>
			<div className={classes.titel}>
			Jodel - Esslingen
			</div>
			<div className={classes.post}>

            			<FormControl className={clsx( classes.textField)}>
            				<InputLabel htmlFor="post">Post something...</InputLabel>
            				<Input
            					id="post"
            					type='text'
            					value={this.state.post}
            					onChange={this.handleChange}
            				/>
            			</FormControl>

            			<Button variant="contained" color="primary" onClick={() => this.createPost()}
							disabled={this.state.postButtonDisabled}>
            				Post

            			</Button>
            	    </div>
			{console.log("Hilfe ist vor Post: " + this.state.hilfe)}
			<PostList hilfe={this.state.hilfe} currentUser={this.state.currentUser}></PostList>




		</div>


		);
	}
}

export default withStyles(styles)(Posts);

