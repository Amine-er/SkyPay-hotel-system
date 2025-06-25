db = db.getSiblingDB('bookingdb');
db.createUser({
    user: "bookinguser",
    pwd: "bookingpass",
    roles: [
        { role: "readWrite", db: "bookingdb" }
    ]
});