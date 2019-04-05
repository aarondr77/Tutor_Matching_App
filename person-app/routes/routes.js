/***************************************/

// admin email & admin_password
const admin_email = "admin@adr.com"
const admin_password = "password";

// boolean: True if logged in, False if not
var logged_in = false;

var User = require('../models/User.js');
var Session = require('../models/Session.js');
var Complaint = require('../models/Complaint.js');
var db = require('../models/database.js');


/***************************************/

function clearDB () {
	User.remove({}, function(err) {
		console.log('cleared User database');
	});

	Session.remove({}, function(err) {
		console.log('cleared Session database');
	})
}

function loadData () {

	var user1 = new User({
		firstName: "Aaron",
		lastName: "Diamond-Reivich",
		email: "aaron@gmail.com",
		password: "aaron",
		userType: "tutor",
		price: 10,
		days: "monday",
		times: "3pm",
		numSessions: 2,
		totalCost: 50,
		avgCost: 25,
		rateNum: 2,
		rateToal: 10,
		rating: 5,
		balance: 150,
		qualifications: "Math114",
	});

	var user2 = new User({
		firstName: "Tamir",
		lastName: "Frank",
		email: "tamir@gmail.com",
		password: "tamir",
		userType: "tutor",
		price: 10,
		days: "monday",
		times: "3pm",
		numSessions: 2,
		totalCost: 50,
		avgCost: 25,
		rateNum: 2,
		rateToal: 10,
		rating: 5,
		balance: 150,
		qualifications: "Math114",
	});

	var user3 = new User({
		firstName: "Petra",
		lastName: "Robertson",
		email: "petra@gmail.com",
		password: "petra",
		userType: "student",
		price: 10,
		days: "monday",
		times: "3pm",
		numSessions: 2,
		totalCost: 50,
		avgCost: 25,
		rateNum: 2,
		rateToal: 10,
		rating: 5,
		balance: 50,
		qualifications: "Math114",
	});

	var user4 = new User({
		firstName: "Chris",
		lastName: "Williams",
		email: "chris@gmail.com",
		password: "chris",
		userType: "student",
		price: 10,
		days: "monday",
		times: "3pm",
		numSessions: 2,
		totalCost: 50,
		avgCost: 25,
		rateNum: 2,
		rateToal: 10,
		rating: 5,
		balance: 50,
		qualifications: "Math114",
	});

	var session1 = new Session({
		sessionID: 1,
		tutor: "Aaron",
		student: "Petra",
		subject: 'Math114',
		date: "3/12/19",
		duration: 60,
		price: "25",
		status: "accepted",
		studentEmail: "petra@gmail.com",
		tutorEmail: "aaron@gmail.com",
	});

	var session2 = new Session({
		sessionID: 2,
		tutor: "Aaron",
		student: "Chris",
		subject: 'Math114',
		date: "3/12/19",
		duration: 60,
		price: "25",
		status: "accepted",
		studentEmail: "chris@gmail.com",
		tutorEmail: "aaron@gmail.com",
	});

	var session3 = new Session({
		sessionID: 1,
		tutor: "Tamir",
		student: "Petra",
		subject: 'Math114',
		date: "3/12/19",
		duration: 60,
		price: "25",
		status: "accepted",
		studentEmail: "petra@gmail.com",
		tutorEmail: "tamir@gmail.com",
	});

	var session4 = new Session({
		sessionID: 1,
		tutor: "Tamir",
		student: "Chris",
		subject: 'Math114',
		date: "3/12/19",
		duration: 60,
		price: "25",
		status: "accepted",
		studentEmail: "chris@gmail.com",
		tutorEmail: "tamir@gmail.com",
	});

	// save the person to the database
	user1.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added user")
		}
	});

	// save the person to the database
	user2.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added user")
		}
	});

	// save the person to the database
	user3.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added user")
		}
	});

	// save the person to the database
	user4.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added user")
		}
	});

	// save the person to the database
	session1.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added user")
		}
	});

	// save the person to the database
	session2.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added user")
		}
	});

	// save the person to the database
	session3.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added user")
		}
	});

	// save the person to the database
	session4.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added user")
		}
	});
}


var checkLogin = function(req, res) {

	//clearDB();
	//loadData();

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

var complaints = function (req, res) {
	console.log("complaints");
	fetch('/get complaints').then(async function(response) {
			allComplaints = await response.json();
			res.render('.././views/complaints', {complaints: allComplaints});
	});
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

var deleteSessions = function (req, res) {
	console.log("in Routes!");
	var email = req.body.email;
	var userType = req.body.userType;

	if (userType == "student") {
		db.deleteSessionsOfStudent(email, (err) => {
			if (err) {
				console.log("err:" + err);
			} else {
				console.log("successful delete");
			}
		});
	} else if (userType == "tutor") {
		db.deleteSessionsOfTutor(email, (err) => {
			if (err) {
				console.log("err:" + err);
			} else {
				console.log("successful delete");
			}
		});
	}
}

var routes = {
	check_login: checkLogin,
	logout: logout,
	getUsers: getUsers,
	complaints: complaints,
	deleteSessions: deleteSessions,
};

module.exports = routes;
