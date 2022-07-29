class User {
    constructor(email, password, name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.image = "";
    }
    setImage(image) {
        this.image = image;
    }
}

class Party {
    constructor(title, user, image, date, startTime, location, atendees, maxAtendees, description, price) {
        this.title = title;
        this.user = user;
        this.image = image;
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.atendees = atendees;
        this.maxAtendees = maxAtendees;
        this.description = description;
        this.price = price;
    }
    setEndTime(endTime) {
        this.endTime = endTime;
    }
    setPrice(price) {
        this.price = price;
    }
    getRemainingSpots() {
        return this.maxAtendees - this.atendees;
    }
}

var party;

window.onload = function() {
    //TODO: actually link up a proper party
    //party = sessionStorage.getItem('selectedParty');
    let pUser = new User("aaa@gmail.com", "password", "Chad");
    pUser.setImage("pineapple_profile.png");
    party = new Party("Chad's Block Party", pUser, "", "June 18", "5PM", "Pittsburgh, PA", 35, 45, "Bored? Come on down and enjoy some good music!", 0);

    let userPicture = document.getElementById('profile_pic');
    let userName = document.getElementById('profile_name');
    let atendees = document.getElementById('party_goers_id');
    let remainingSpots = document.getElementById('spots_left_id');

    userPicture.src = party.user.image;
    userName.innerHTML = party.user.name;
    atendees.innerHTML = party.atendees + " party goers";
    remainingSpots.innerHTML = party.getRemainingSpots() + " spots left!";
/*
    let partyTitle = document.getElementById('partyTitle');
    let date = document.getElementById('date');
    let times = document.getElementById('times');
    let location = document.getElementById('location');
    let description = document.getElementById('description');
 
    partyTitle.innerHTML = party.title;
    date.innerHTML = party.date; //TODO: Month is currently output as a number
    times.innerHTML = party.startTime;
    if(party.endTime !== "") {
        times.innerHTML += " - " + party.endTime;
    }
    location.innerHTML = party.location;
    description.innerHTML = party.description;
    */
}

function logoutClick() {
    window.location.replace("login.html");
}