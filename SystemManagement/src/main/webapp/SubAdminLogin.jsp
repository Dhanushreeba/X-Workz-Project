<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- To include the Font Awesome library in your HTML and then use the appropriate classes to add the icons. -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
    .oval-btn {
        border-radius: 40px; /* Adjust the value as needed for an oval shape */
        padding: 20px 20px;  /* Adjust the padding to control the button size */
    }


        .bold-text {
            font-weight: bold; /* Makes the text bold */
            font-size: 1.5em;  /* Adjust the size as needed */
        }

    body {
                    background-color: white; /* Change this to the desired background color */
                }
</style>

<script>
    function disableButton() {
        var accountLocked = "${accountLocked}";
        if (accountLocked === "true") {
            document.getElementById("signInButton").disabled = true;
        }
    }
    window.onload = disableButton;
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

<div class="card border-dark container  w-25 mt-5 mb-5 justify-content-center">
    <div class="card-header">
        <h3><b><center>Log In</center></b></h3>
    </div>

    <div class="card-body ">

        <span style="color:green"><strong>${msg}</strong></span>
       <!-- <span style="color:red"><strong>${errorMsg}</strong></span>--!>

                <span style="color:red"><strong>${error}</strong></span>
                        <span style="color:red"><strong>${accountError}</strong></span>

       <!-- <span style="color:red"><strong>${accountLocked}</strong></span> --!>





        <form action="sub-admin-log-in" method="post">

            <div class="row mb-3">
                <span id="emailError" style="color:red;"></span><br>
                <label for="email" class="form-label"><b>Email:</b></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" value="" onblur="emailValidation()">
                </div>
            </div>


            <div class="mb-3">
                <span id="passwordError" style="color:red;"></span><br>
                <label for="password" class="form-label"><b>Password:</b></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" value="" onblur="passwordValidation">
                </div>
            </div>

            <div class="mb-3">
                <a href="SubAdminForgetPassword" class="link-primary">Forgot Password?</a>
            </div>

            <div class="d-flex justify-content-center mt-3">
                <input type="submit" value="Login" class="btn btn-info oval-btn bold-text" id="signInButton">
            </div>
        </form>
    </div>
</div>
</body>
</html>