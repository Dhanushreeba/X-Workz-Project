<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">


 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>


<style>
    .oval-btn {
        border-radius: 50px; /* Adjust the value as needed for an oval shape */
        padding: 10px 20px;  /* Adjust the padding to control the button size */
    }
</style>
</head>
<body>

<nav class="navbar navbar-dark bg-info">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <a class="navbar-brand text-dark" href="HomePage"><b>Home</b></a>
            <a class="navbar-brand text-dark" href="Admin"><b>Admin</b></a>
        </div>



      <div class="dropdown">
                      <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">

                         <!----image display in icon when user sign in--!>
                        <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle">

                  </div>


                      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

                    <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit</strong></a></li>


                          <li><a class="dropdown-item" href="view-profile"><strong>UserView</strong></a></li>

                         <!--<li><a class="dropdown-item" href="raise-complaint-view"><strong>ViewRaiseComplaint</strong></a></li>--!>

                       <!-- <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#ViewModal" ><strong> Modal</strong></a></li>--!>

                          <li><a class="dropdown-item" href="Raise_Complaint"><strong>RaiseComplaint</strong></a></li>

                      <li> <a class="dropdown-item" href="view-raise-complaint"><strong>ViewRaiseComplaint</strong></a></li>

                       <li><a class="dropdown-item" href="HomePage"><strong>Logout</strong></a></li>

         <!-- <a class="dropdown-item" href="raise-complaint-view?complaintId=${complaint.complaintId}">---!>

                      </ul>
                  </div>



    </div>
</nav>

<div class="card border-dark container w-25 mt-5 mb-5 justify-content-center">
    <div class="card-header">
        <h3><b><center>Change Password</center></b></h3>
    </div>
    <div class="card-body text-dark">
        <span style="color:blue"><strong>${changePasswordMessage}</strong></span>
        <span style="color:red"><strong>${changePasswordError}</strong></span>

        <form action="reset-password" method="post">
            <div class="row mb-3">
                <span id="emailError" style="color:red;"></span>
                <label for="email" class="form-label"><b>Email:</b></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                    <input type="email" class="form-control" id="email" name="email" value="${sessionScope.signedInUserEmail}" readonly autocomplete="email" placeholder="Enter email">
                </div>
            </div>

            <div class="mb-3">
                <span id="oldPasswordError" style="color:red;"></span><br>
                <label for="oldPassword" class="form-label"><b>Old Password:</b></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    <input type="password" class="form-control" id="oldPassword" name="oldPassword" autocomplete="old-password" onblur="oldPasswordValidation()" placeholder="Enter old password"/>
                </div>
            </div>

            <div class="mb-3">
                <span id="newPasswordError" style="color:red;"></span><br>
                <label for="newPassword" class="form-label"><b>New Password:</b></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-key w-2"></i></span>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" autocomplete="new-password" onblur="newPasswordValidation()" placeholder="Enter new password">
                </div>
            </div>

            <div class="mb-3">
                <span id="confirmPasswordError" style="color:red;"></span><br>
                <label for="confirmPassword" class="form-label"><b>Confirm Password:</b></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-check"></i></span>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" autocomplete="new-password" onblur="confirmPasswordValidation()" placeholder="Enter confirm password">
                </div>
            </div>

            <div>
                <input type="submit" value="Reset Password" class="btn btn-primary oval-btn" id="submit" disabled>
            </div>

            <div class="mb-3">
                <center><a href="SignInPage" class="link-primary"><strong>SignIn Here?</strong></a></center>
            </div>
        </form>
    </div>
</div>



<script>
    let fieldsChecks = {
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

    function oldPasswordValidation() {
        console.log("oldPasswordValidation");
        let oldPassword = document.getElementById("oldPassword");
        let error = document.getElementById("oldPasswordError");
        let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{10,}$/;

        if (oldPasswordRegex.test(oldPassword.value)) {
            error.innerHTML = "";
            fieldsChecks["oldPassword"] = true;
        } else {
            error.innerHTML = "Password must be at least 10 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
            error.style.color = "red";
            fieldsChecks["oldPassword"] = false;
        }

        validateAndEnableSubmit();
    }

    function newPasswordValidation() {
        console.log("newPasswordValidation");
        let newPassword = document.getElementById("newPassword");
        let error = document.getElementById("newPasswordError");
        let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{10,}$/;

        if (newPasswordRegex.test(newPassword.value)) {
            error.innerHTML = "";
            fieldsChecks["newPassword"] = true;
        } else {
            error.innerHTML = "Password must be at least 10 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
            error.style.color = "red";
            fieldsChecks["newPassword"] = false;
        }

        confirmPasswordValidation(); // Call confirmPasswordValidation to ensure both passwords match
        validateAndEnableSubmit();
    }

    function confirmPasswordValidation() {
        console.log("confirmPasswordValidation");
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
</script>

</body>
</html>