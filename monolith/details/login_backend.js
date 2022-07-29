function openSignUpPage() {
    window.location = "signup.html"
}

function submitCredentials() {
    let email_field = document.getElementById("email_field");
    let pwd_field = document.getElementById("pwd_field");

    if (email_field.value.length < 1) {
        let missing_email = document.getElementById("missing_email");
        missing_email.innerHTML = "* Email is required";
        return;
    }

    if (! ValidateEmail(email_field)) {  // Check if valid email format
        let missing_email = document.getElementById("missing_email");
        missing_email.innerHTML = "* Incorrect email format";
        return;
    }

    if (pwd_field.value.length < 1) {
        let missing_email = document.getElementById("missing_pwd");
        missing_email.innerHTML = "* Password is required";
        return;
    }

    if (email_field.value === "aaa@partopia.com" && pwd_field.value === "bbb") {  // TODO: Add proper validation
        loadHomePage()
    } else {
        let invalid_creds_text = document.getElementById("invalid_creds_text");
        invalid_creds_text.innerHTML = "* Email or password is incorrect";
        return;
    }
}

function ValidateEmail(inputText) {
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    return inputText.value.match(mailformat);
}

function onEmailType() {
    let email_field = document.getElementById("email_field");

    if (email_field.value.length >= 0) {
        let missing_email = document.getElementById("missing_email");
        missing_email.innerHTML = "";
    }
}

function onPwdType() {
    let pwd_field = document.getElementById("pwd_field");

    if (pwd_field.value.length >= 0) {
        let missing_pwd = document.getElementById("missing_pwd");
        missing_pwd.innerHTML = "";
    }
}

function loadHomePage() {
    window.location = "homepage_auth.html"
}

