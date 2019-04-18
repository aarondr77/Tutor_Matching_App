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


var db = require('../models/database.js');

/***************************************/

function clearDB () {
	User.remove({}, function(err) {
		console.log('cleared User database');
	});

	Session.remove({}, function(err) {
		console.log('cleared Session database');
	})

	Complaint.remove({}, function(err) {
		console.log('cleared Session database');
	})
}

function loadData () {
	var dummyUsers = [];
	var dummySessions = [];
	var dummyComplaints = [];
	var dummyFeedback = [];

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
		qualifications: [],
		pendingQualifications: ["MATH114-C"],
		sessions: ["1", "2"],
		banned: false,
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
		qualifications: [],
		pendingQualifications: ["MATH114-A", "CIS110-B"],
		sessions: ["3", "4"],
		banned: false,
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
		qualifications: [],
		pendingQualifications: ["CIS110-B+"],
		sessions: ["1", "3"],
		banned: false,
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
		qualifications: ["MATH114-A"],
		pendingQualifications: [],
		sessions: ["2", "4"],
		banned: false,
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
		subject: 'Bio001',
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
		subject: 'Phys100',
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
		subject: 'Bio001',
		date: "3/12/19",
		duration: 60,
		price: "25",
		status: "accepted",
		studentEmail: "chris@gmail.com",
		tutorEmail: "tamir@gmail.com",
	});

	var session5 = new Session({
		sessionID: 1,
		tutor: "Tamir",
		student: "Chris",
		subject: 'Bio001',
		date: "3/12/19",
		duration: 60,
		price: "25",
		status: "accepted",
		studentEmail: "chris@gmail.com",
		tutorEmail: "tamir@gmail.com",
	});

	var session6 = new Session({
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

	session5.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added user")
		}
	});

	session6.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added user")
		}
	});

	var complaint1 = new Complaint({
		target: "aaron@gmail.com",
		submitter: "chris@gmail.com",
		content: "He sucks",
		status: 'Approved'
	});

	var complaint2 = new Complaint({
		target: "chris@gmail.com",
		submitter: "tamir@gmail.com",
		content: "Boo",
		status: 'Denied'
	});

	var complaint3 = new Complaint({
		target: "petra@gmail.com",
		submitter: "aaron@gmail.com",
		content: "Silly",
		status: 'Pending'
	});

	complaint1.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added complaint")
		}
	});

	complaint2.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added complaint")
		}
	});

	complaint3.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added complaint")
		}
	});

	var feedback1 = new Complaint({
		target: "admin@adr.com",
		submitter: "tamir@gmail.com",
		content: "This app is Dumbbbb",
	});

	feedback1.save( (err) => {
		if (err) {
		    console.log(err);
		    res.end();
		} else {
			console.log("added feedback")
		}
	});
}

// END OF DUMMY DATA --------------------------------------------------------

var checkLogin = function(req, res) {


	clearDB();
	loadData();


	var input_email = req.body.email;
	var input_password = req.body.password;

	if (admin_password === input_password && admin_email === input_email) {
		logged_in = true;
		console.log("logged in!");
		var userArray = []
		db.get_users((err, users) => {
			userArray = users
			res.redirect('/home');

		})
	} else {
		res.render('.././views/login', {error_message: "invalid email or password"});
	}
}


var logout = function (req, res) {
	console.log("loggedout");
	logged_in = false;
	res.render('.././views/login', {error_message: null});
}

var analytics = function(req, res) {
	console.log("reached analytics");
	if(logged_in) {
		// search for most popular sessions
		var mostPopularCourse = "";

		db.get_most_popular_course((err, course) => {
			if(err) {
				console.log("err for analytics:" , err)
				mostPopularCourse = "Error getting most popular course."
			} else {
				console.log("MOST POPULAR ------> ", course);
				mostPopularCourse = course;
				db.get_avg_daily_sessions((avgDailyErr, average) => {
					avgDailySessions = "";
					if(avgDailyErr) {
						console.log("err for analytics:", avgDailyErr);
						avgDailySessions = avgDailyErr;
					} else {
						avgDailySessions = average;
						res.render('.././views/analytics', {stats: {avgSessions: avgDailySessions, mostPopular: mostPopularCourse}});
					}
				})
			}
		});
	} else {
		res.redirect('/');
	}
}

var addBalance = function(req, res) {
	var userEmail = req.body.email;
	var addBalance = req.body.addBalance;
	console.log("req.body", req.body)
	db.addBalance(userEmail, addBalance, (err, updatedUser) => {
		if(err) {
			console.log("ERROR ADDING BALANCE", err);
		} else {
			console.log("updated user>>>", updatedUser);
		}
	});
}

var complaints = function (req, res) {
	console.log("complaints");
	var complaintsArray = []

	if (logged_in) {
		db.get_complaints((err, complaints) => {
			console.log(err);
			complaintsArray = complaints
			console.log(complaintsArray);
			res.render('.././views/complaints', {complaints: complaintsArray});
		})
	} else {
		res.redirect('/');
	}
}

var homepage = function(req,res) {
	// check if logged in, if not redirect to login page, which currently we have as '/'
	if(logged_in) {
		res.render('.././views/homepage');
	} else {
		res.redirect('/');
	}
}

var feedback = function (req, res) {
	console.log("feedback");
	var complaintsArray = []


	if (logged_in == true) {
		db.get_complaints((err, complaints) => {
			console.log(err);
			complaintsArray = complaints
			console.log(complaintsArray);
			res.render('.././views/feedback', {complaints: complaintsArray});
		})
	} else {
		res.redirect('/');
	}
}

var getUsers = function (req, res) {
	var userArray = []
	db.get_users((err, users) => {
		console.log(err);
		userArray = users
		res.json({users: userArray});
	})
}

var getUsersPendingQualifications = function(req, res) {
	db.get_users((err, users) => {
		if (err) {
			console.log(err)
		} else if (!users) {
			console.log("Users field was null")
		} else {
			var pendingUsers = [];
			var length = users.length - 1
			users.forEach((user) => {
				if (user.pendingQualifications.length > 0) {
					pendingUsers.push(user)
				}
				if (length == 0) {
					//res.setHeader('Content-Type', 'text/plain');
					//res.setHeader('Content-Length', pendingUsers.length);
					console.log(pendingUsers)
					res.json({users: pendingUsers});
				}
				length -= 1;
			})
		}
	})
}

// method to approve a single qualification for a single user
var approveQualification = function(req, res) {
	db.approve_qualification(req.query.email, req.query.qualif, (err) => {
		if (err) {
			console.log(err)
			res.render('.././views/homepage');
		} else {
			console.log("successfully approved qualification!")
			res.render('.././views/homepage');		}
	})
}

// method to deny a single qualification for a single user
var denyQualification = function(req, res) {
	db.deny_qualification(req.query.email, req.query.qualif, (err) => {
		if (err) {
			console.log(err)
			res.render('.././views/homepage');
		} else {
			console.log("successfully denied qualification")
			res.render('.././views/homepage');
		}
	})
}

var removeQualification = function(req, res) {
	db.remove_qualification(req.query.email, req.query.qualif, (err) => {
		if (err) {
			console.log(err)
			res.render('.././views/homepage');
		} else {
			console.log("successfully removed qualification")
			res.render('.././views/homepage');
		}
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

var updateComplaint = function (req, res) {
	console.log("in Routes! Updating Complaints");
	var target = req.body.target;
	var submitter = req.body.submitter;
	var content = req.body.content;
	var status = req.body.status;

	db.updateComplaint(target, submitter, content, status, (err) => {
		if (err) {
			console.log("err:" + err);
		} else {
			console.log("successful Update");
		}
	});
}

var banUser = function (req, res) {
	console.log("in Routes! Banning User");
	var target = req.body.target;

	db.banUser(target, (err) => {
		if (err) {
			console.log("err:" + err);
		} else {
			console.log("successful Banning");
		}
	});
}

var registerUser = function(req, res) {
    db.register_new_user(req.body, (err) => {
        if (err) {
       		console.log("err:" + err);
       	} else {
        	console.log("successfully reqistered " + req.body.email);
        	res.end();
    	}
    });
}


var routes = {
	check_login: checkLogin,
	logout: logout,
	getUsers: getUsers,
	addBalance: addBalance,
	approve_qualification: approveQualification,
	deny_qualification: denyQualification,
	get_users_pending_qualifications: getUsersPendingQualifications,
	remove_qualification: removeQualification,
	complaints: complaints,
	deleteSessions: deleteSessions,
	updateComplaint: updateComplaint,
	banUser: banUser,
	feedback: feedback,
	analytics: analytics,
	home: homepage,
	register_user: registerUser,
};

module.exports = routes;
