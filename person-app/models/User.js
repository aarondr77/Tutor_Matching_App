var mongoose = require('mongoose');

// the host:port must match the location where you are running MongoDB
// the "myDatabase" part can be anything you like
mongoose.connect('mongodb://localhost:27017/appDatabase');

var Schema = mongoose.Schema;

var userSchema = new Schema({
	firstName: String,
	lastName: String,
	email: {type: String, unique: true},
	password: String,
	userType: String,
	price: Number,
	days: String,
	times: String,
	numSessions: Number,
	totalCost: Number,
	avgCost: Number,
	rateNum: Number,
	rateToal: Number,
	rating: Number,
	balance: Number,
	qualifications: [String],
	pendingQualifications: [String],
	sessions: [String]
	banned : Boolean,
});


// export personSchema as a class called Person
module.exports = mongoose.model('User', userSchema);

userSchema.methods.standardizeName = function() {
    this.name = this.name.toLowerCase();
    return this.name;
}
