// import the databases
var User = require('./User.js');
var Session = require('./Session.js');
var Complaint = require('./Complaint.js');

// returns all users
var getUsers = function(route_callback) {
	User.find({}, (err, users) => {
		if (err) {
			console.log("err: " + err)
			route_callback(err, null)
		} else if (!users) {
			console.log("no users")
			route_callback(null,  null)
		} else {
			route_callback(null, users)
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

	get_complaints: getComplaints,

	deleteSessionsOfTutor: deleteSessionsOfTutor,
	deleteSessionsOfStudent: deleteSessionsOfStudent,

	updateComplaint: updateComplaint,

	banUser: banUser,
};

module.exports = database;
