import React from "react";
import { withStyles } from '@material-ui/core/styles';
import clsx from "clsx";
import InputLabel from "@material-ui/core/InputLabel";
import Input from "@material-ui/core/Input";
import FormControl from "@material-ui/core/FormControl";
import Button from "@material-ui/core/Button";

const styles = theme => ({


	kommentar: {
		width: "90%",
		padding: "5%",
		boxSizing: "borderBox",
		background: "#B5E196",
		marginTop: "10%",
        height: '25px',
	},

	textField: {
		width: "75%",
		padding: '1px',

	},

	buttonRightAlign: {
		padding: '50px',
		textAlign: 'right',

	},


});

class PostForm extends React.Component {

	constructor(props) {
		super(props);
	    this.state = {
			text: "",
			longitude: 20,
			latitude: 20,
	    	authorId: this.props.currentUser.id,
            postId: this.props.post.id,
            newKomment: []
		};
	}

	handleChange = (event) => {
    	const text = this.state.text;
    	  if (event.target.id==="text") {
    		this.setState({text: event.target.value});
    		if (text !== null && text.length < 1) {
    			this.setState({commentButtonDisabled: true });
    		} else {
    			this.setState({commentButtonDisabled: false });
    		}
    	  }
    	};

	createComment = ( event ) => {
          //var formdata = JSON.stringify( this.state );

          fetch( "http://localhost:8080/Comments/addComment?text=" + this.state.text +
		  "&lon=20&lat=20&authorId=" + this.state.authorId + "&postId=" + this.state.postId,{

              headers: {
                  'Content-Type': 'application/json',//+ 'application/Long',
                  'Accept': 'application/json',

	},
              method: 'post',
             // body: formdata
          })
              .then( this.status )
              .then( function(response) { return response.json() } )
			  .then( data => this.setState({newKomment: data }))

              .catch( function( error ) {
                  console.log( 'Request failed', error );
              });

      }


	render() {
		const { classes } = this.props;

		//console.log( "Benutzer ist in PostForm:" + this.state.currentUser.username)
		//console.log("Der post ist:" + this.state.post.text)
		return (
		<div className={classes.kommentar}>


			<FormControl className={clsx( classes.textField)}>
				<InputLabel htmlFor="text">Hier können Sie kommentieren</InputLabel>
				<Input
					id="text"
					type='text'
					value={this.state.text}
					onChange={this.handleChange}
				/>
			</FormControl>

			<Button variant="contained" color="Primary"

			onClick={() => this.createComment()}>
				Kommentieren

			</Button>
	    </div>

		);
	}
}

export default withStyles(styles)(PostForm);

