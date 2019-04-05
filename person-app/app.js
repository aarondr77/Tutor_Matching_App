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

app.post('/logout', routes.logout);

app.post('/deleteSessions', routes.deleteSessions);

app.get('/getUsers', routes.getUsers);

app.post('/complaints',  routes.complaints);
app.use('/complaints',  routes.complaints);

app.post('/updateComplaint', routes.updateComplaint);

app.post('/banUser', routes.banUser);

app.post('/feedback',  routes.feedback);

app.use('/public', express.static('public'));

app.get('/', function(req, res){
  res.render('../views/login', {error_message: null});
});

app.listen(3000,  () => {
	console.log('Listening on port 3000');
});
