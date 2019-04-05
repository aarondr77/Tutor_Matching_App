var mongoose = require('mongoose');

// the host:port must match the location where you are running MongoDB
// the "myDatabase" part can be anything you like
mongoose.connect('mongodb://localhost:27017/appDatabase');

var Schema = mongoose.Schema;

var complaintSchema = new Schema({
	target: String,
	submitter: String,
	content: String,
	status: String,
});

// export personSchema as a class called Person
module.exports = mongoose.model('Complaint', complaintSchema);

complaintSchema.methods.standardizeName = function() {
    this.name = this.name.toLowerCase();
    return this.name;
}
