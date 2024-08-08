<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        .oval-btn {
            border-radius: 40px;
            padding: 20px 20px;
        }
        .bold-text {
            font-weight: bold;
            font-size: 1.5em;
        }
        body {
            background-color: white;
        }
    </style>

    <script>
    let fieldsChecks = {
        "departmentName": false,
        "email": false,
        "password": false,
    };

    function validateAndEnableSubmit() {
        let allValid = true;
        for (let value of Object.values(fieldsChecks)) {
            if (!value) {
                allValid = false;
                break;
            }
        }

        if (allValid) {
            document.getElementById("signInButton").removeAttribute("disabled");
        } else {
            document.getElementById("signInButton").setAttribute("disabled", "");
        }
    }

    function departmentNameValidation() {
        let element = document.getElementById("complaintType");
        let error = document.getElementById("departmentNameError");

        if (element.value.trim() !== "") {
            error.innerHTML = "";
            fieldsChecks["departmentName"] = true;
        } else {
            error.innerHTML = "Please select a department.";
            error.style.color = "red";
            fieldsChecks["departmentName"] = false;
        }

        validateAndEnableSubmit();
    }

    function emailValidation() {
        let element = document.getElementById("email");
        let error = document.getElementById("erroremail");

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (element.value.length >= 3 && element.value.length <= 30 && emailRegex.test(element.value)) {
            error.innerHTML = "";
            fieldsChecks["email"] = true;
        } else {
            error.innerHTML = "Invalid email. Length should be between 3 and 30 characters.";
            error.style.color = "red";
            fieldsChecks["email"] = false;
        }

        validateAndEnableSubmit();
    }

    function passwordValidation() {
        let element = document.getElementById("password");
        let error = document.getElementById("errorpassword");

        if (element.value.length > 0 && element.value.length <= 12) {
            error.innerHTML = "";
            fieldsChecks["password"] = true;
        } else {
            error.innerHTML = "Password should be less than or equal to 12 characters.";
            error.style.color = "red";
            fieldsChecks["password"] = false;
        }

        validateAndEnableSubmit();
    }
    </script>
</head>
<body>

<nav class="navbar navbar-dark bg-info">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <a class="navbar-brand text-dark" href="HomePage"><b>Home</b></a>
        </div>
    </div>
</nav>

<div class="card border-dark container w-25 mt-5 mb-5 justify-content-center">
    <div class="card-header">
        <h3><b><center>Log In</center></b></h3>
    </div>

    <div class="card-body">
        <span style="color:green"><strong>${msg}</strong></span>
        <span style="color:red"><strong>${errorMsg}</strong></span>
        <span style="color:red"><strong>${error}</strong></span>
        <span style="color:red"><strong>${accountError}</strong></span>
        <span style="color:red"><strong>${accountLocked}</strong></span>

        <form action="sub-admin-log-in" method="post">
            <div class="row mb-3">
                <span id="departmentNameError"></span>
                <label for="complaintType" class="form-label"><b>Department Name:</b></label>
                <select class="form-select" id="complaintType" name="departmentName" onblur="departmentNameValidation()">
                    <option value="" ${countryDTO.complaintType == null ? 'selected' : ''}>Select</option>
                    <option value="Electric issue" ${countryDTO.complaintType == 'Electric issue' ? 'selected' : ''}>Electric issue</option>
                    <option value="Water Supply" ${countryDTO.complaintType == 'Water Supply' ? 'selected' : ''}>Water Supply</option>
                    <option value="Network Problem" ${countryDTO.complaintType == 'Network Problem' ? 'selected' : ''}>Network Problem</option>
                    <option value="System Problem" ${countryDTO.complaintType == 'System Problem' ? 'selected' : ''}>System Problem</option>
                    <option value="Gas Leakage" ${countryDTO.complaintType == 'Gas Leakage' ? 'selected' : ''}>Gas Leakage</option>
                </select><br>
            </div>

            <div class="row mb-3">
                <span id="erroremail"></span><br>
                <label for="email" class="form-label"><b>Email:</b></label>
                <input type="email" class="form-control" id="email" name="email" onblur="emailValidation()">
            </div>

            <div class="row mb-3">
                <span id="errorpassword"></span><br>
                <label for="password" class="form-label"><b>Password:</b></label>
                <input type="password" class="form-control" id="password" name="password" onblur="passwordValidation()">
            </div>

            <div class="mb-3">
                <a href="SubAdminForgetPassword" class="link-primary">Forgot Password?</a>
            </div>

            <div class="d-flex justify-content-center mt-3">
                <input type="submit" value="Login" class="btn btn-info oval-btn bold-text" id="signInButton" disabled/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
