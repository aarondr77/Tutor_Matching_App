/***************************************/

// admin email & admin_password
const admin_email = "admin@adr.com"
const admin_password = "password";

// boolean: True if logged in, False if not
var logged_in = false;

var User = require('../models/User.js');

/***************************************/


var checkLogin = function(req, res) {

	var newUser = new User ({
  		firstName: 'Petra',
		lastName: 'Robertson',
		email: 'petrar@seas.upenn.edu',
		password: 'password',
		userType: 'student',
		price: 5,
		days: '',
		times: '',
		numSessions: 0,
		totalCost: 0,
		avgCost: 0,
		rateNum: 0,
		rateToal: 0,
		rating: 0,
		balance: 0,
		qualifications: 'MATH114-A'
  	})
  	newUser.save((err) => {
  	if (err) {
		    console.log(err);
		}
		else {
		    //res.render('.././views/homepage', {error_message: newUser.firstName})
		    console.log(newUser)
		}
  	});


	var input_email = req.body.email;
	var input_password = req.body.password;

	if (admin_password === input_password && admin_email === input_email) {
		logged_in = true;
		console.log("logged in!");
		res.render('.././views/homepage', {error_message: "you signed in!"});
	} else {
		res.render('.././views/login', {error_message: "invalid email or password"});
	}
} 



var routes = {
	check_login: checkLogin,
};

module.exports = routes;