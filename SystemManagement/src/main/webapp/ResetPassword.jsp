<%@ page  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>System Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

 <script>
<!--
let fieldsChecks = {
    "email": false,
    "oldPassword": false,
    "newPassword": false,
    "confirmPassword": false
};

function validateAndEnableSubmit() {
    let allFieldsValid = Object.values(fieldsChecks).every(value => value);
    let submitButton = document.getElementById("submit");

    if (allFieldsValid) {
        submitButton.removeAttribute("disabled");
    } else {
        submitButton.setAttribute("disabled", "true");
    }
}

function emailValidation() {
    console.log("emailValidation")
    let email = document.getElementById("email");
    let error = document.getElementById("emailError");
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (emailRegex.test(email.value)) {
        error.innerHTML = "";
        fieldsChecks["email"] = true;
    } else {
        error.innerHTML = "Please enter a valid email address.";
        error.style.color = "red";
        fieldsChecks["email"] = false;
    }

    validateAndEnableSubmit();
}

function oldPasswordValidation() {
    console.log("oldPasswordValidation")
    let oldPassword = document.getElementById("oldPassword");
    let error = document.getElementById("oldPasswordError");
    //let oldPasswordRegex =!@#$%^&*()_+[]{}|;:,.<>?
    //let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&<>])[A-Za-z\d@$!%*?&<>]{12,}$/;
    let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{12,}$/;


    if (oldPasswordRegex.test(oldPassword.value)) {
        error.innerHTML = "";
        fieldsChecks["oldPassword"] = true;
    } else {
        error.innerHTML = "Password must be at least 12 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
        error.style.color = "red";
        fieldsChecks["oldPassword"] = false;
    }

    validateAndEnableSubmit();
}

function newPasswordValidation() {
    console.log("newPasswordValidation")
    let newPassword = document.getElementById("newPassword");
    let error = document.getElementById("newPasswordError");
//    let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&<>])[A-Za-z\d@$!%*?&<>]{12,}$/;

    let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{12,}$/;

    if (newPasswordRegex.test(newPassword.value)) {
        error.innerHTML = "";
        fieldsChecks["newPassword"] = true;
    } else {
        error.innerHTML = "Password must be at least 12 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
        error.style.color = "red";
        fieldsChecks["newPassword"] = false;
    }

    confirmPasswordValidation();
    validateAndEnableSubmit();
}

function confirmPasswordValidation() {
    let newPassword = document.getElementById("newPassword");
    let confirmPassword = document.getElementById("confirmPassword");
    let error = document.getElementById("confirmPasswordError");

    if (confirmPassword.value === newPassword.value && fieldsChecks["newPassword"]) {
        error.innerHTML = "";
        fieldsChecks["confirmPassword"] = true;
    } else {
        error.innerHTML = "Passwords do not match.";
        error.style.color = "red";
        fieldsChecks["confirmPassword"] = false;
    }

    validateAndEnableSubmit();
}
-->

  </script>


</head>

<body>
<nav class="navbar navbar-dark bg-info">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <a class="navbar-brand text-dark" href="index.jsp"><b>Home</b></a>
        </div>
    </div>
</nav>

<div class="card border-dark container w-25 mt-5 mb-5 justify-content-center">
    <div class="card-header">
        <h3><b><center>Reset Password</center></b></h3>
    </div>
    <div class="card-body text-dark">
        <span style="color:green"><strong>${resetPasswordMessage}</strong></span>
        <span style="color:red"><strong>${resetPasswordError}</strong></span>


        <form action="reset" method="post">
            <div class="mb-3">
                <label for="email" class="form-label"><b>Email:</b></label>
                <input type="email" class="form-control" id="email" name="email"
                 autocomplete="email" required />
                <!--<span id="emailError" style="color:red;"></span><br>-->
            </div>
            <div class="mb-3">
                <label for="oldPassword" class="form-label"><b>Old Password:</b></label>
                <input type="password" class="form-control" id="oldPassword" name="oldPassword"
                autocomplete="old-password" required />
                <!--<span id="erroroldPassword" style="color:red;"></span><br>-->
            </div>
            <div class="mb-3">
                <label for="newPassword" class="form-label"><b>New Password:</b></label>
                <input type="password" class="form-control" id="newPassword" name="newPassword"
                autocomplete="new-password" required />
                <!--<span id="errornewPassword" style="color:red;"></span><br>-->
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label"><b>Confirm Password:</b></label>
                <input type="password" class="form-control" id="confirmPassword"
                name="confirmPassword" autocomplete="new-password" required />
                <!--<span id="confirmPasswordError" style="color:red;"></span><br>-->
            </div>
            <div class="list-group">
                <input class="btn btn-info" type="submit" id="submit" value="Reset" />
            </div>
            <div class="mb-3">
                <center><a href="SignIn.jsp" class="link-primary"><strong>SignIn Here?</strong></a></center>
            </div>
        </form>
    </div>
</div>


</body>
</html>

