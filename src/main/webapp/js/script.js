//Login form
function validateForm() {
    var empName = isEmptyLog("j_username");
    var empPass = isEmptyLog("j_password");
    var regLogin = validateLogin();
    if (!empName || !empPass) {
        alert("Name and Password must be filled out");
        return false;
    } else if (!regLogin) {
        alert("Illegal login format! Example 'E-not_2015'");
        return false;
    }else{
        return true;
    }
}

function validateRegForm() {
    var empName = isEmpty("name");
    var empLogin = isEmpty("login");
    var empPass = isEmpty("password");
    var empPassConfirm = isEmpty("passwordConfirm");
    var empEmail = isEmpty("email");
    var empEmailConfirm = isEmpty("emailConfirm");
    var regLogin = validateLogin("login");
    var regEmail = validateEmail("email");
    var regEmailConfirm = validateEmail("emailConfirm");
    var regName = validateName("name");
    var validatePass = validatePassword();
    if (!empName || !empLogin || !empPass || !empPassConfirm || !empEmail || !empEmailConfirm) {
        alert("All fields must be filled out");
        return false;
    } else if (!regLogin) {
        alert("Illegal login format! Example 'E-not_2015'");
        return false;
    } else if (!validatePass) {
        alert("Passwords do not match!");
        return false;
    } else if (!regEmail || !regEmailConfirm) {
        alert("Illegal email format! Example 'XYZ_W@gmail.com'");
        return false;
    } else if (!regEmail === regEmailConfirm) {
        alert("Emails do not match!");
        return false;
    } else if (!regName) {
        alert("Illegal Username format! Username must have alphabet characters only!");
        return false;
    } else {
        return true;
    }
}

function isEmptyLog(field) {
    var fieldHolder = document.forms["login"][field];
    return fieldHolder && fieldHolder.value;
}
function isEmpty(field) {
    var fieldHolder = document.forms["regData"][field];
    return fieldHolder && fieldHolder.value;
}

function validateEmail() {
    var email = document.forms["regData"]["email"].value;
    var chrbeforAt = email.substr(0, email.indexOf('@'));
    if (!($.trim(email).length > 127)) {
        if (chrbeforAt.length >= 2) {
            var regExp = /^([a-zA-Z0-9_\.\-+])+@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            return regExp.test(email);
        } else {
            return false;
        }
    } else {
        return true;
    }
}

function validateName() {
    var name = document.forms["regData"]["name"].value;
    if (!($.trim(name).length > 25)) {
        var nameReg = /^([a-zA-Z])+$/;
        return nameReg.test(name);
    } else {
        return false;
    }
}

function validatePassword() {
    var pass = document.forms["regData"]["password"].value;
    var passConfirm = document.forms["regData"]["passwordConfirm"].value;
    return pass === passConfirm;
}


function validateLogin() {
    var login = document.forms["login"]["j_username"].value;
    if (!($.trim(login).length > 15)) {
        var logReg = /^([a-zA-Z0-9_-])+$/;
        return logReg.test(login);
    } else {
        return false;
    }
}

