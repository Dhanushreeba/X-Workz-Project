<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>System Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<script>

 <!--this button disabled is not working in js file
                     function disableButton() {
                 var accountLocked = "${accountLocked}";
                 if (accountLocked === "true") {
                     document.getElementById("signinsubmit").disabled = true;
                 }
             }
             window.onload = disableButton;-->
 </script>

    <script>
    let fieldsChecks = {
        "email": false,
        "password": false
    };

    function validateAndEnableSubmit() {
        let flag = false;

        for (let value of Object.values(fieldsChecks)) {
            if (!value) {
                flag = true;
                break;
            }
        }

        const submitButton = document.getElementById("signInButton");
        if (!flag) {
            submitButton.removeAttribute("disabled");
        } else {
            submitButton.setAttribute("disabled", "true");
        }
    }

    function emailValidation() {
        let element = document.getElementById("email");
        let error = document.getElementById("erroremail");

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (element.value.length > 3 && element.value.length < 30 && emailRegex.test(element.value)) {
            error.innerHTML = "";
            fieldsChecks["email"] = true;
        } else {
            error.innerHTML = "Invalid email. Characters should be greater than 3 and less than 30.";
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

    window.onload = function() {
        validateAndEnableSubmit(); // Ensure the button is disabled on page load if fields are invalid
    };


    </script>

</head>
<body>
<nav class="navbar navbar-dark bg-info">
    <div class="container-fluid">
        <div class="navbar-header">
            <!-- Add your logo here -->
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <!-- End of logo -->
            <a class="navbar-brand text-dark" href="HomePage"><b>Home</b></a>
        </div>
    </div>
</nav>

<div class="container mt-5 mb-5 d-flex justify-content-center">
    <div class="card p-4">
        <div class="card-body">
            <form action="admin-controller" method="post">
                <h3><b>Log In</b></h3>
                <span style="color:green"><strong>${adminMessage}</strong></span>
                <span style="color:red"><strong>${errorAdminMessage}</strong></span>

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

                <br>
                <div class="d-flex justify-content-center mt-3">
                    <input type="submit" value="SignIn" class="btn btn-primary oval-btn bold-text" id="signInButton" disabled>
                </div>

                <div class="form-group">
                    <p class="text-center text-muted mt-5 mb-0">Forget password? <a href="ForgetPage"
                        class="fw-bold text-body"><u>Click here</u></a></p>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
