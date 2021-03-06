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
		rateTotal: 10,
		rating: 3,
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
		rateTotal: 10,
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
		rateTotal: 10,
		rating: 1,
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
		rateTotal: 10,
		rating: 2,
		balance: 50,
		qualifications: ["MATH114-A"],
		pendingQualifications: [],
		sessions: ["2", "4"],
		banned: false,
	});

	var session1 = new Session({
		sessionID: "1",
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
		sessionID: "2",
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
		sessionID: "3",
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
		sessionID: "4",
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
		sessionID: "5",
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
		sessionID: "6",
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

	var session7 = new Session({
		sessionID: "7",
		tutor: "Tamir",
		student: "unclaimed",
		subject: 'Math114',
		date: "3/12/19",
		duration: 60,
		price: "25",
		status: "unclaimed",
		studentEmail: "unclaimed",
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

	session7.save( (err) => {
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
		status: "What a silly Idea",
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
						db.getHighestRatedTutor((highTutorError, highTutor) => {
							var highestRatedTutor = "";
							if(highTutorError) {
								console.log("err for highest tutor", highTutorError);
								highestRatedTutor = highTutorError;
							} else {
								highestRatedTutor = highTutor;
								db.getHighestRatedStudent((highStudentError, highStudent) => {
									var highestRatedStudent = "";
									if(highStudentError) {
										highestRatedStudent = highStudentError;
										console.log("err for highest student", highStudentError);
									} else {
										highestRatedStudent = highStudent;
										res.render('.././views/analytics', {stats: {avgSessions: avgDailySessions, mostPopular: mostPopularCourse, highestTutor: highestRatedTutor
											, highestStudent: highestRatedStudent}});
									}
								});
							}
						});

					}
				})
			}
		});
	} else {
		res.redirect('/');
	}
}

var getUser = function(req, res) {
	console.log("getUser>>>", req.params.email);
	var userEmail = req.params.email;
	db.getUser(userEmail, (err, user) => {
		if(err) {
			console.log("Error finding user", err);
		} else {
			res.json({userFound: user});
		}
	});
}

var addBalance = function(req, res) {
	var userEmail = req.body.email;
	var addBalance = req.body.addBalance;
	db.addBalance(userEmail, addBalance, (err, updatedUser) => {
		if(err) {
			console.log("ERROR ADDING BALANCE", err);
		} else {
			console.log("updated user>>>", updatedUser);
		}
	});
	res.end();
}

var addSession = function(req, res) {
	var tutor = req.body.tutor;
	var student = req.body.student;
	var subject = req.body.subject;
	var date = req.body.date;
	var duration = req.body.duration;
	var price = req.body.price;
	var status = req.body.status;
	var studentEmail = req.body.studentEmail;
	var tutorEmail = req.body.tutorEmail;
	db.addSession(tutor, student, tutorEmail, studentEmail, subject, date, duration, price, status, (err, session) => {
		if(err) {
			console.log("ERROR ADDING SESSION", err);
		} else {
			console.log("updated session", session);
		}
	});
	res.end();
}


var updateRating = function(req, res) {
	var userEmail = req.params.email;
	var addRating = req.params.rating;
	db.updateRating(userEmail, addRating, (err, updatedUser) => {
		if(err) {
			console.log("ERROR ADDING RATING", err);
		} else {
			console.log("updated user>>>", updatedUser);
		}
	});
	res.end();
}


var addComplaint = function(req, res) {
	var content = req.body.content;
	var target = req.body.target;
	var submitter = req.body.submitter;
	var status = req.body.status;
	console.log("req.body", req.body)
	db.addComplaint(target, submitter, content, status, (err) => {
		if (err) {
			console.log("err:" + err);
		} else {
			console.log("successful Update!");
		}
	})
	res.end();
}

var getComplaints = function (req, res) {
	console.log("getting complaints");
	var complaintsArray = []

	db.get_complaints((err, complaints) => {
		console.log(err);
		complaintsArray = complaints;
		console.log(complaintsArray);
		res.json({complaints: complaintsArray});
	})
}

var complaints = function (req, res) {
	console.log("complaints");
	var complaintsArray = []

	if (logged_in) {
		db.get_complaints((err, complaints) => {
			console.log(err);
			complaintsArray = complaints;
			console.log(complaintsArray);
			res.render('.././views/complaints', {complaints: complaintsArray});
		})
	} else {
		res.redirect('/');
	}
}

var bannedUsers = function (req, res) {
	console.log("bannedUsers");
	var usersArray = []

	if (logged_in) {
		db.get_users((err, users) => {
			console.log(err);
			usersArray = users;
			console.log(usersArray);
			res.render('.././views/bannedUsers', {users: usersArray});
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


var getSessions = function (req, res) {
	var sessionArray = [];
	db.getSessions((err, sessions) => {
		console.log(err);
		sessionArray = sessions;
		res.json({sessions: sessionArray});
	})
}

var claimSession = function(req, res) {
	var sessionID = req.body.sessionID;
	var studentEmail = req.body.studentEmail;
	var studentName = req.body.studentName;
	db.claimSession(sessionID, studentEmail, studentName, (err, updatedSession) => {
		if(err) {
			console.log("ERROR CLAIMING SESSION", err);
		} else {
			console.log("updated session>>>", updatedSession);
		}
	});
	res.end();
}

var updateBalanceAndCost = function(req, res) {
	var email = req.body.email;
	var isIncrease = req.body.isIncreasing;
	var amount = req.body.sessionPrice;
	db.updateBalanceAndCost(email, isIncrease, amount, (err, updatedUser) => {
		if(err) {
			console.log("error updating balance", err);
		} else {
			console.log("updated balance>>>", updatedUser);
		}
	});
	res.end();
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

var addNewQualification = function(req, res) {
    console.log("ADDING QUALIF")
    console.log(req.query.email)
    console.log(req.query.qual)

	db.add_new_qualification(req.query.email, req.query.qual, (err) => {
		if (err) {
			console.log(err)
			res.end();
		} else {
			console.log("successfully added qualification")
			res.end();
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

var unbanUser = function (req, res) {
	console.log("in Routes! unBanning User");
	var email = req.body.email;
	console.log(email);

	db.unbanUser(email, (err) => {
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
       		res.end();
       	} else {
        	console.log("successfully reqistered " + req.body.email);
        	res.end();
    	}
    });
}


var routes = {
	check_login: checkLogin,
	logout: logout,
	getUser: getUser,
	getUsers: getUsers,
	addSession: addSession,
	addBalance: addBalance,
	updateRating: updateRating,

	approve_qualification: approveQualification,
	deny_qualification: denyQualification,
	get_users_pending_qualifications: getUsersPendingQualifications,
	remove_qualification: removeQualification,
	add_new_qualification: addNewQualification,

	complaints: complaints,
	bannedUsers: bannedUsers,
	getComplaints: getComplaints,
	addComplaint: addComplaint,
	deleteSessions: deleteSessions,
	updateComplaint: updateComplaint,
	banUser: banUser,
	unbanUser: unbanUser,
	feedback: feedback,
	analytics: analytics,
	home: homepage,

	getSessions: getSessions,
	claimSession: claimSession,
	updateBalanceAndCost: updateBalanceAndCost,
	register_user: registerUser,
};

module.exports = routes;
