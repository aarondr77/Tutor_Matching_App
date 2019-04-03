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
			route_callback(null,  null)
		} else {
			route_callback(null, users)
		}
	});
}

var database = {
get_users: getUsers,
};

module.exports = database;
