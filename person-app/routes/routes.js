/***************************************/

// admin email & admin_password
const admin_email = "admin@adr.com"
const admin_password = "password";

// boolean: True if logged in, False if not
var logged_in = false;

/***************************************/


var checkLogin = function(req, res) {

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