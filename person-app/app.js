// set up Express
var express = require('express');
var app = express();
var routes = require('./routes/routes.js')
var jsdom = require("jsdom");
var mongoose = require("mongoose");
mongoose.connect('mongodb://localhost:27017/appDatabase');

// set up EJS
app.set('view engine', 'ejs');
app.set('views', __dirname + '/views');

// set up BodyParser
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));

// import the User class from User.js
var User = require('./models/User.js');

app.post('/checkLogin', routes.check_login);

app.post('/approveQualification', routes.approve_qualification)

app.post('/denyQualification', routes.deny_qualification)

app.post('/removeQualification', routes.remove_qualification)

app.post('/logout', routes.logout);

app.get('/getUsers', routes.getUsers);

app.get('/getUsersPendingQualification', routes.get_users_pending_qualifications);

app.post('/deleteSessions', routes.deleteSessions);

app.get('/getUsers', routes.getUsers);

app.get('/home', routes.home);

app.get('/complaints',  routes.complaints);
app.post('/complaints',  routes.complaints);

app.post('/complaints/updateComplaint', routes.updateComplaint);

app.post('/complaints/banUser', routes.banUser);

app.get('/analytics', routes.analytics);

app.post('/feedback',  routes.feedback);

app.use('/public', express.static('public'));

app.get('/', function(req, res){
  res.render('../views/login', {error_message: null});
});

app.listen(3000,  () => {
	console.log('Listening on port 3000');
});
