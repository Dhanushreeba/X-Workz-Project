<%@ page  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>System Management </title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<script>

 <!--this button disabled is not working in js file-->
                     function disableButton() {
                 var accountLocked = "${accountLocked}";
                 if (accountLocked === "true") {
                     document.getElementById("signinsubmit").disabled = true;
                 }
             }
             window.onload = disableButton;
 </script>
<script>
let fieldsChecks=
{
		"email":false,
		"password":false,

}


function validateAndEnableSubmit()
{
    let flag = false;

    for(let [key, value] of Object.entries(fieldsChecks))
    {

        if(value === false)
        {
            flag = true;
            break;
        }
    }

    if(!flag)
    {
        document.getElementById("submit").removeAttribute("disabled");//valuse empty idre disabled
    }
    else
    {
        document.getElementById("submit").setAttribute("disabled","");//valuse iddaga enable agirutte
    }
}

function emailValidation()

{
    let element = document.getElementById("email");
    let error = document.getElementById("erroremail");
    console.log(element);
    console.log(element.value);
    console.log(error);


        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (element.value.length > 3 && element.value.length < 30 && emailRegex.test(element.value))
    {
        error.innerHTML = "";
        fieldsChecks["email"] = true;
    }
    else
    {
        error.innerHTML = "Invalid email. Characters should be greater 3 and lessthan 30 ";
        error.style.color="red"
        fieldsChecks["email"] = false;

}
    validateAndEnableSubmit();
}

function passwordValidation() {
                    let element = document.getElementById("password");
                    let error = document.getElementById("errorpassword");

                    if (element.value.length<=12) {
                        error.innerHTML = "";
                        fieldsChecks["password"] = true;
                    } else {
                        error.innerHTML = "password should be lessthan or equal to 12";
                        error.style.color = "red";
                        fieldsChecks["password"] = false;
                    }

                    validateAndEnableSubmit();
}

function disableButton() {
              var accountLocked = document.getElementById("accountLocked").value;
              if (accountLocked === "true") {
                  document.getElementById("signinsubmit").disabled = true;
              }
          }
          window.onload =disableButton;

</script>
</head>
<body>
<nav class="navbar navbar-dark bg-info">
    <div class="container-fluid">
        <div class="navbar-header">

        <!-- Add your logo here -->
                                    <a class="navbar-brand" href="#">
                                        <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70" >
                                    </a>
                                    <!-- End of logo -->


        <a class="navbar-brand  text-dark" href="index.jsp"><b>Home</b></a>
        <a class="navbar-brand  text-dark" href="SignUp.jsp"><b>SignUp</b></a>



  </div>
</nav>



<%---card---%>


<div class="container mt-5 mb-5 d-flex justify-content-center">
    <div class="card p-4 ">
        <div class="card-body">


            <form action="signin"  method="post">

                                <h3><b>SignIn</b></h3>

                    <div class="text-primary">${msg}</div>
                    <span style="color:green">${error}</span>
                    <span style="color:red;">
                        <c:forEach items="${errors}" var="dtoError">
                        ${dtoError.defaultMessage}<br/>
                        </c:forEach>
                        </span>

            <div class="  row mb-3 ">
                        <span id="erroremail" ></span><br>
                                    <label for="email" class="form-label  "><b>Email:</b></label>
                                    <input type="email" class="form-control "  value="${dto.email}" id="email" name="email" onblur="emailValidation()">
                                </div>


              <div class="  row mb-3 ">
              <span id="errorpassword"></span><br>
                 <label for="password" class="form-label  "><b>Password:</b></label>
                 <input type="password" class="form-control "  value="${dto.password}" id="password" name="password" onblur="passwordValidation()">
                 <i id="password-icon" class="fa fa-key" style="display: none;"></i>
                        </div>

             <br>
                            <div class="list-group">
                                <input class="btn btn-info" type="submit" id="signinsubmit" value="SignIn"  <c:if test="${isLocked}">disabled</c:if>/>
                            </div>

                        <div class="form-group">
                                  <p class="text-center text-muted mt-5 mb-0">Forget password? <a href="ForgetPassword.jsp"
                                      class="fw-bold text-body"><u>Click here</u></a></p>
                                  </div>


</form>


 </div>
    </div>
</div>

</body>
</html>
