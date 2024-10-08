<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">




<style>
        .oval-btn {
            border-radius: 50px; /* Adjust the value as needed for an oval shape */
            padding: 10px 20px;  /* Adjust the padding to control the button size */
        }
    </style>
<script>
        let fieldsChecks = { "email": false, };

        function validateAndEnableSubmit() {
            let flag = false;
            for (let [key, value] of Object.entries(fieldsChecks)) {
                if (value === false) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                document.getElementById("submit").removeAttribute("disabled");
            } else {
                document.getElementById("submit").setAttribute("disabled", "");
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
        <div class="dropdown">
                        <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">

                        </div>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                            <li> <a class="dropdown-item" href="SubAdminProfile"><strong>sub Admin</strong></a></li>
                            <li> <a class="dropdown-item" href="SubAdminLogin"><strong>SubAdminLogin</strong></a></li>

                        </ul>
                    </div>
        </div>
    </div>
</nav>

<div class="card border-dark container  w-25 mt-5 mb-5 justify-content-center">
    <div class="card-header">
        <h3><b><center>Forgot Password</center></b></h3>
    </div>

    <div class="card-body text-dark">

        <span style="color:green"><strong>${msg}</strong></span>
        <span style="color:red"><strong>${errorMsg}</strong></span>

        <form action="sub-admin-forgot-password" method="post">
            <div class="row mb-3">
                <span id="erroremail"></span><br>
                <label for="email" class="form-label"><b>Email:</b></label>
                <input type="email" class="form-control" value="${dto.email}" id="email" name="email" onblur="emailValidation()">
                   </div>
                     <br>



            <div>
              <center>  <input type="submit" value="Reset Password" class="btn btn-primary oval-btn""> </center>
            </div><br>


            <div class="mb-3">
                    <center>     <a href="SubAdminLogin" class="link-primary"><strong>Login Here?</strong></a></p> </center>
                        </div>

        </form>
    </div>
</div>
</body>
</html>