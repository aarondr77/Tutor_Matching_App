/***************************************/

// admin email & admin_password
const admin_email = "admin@adr.com"
const admin_password = "password";

// boolean: True if logged in, False if not
var logged_in = false;

var User = require('../models/User.js');
var db = require('../models/database.js');


/***************************************/


var checkLogin = function(req, res) {

	var sessionSchema = new Schema({
		sessionID: String,
		tutor: String,
		student: String,
		subject: String,
		date: String,
		duration: Number,
		price: String,
		status: String,
		studentEmail: Number,
		tutorEmail: Number,
	});


	var session1 = new Session ({
		sessionID: "1",
		tutor: "Aaron Diamond-Reivich",
		student: "Petra Robertson",
		subject: ,
		date: String,
		duration: Number,
		price: String,
		status: String,
		studentEmail: Number,
		tutorEmail: Number,
	 });

	// save the person to the database
	newPerson.save( (err) => {
		if (err) {
		    res.type('html').status(200);
		    res.write('uh oh: ' + err);
		    console.log(err);
		    res.end();
		}
		else {
		    // display the "successfull created" page using EJS
		    res.render('created', {person : newPerson});
		}
	    } );

	var input_email = req.body.email;
	var input_password = req.body.password;

	if (admin_password === input_password && admin_email === input_email) {
		logged_in = true;
		console.log("logged in!");
		var userArray = []
		db.get_users((err, users) => {
			userArray = users
			//console.log(userArray)
			res.render('.././views/homepage', {users: userArray});
		})
	} else {
		res.render('.././views/login', {error_message: "invalid email or password"});
	}
}

var logout = function (req, res) {
	console.log("logedout");
	logged_in = false;
	res.render('.././views/login', {error_message: null});
}

var getUsers = function (req, res) {
	var userArray = []
	db.get_users((err, users) => {
		console.log(err);
		userArray = users
		console.log(userArray);
		res.json({users: userArray});
	})
}

var routes = {
	check_login: checkLogin,
	logout: logout,
	getUsers: getUsers,
};

module.exports = routes;
