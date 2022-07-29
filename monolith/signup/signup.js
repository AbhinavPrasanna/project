function onSubmit() {
    let email = document.getElementById("email")
    let firstName = document.getElementById("firstName")
    let lastName = document.getElementById("lastName")
    let password = document.getElementById("password")

    if (email.value === "") {
        document.getElementById("e-error").innerHTML = "* Email is required"
    } else if (ValidateEmail(email)) {
        console.log("validated bad")
        document.getElementById("e-error").innerHTML = "* Enter valid email address"
    } else {
        document.getElementById("e-error").innerHTML = "&nbsp;"
    }
    if (firstName.value === "") {
        document.getElementById("fn-error").innerHTML = "* First name is required"
    } else {
        document.getElementById("fn-error").innerHTML = "&nbsp;"
    }
    if (lastName.value == "") {
        document.getElementById("ln-error").innerHTML = "* Last name is required"
    } else {
        document.getElementById("ln-error").innerHTML = "&nbsp;"
    } 
    if (password.value == "") {
        document.getElementById("p-error").innerHTML = "* Password is required"
    } else {
        document.getElementById("p-error").innerHTML = "&nbsp;"
    }
}

function ValidateEmail(inputText) {
    console.log("validating")
    const mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    return !(inputText.value.match(mailformat))
}