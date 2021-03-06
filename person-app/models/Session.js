var mongoose = require('mongoose');

// the host:port must match the location where you are running MongoDB
// the "myDatabase" part can be anything you like
mongoose.connect('mongodb://localhost:27017/appDatabase');

var Schema = mongoose.Schema;

var sessionSchema = new Schema({
	sessionID: String,
	tutor: String,
	student: String,
	subject: String,
	date: String,
	duration: Number,
	price: String,
	status: String,
	studentEmail: String,
	tutorEmail: String,
});

// export personSchema as a class called Person
module.exports = mongoose.model('Session', sessionSchema);

sessionSchema.methods.standardizeName = function() {
    this.name = this.name.toLowerCase();
    return this.name;
}
