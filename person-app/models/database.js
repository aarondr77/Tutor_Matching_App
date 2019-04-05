// import the User class from User.js
var User = require('./User.js');


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


/* Method to approve a qualification for a single user. Removes that subject
   from the "pendingQualifications" field in their user object 
   and adds is to the "qualifications" field */
var approveQualification = function(email_id, qual, route_callback) {
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
				if (pendingQual.split("-")[0] === qual) {
					console.log(pendingQual.split("-")[0])
					idx = count
				}
				count++
			})
			
			console.log(pendingUserQualif)
			pendingUserQualif.splice(idx, 1)
			user.pendingQualifications = pendingUserQualif
			console.log(pendingUserQualif)
			console.log(idx)

			//then add it to their qualifications
			user.qualifications.push(qual)

			user.save( (err) => {
				if (err) {
					console.log("unable to approve user qualification")
					route_callback(err)
				} else {
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
					console.log(pendingQual.split("-")[0])
					idx = count
				}
				count++
			})
			
			// remove the element from the list
			console.log(pendingUserQualif)
			pendingUserQualif.splice(idx, 1)
			user.pendingQualifications = pendingUserQualif
			console.log(pendingUserQualif)
			console.log(idx)

			//update that user's object
			user.save( (err) => {
				if (err) {
					console.log("unable to approve user qualification")
					route_callback(err)
				} else {
					console.log(user)
					route_callback(null)
				}
			})
		}
	})
}

var removeQualification = function(email_id, qual, route_callback) {
	console.log("REMOVE QUALIFICATIONS METHOD")
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
			console.log(qualifs)
			// remove the element from the list
			qualifs.splice(idx, 1)
			user.qualifications = qualifs
			console.log(qualifs)

			//update that user's object
			user.save( (err) => {
				if (err) {
					console.log("unable to remove user qualification")
					route_callback(err)
				} else {
					console.log("UPDATED USER OBJECT")
					console.log(user)
					route_callback(null)
				}
			})
		}
	})
}



var database = {
	get_users: getUsers,
	approve_qualification: approveQualification,
	deny_qualification: denyQualification,
	remove_qualification: removeQualification,
};

module.exports = database;

