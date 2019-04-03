// set up Express
var express = require('express');
var app = express();

// set up EJS
app.set('view engine', 'ejs');
app.set('views', __dirname + '/views');

// set up BodyParser
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));

// import the Person class from Person.js
var Person = require('./Person.js');

/***************************************/

// admin email & admin_password
const admin_email = "admin@adr.com"
const admin_password = "password";

// boolean: True if logged in, False if not
var logged_in = false;

/***************************************/




	// route for creating a new person
	// this is the action of the "create new person" form
	app.post('/checkLogin', (req, res) => {

		var input_email = req.body.email;
		var input_password = req.body.password;

		if (admin_password === input_password && admin_email === input_email) {
			logged_in = true;
			console.log("logged in!");
			res.render('../views/login', {error_message: "you signed in!"});
		} else {
			res.render('../views/login', {error_message: "invalid email or password"});
		}
	});


/*************************************************/

app.use('/public', express.static('public'));

app.get('/', function(req, res){
  res.render('../views/login', {error_message: null});
});


app.listen(3000,  () => {
	console.log('Listening on port 3000');
});
