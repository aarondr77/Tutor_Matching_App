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
	var input_email = req.body.email;
	var input_password = req.body.password;

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
	pendingQualifications: 'MATH114-A',
	qualifications: ''
  })
  newUser.save((err) => {
  	if (err) {
		    console.log(err);
		}
		else {
		    // display the "successfull created" page using EJS
		}
  });

	if (admin_password === input_password && admin_email === input_email) {
		logged_in = true;
		console.log("logged in!");
		var userArray = []
		db.get_users((err, users) => {
			userArray = users
			console.log(userArray)
			res.render('.././views/homepage', {users: userArray});

		})
	} else {
		res.render('.././views/login', {error_message: "invalid email or password"});
	}
} 


var routes = {
	check_login: checkLogin,
};

module.exports = routes;