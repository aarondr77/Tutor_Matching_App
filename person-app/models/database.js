// import the databases
var User = require('./User.js');
var Session = require('./Session.js');
var Complaint = require('./Complaint.js');
var _ = require('lodash');
var dateFounded = new Date('04/02/2019');


// returns all users
var getUsers = function(route_callback) {
	User.find({}, (err, users) => {
		if (err) {
			console.log("err: " + err)
			route_callback(err, null)
		} else if (!users) {
			console.log("no users")
			route_callback(null,  null)
		} else {
			route_callback(null, users)
		}
	});
}

// returns all users
var getSessions = function(route_callback) {
	Session.find({}, (err, sessions) => {
		if (err) {
			console.log("err: " + err)
			route_callback(err, null)
		} else if (!sessions) {
			console.log("no sessions")
			route_callback(null,  null)
		} else {
			route_callback(null, sessions)
		}
	});
}


/* Method to approve a qualification for a single user. Removes that subject
   from the "pendingQualifications" field in their user object
   and adds is to the "qualifications" field */
var approveQualification = function(email_id, qual, route_callback) {
	console.log(qual)
	User.findOne({email: email_id}, (err, user) => {
		if (err) {
			console.log("Unable to find user to update qualifications")
			route_callback(err)
		} else if (!user) {
			route_callback("User does not exist")
		} else {
						// first delete that element from pending qualifications
			var count = 0
			var idx = -1
			var pendingUserQualif = user.pendingQualifications
			pendingUserQualif.forEach( (pendingQual) => {
				console.log("hey now")
				console.log(qual)
				if (pendingQual.split("-")[0] === qual) {
					idx = count
				}
				count++
			})


			pendingUserQualif.splice(idx, 1)
			user.pendingQualifications = pendingUserQualif

			newQualifications = user.qualifications
			if (newQualifications == null || newQualifications == undefined) {
				newQualifications = []
			}

			newQualifications.push(qual)
			user.qualifications = newQualifications

			// then update the DB with the new user object
			user.save( (err) => {
				if (err) {
					console.log("unable to approve user qualification")
					route_callback(err)
				} else {
					console.log("new user object")
					console.log(user)
					route_callback(null)
				}
			})
		}

	})
}

/* Method to deny a qualification for a single user. Removes that subject
   from the "pendingQualifications" field in their user object */
var denyQualification = function(email_id, qual, route_callback) {
	User.findOne({email: email_id}, (err, user) => {
		if (err) {
			console.log("Unable to find user to update qualifications")
			route_callback(err)
		} else if (!user) {
			route_callback("User does not exist")
		} else {

			// first delete find the element to delete
			var count = 0
			var idx = -1
			var pendingUserQualif = user.pendingQualifications
			pendingUserQualif.forEach( (pendingQual) => {
				if (pendingQual.split("-")[0] === qual) {
					idx = count
				}
				count++
			})

			// remove the element from the list
			pendingUserQualif.splice(idx, 1)
			user.pendingQualifications = pendingUserQualif


			//update that user's object
			user.save( (err) => {
				if (err) {
					console.log("unable to approve user qualification")
					route_callback(err)
				} else {
					route_callback(null)
				}
			})
		}
	})
}

var getMostPopularCourse = function (route_callback) {
	var allCourses = [];
	getAllSessions((err, sessionArray) => {
		if(err) {
			route_callback(err, null)
		} else {
			// iterate throuhg all sessions and add course title to allCourses
			_.forEach(sessionArray, function(sessionObject) {
				allCourses.push(sessionObject.subject);
			});
			console.log("all courses", allCourses);
			// get most popular
			var mostPopular = _.head(_(allCourses)
  			.countBy()
  			.entries()
  			.maxBy('[1]'));
			route_callback(null, mostPopular);
		}
	});
}

var getAverageDailySessions = function (route_callback) {
	getAllSessions((err, sessionArray) => {
		if(err) {
			route_callback(err, null)
		} else {
			// get total number of sessions and total number of days
			var oneDay = 24*60*60*1000;
			var totalNumSessions = sessionArray.length;
			var currDate = new Date();
			console.log("currDate: ", currDate);
			console.log("dateFounded: " , dateFounded);
			var totalDays = Math.round(Math.abs((dateFounded.getTime() - currDate.getTime())/(oneDay)));
			console.log("DATE DIFFERENCE>>>>", totalDays);
			var avgDailySessions = totalNumSessions/totalDays;
			route_callback(null, avgDailySessions);
		}
	});
}

var claimSession = function(sessionID, studentEmail, studentName, route_callback) {
	Session.findOne({sessionID: sessionID}, (err, session) => {
		if (err) {
			route_callback(err, null);
		} else if (!session) {
			console.log("session could not be found");
			route_callback("session could not be found", null);
		} else {
			// session Found
			session.studentEmail = studentEmail;
			session.student = studentName;
			session.status = "accepted";

			session.save ((err) => {
				if (err) {
					route_callback(err, null);
				} else {
					console.log("Success", session);
					route_callback(null, session);
				}
			});
		}
	});
}

var addBalance = function(userEmail, amount, route_callback) {
	User.findOne( {email: userEmail}, (err, user) => {
		if (err) {
			route_callback(err, null);
		} else if (!user) {
			console.log("user could not be found");
			route_callback("user could not be found", null);
		} else {
			// user found
			// check for integer overflow
			if(parseInt(user.balance) + parseInt(amount) >= 0) {
				user.balance = parseInt(user.balance) + parseInt(amount);
			}
			user.save ((err) => {
				if (err) {
					route_callback(err, null);
				} else {
					route_callback(null, user);
				}
			});
		}
	})
}

var getAllSessions = function(route_callback) {
	Session.find({}, (err, allSessions) => {
		if(err) {
			console.log("error: " + err);
			route_callback(err, null)
		} else if(!allSessions) {
			console.log("no sessions");
			route_callback(null, null)
		} else {
			console.log("found all users : ", allSessions);
			route_callback(null, allSessions);
		}
	});
}

var getComplaints = function(route_callback) {
	Complaint.find({}, (err, complaints) => {
		if (err) {
			console.log("err: " + err)
			route_callback(err, null)
		} else if (!complaints) {
			console.log("no users")
			route_callback(null,  null)
		} else {
			console.log("getComplaints:" + complaints)
			route_callback(null, complaints)
		}
	});
}


var deleteSessionsOfTutor = function (email, route_callback) {
	console.log("trying to delete: " + email);
	Session.remove({tutorEmail: email}, err => {
		if (err) {
			console.log("err: " + err);
			route_callback(err);
		} else {
			console.log("deleted sessions");
			route_callback(null)
		}
	});
}

var deleteSessionsOfStudent = function (email, route_callback) {
	console.log("trying to delete: " + email);
	Session.remove({studentEmail: email}, err => {
		if (err) {
			console.log("err: " + err);
			route_callback(err);
		} else {
			console.log("deleted sessions");
			route_callback(null)

		}
	});
}

var addComplaint = function(target, submitter, content, status, route_callback) {
	var newComplaint = new Complaint ({target: target, submitter: submitter, content: content, status: status});
	newComplaint.save( (err) => {
		if (err) {
			route_callback(err);
		} else {
			route_callback(null)
		}
	})
}

var updateComplaint = function(target, submitter, content, status, route_callback) {
	console.log(submitter);
	Complaint.findOne( {target: target, submitter: submitter, content: content}, (err, comp) => {
		if (err) {
			route_callback(err);
		} else if (!comp) {
			route_callback("Complaint does not exist")
		} else {
			console.log(typeof submitter);
			comp.status = String(status);
			console.log(comp.status);
			comp.save ((err) => {
				if (err) {
					route_callback(err);
				} else {
					route_callback(null)
				}
			})
		}
	})
}

var removeQualification = function(email_id, qual, route_callback) {
	User.findOne({email: email_id}, (err, user) => {
		if (err) {
			console.log("Unable to find user to update qualifications")
			route_callback(err)
		} else if (!user) {
			route_callback("User does not exist")
		} else {

			// first delete find the element to delete
			var count = 0
			var idx = -1
			var qualifs = user.qualifications
			qualifs.forEach( (qualif) => {
				if (qualif.split("-")[0] === qual) {
					idx = count
				}
				count++
			})

			// remove the element from the list
			qualifs.splice(idx, 1)
			user.qualifications = qualifs

			//update that user's object
			user.save( (err) => {
				if (err) {
					console.log("unable to remove user qualification")
					route_callback(err)
				} else {
					route_callback(null)
				}
			})
		}
	})
}
var updateRating = function(userEmail, addRating, callback) {
	User.findOne({email: userEmail}, (err, user) => {
		if (err) {
			callback(err, null);
		} else if(!user) {
			callback("user not found", null);
		} else {
			user.rateTotal = user.rateTotal + addRating;
			user.rateNum = user.rateNum + 1;
			user.rating = parseFloat(user.rateTotal)/user.rateNum;

			user.save( (err) => {
				if (err) {
					console.log("unable to update user rating")
					callback(err, null);
				} else {
					callback(null, user);
				}
			})
		}
	});
}

var getUser = function(userEmail, callback) {
	User.findOne({email: userEmail}, (err, user) => {
		if (err) {
			callback(err, null);
		} else if(!user) {
			callback("user not found", null);
		} else {
			callback(null, user);
		}
	})
}

var banUser = function(target, route_callback) {
	User.findOne( {email: target}, (err, user) => {
		if (err) {
			route_callback(err);
		} else if (!user) {
			route_callback(200);
		} else {
			user.banned = true;
			user.save ((err) => {
				if (err) {
					route_callback(err);
				} else {
					route_callback(null)
				}
			})
		}
	})
}


var database = {
	get_users: getUsers,
	get_sessions: getAllSessions,
	get_most_popular_course: getMostPopularCourse,
	get_complaints: getComplaints,
	get_avg_daily_sessions: getAverageDailySessions,

	deleteSessionsOfTutor: deleteSessionsOfTutor,
	deleteSessionsOfStudent: deleteSessionsOfStudent,
	getSessions: getSessions,
	claimSession: claimSession,

	updateRating: updateRating,
	updateComplaint: updateComplaint,
	addComplaint: addComplaint,
	addBalance: addBalance,
	getUser: getUser,
	banUser: banUser,

	approve_qualification: approveQualification,
	deny_qualification: denyQualification,
	remove_qualification: removeQualification,
}

module.exports = database;
