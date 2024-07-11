<%@ page  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>System Management </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
            <a class="navbar-brand text-dark" href="index.jsp"><b>Home</b></a>
            <a class="navbar-brand text-dark" href="SignUp.jsp"><b>SignUp</b></a>
            <a class="navbar-brand text-dark" href="SignIn.jsp"><b>SignIn</b></a>
        </div>
    </div>
</nav>

<div class="container mt-5 mb-5 d-flex justify-content-center">
    <div class="card p-4">
        <div class="card-body">
            <form action="forget" method="post">
                <h3><b>Forget Password</b></h3>

                <div class="row mb-3">
                    <span id="erroremail"></span><br>
                    <label for="email" class="form-label"><b>Email:</b></label>
                    <input type="email" class="form-control" value="${dto.email}" id="email" name="email" onblur="emailValidation()">
                </div>
                <br>
                <div class="list-group">
                    <input class="btn btn-info" type="submit" id="submit" value="submit" disabled/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>


