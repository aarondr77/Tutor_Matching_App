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

var updateComplaint = function(target, submitter, content, status, route_callback) {
	console.log(submitter);
	Complaint.findOne( {target: target, submitter: submitter, content: content}, (err, comp) => {
		if (err) {
			route_callback(err);
		} else if (!comp) {
			route_callback(200);
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

var banUser = function(target, route_callback) {
	console.log(target);
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

	updateComplaint: updateComplaint,

	banUser: banUser,
};

module.exports = database;

