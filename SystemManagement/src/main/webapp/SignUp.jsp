<%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="ISO-8859-1">
            <title>System Management </title>

            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"/>

    <script>
                        let fieldsChecks =
                        {
                            "firstName": false,
                            "lastName": false,
                            "email": false,
                            "contactNumber": false,
                            "alternateNumber": false,
                            "address": false,

                        }

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
                            }
                            else {
                                document.getElementById("submit").setAttribute("disabled", "");
                            }
                        }

                        function firstNameValidation() {
                            let element = document.getElementById("firstName");
                            let error = document.getElementById("firstNameError");

                            let firstNameRegex = /^[A-Za-z\s]+$/;

                            console.log(element);
                            console.log(element.value);
                            console.log(error);

                            if (element.value.length > 3 && element.value.length < 30 && firstNameRegex.test(element.value)) {
                                error.innerHTML = "";
                                fieldsChecks["firstName"] = true;
                            }
                            else {
                                error.innerHTML = "Invalid firstName. Characters should be greater 3 and lessthan 30 ";
                                error.style.color = "red"
                                fieldsChecks["firstName"] = false;

                            }
                            validateAndEnableSubmit();
                        }

                        function lastNameValidation() {
                            let element = document.getElementById("lastName");
                            let error = document.getElementById("lastNameError");

                            let lastNameRegex = /^[A-Za-z\s]+$/;

                            console.log(element);
                            console.log(element.value);
                            console.log(error);

                            if (element.value.length = 1 && element.value.length < 30 && lastNameRegex.test(element.value)) {
                                error.innerHTML = "";
                                fieldsChecks["lastName"] = true;
                            }
                            else {
                                error.innerHTML = "Invalid lastName. Characters start from 1 and lessthan 30 ";
                                error.style.color = "red"
                                fieldsChecks["lastName"] = false;


                            }
                            validateAndEnableSubmit();
                        }


                        function emailValidation() {
                            let element = document.getElementById("email");
                            let error = document.getElementById("emailError");
                            console.log(element);
                            console.log(element.value);
                            console.log(error);


                            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                            if (element.value.length > 3 && element.value.length < 30 && emailRegex.test(element.value)) {
                                error.innerHTML = "";
                                fieldsChecks["email"] = true;
                            }
                            else {
                                error.innerHTML = "Invalid email. Characters should be greater 3 and lessthan 30 ";
                                error.style.color = "red"
                                fieldsChecks["email"] = false;

                            }
                            validateAndEnableSubmit();
                        }


                        function contactNumberValidation() {
                            let element = document.getElementById("contactNumber");
                            let error = document.getElementById("contactNumberError");
                            console.log(element);
                            console.log(element.value);
                            console.log(error);

                            //if(element.value.length ==10 )
                            if (/^\d{10}$/.test(element.value)) {
                                error.innerHTML = "";
                                fieldsChecks["contactNumber"] = true;
                            }
                            else {
                                error.innerHTML = "Invalid contactNumber. number should be  exact 10 digit  ";
                                error.style.color = "red"
                                fieldsChecks["contactNumber"] = false;

                            }
                            validateAndEnableSubmit();
                        }

                        function alternateNumberValidation() {
                            let element = document.getElementById("alternateNumber");
                            let error = document.getElementById("alternateNumberError");
                            console.log(element);
                            console.log(element.value);
                            console.log(error);

                            //if(element.value.length ==10 )
                            if (/^\d{10}$/.test(element.value)) {
                                error.innerHTML = "";
                                fieldsChecks["alternateNumber"] = true;
                            }
                            else {
                                error.innerHTML = "Invalid alternateNumber. alternateNumber should be  exact 10 digit  ";
                                error.style.color = "red"
                                fieldsChecks["alternateNumber"] = false;

                            }
                            validateAndEnableSubmit();
                        }

                        function addressValidation() {
                            let element = document.getElementById("address");
                            let error = document.getElementById("addressError");

                            if (element.value.length > 5 && element.value.length < 300) {
                                error.innerHTML = "";
                                fieldsChecks["address"] = true;
                            } else {
                                error.innerHTML = "address should be greater than 5 and less than 300";
                                error.style.color = "red";
                                fieldsChecks["address"] = false;
                            }

                            validateAndEnableSubmit();
                        }

    <!-- ajax email validation-->

         function emailValidation() {
                            console.log("Validate email");
                            let email = document.getElementById("email").value;
                            console.log(email);
                            let error = document.getElementById("emailError");
                            const request = new XMLHttpRequest();
                            request.open("GET", "http://localhost:8080/SystemManagement/validateEmail/" + email);
                            request.send();
                            console.log(request);
                            request.onload = function () {
                                var ref = this.responseText;
                                console.log(ref);
                                error.innerHTML = ref;

                        <!--enable and disable submit button for ajaxemail validation-->
                                if (ref === "") {
                                fieldsChecks["email"] = true;
                               } else {
                               fieldsChecks["email"] = false;
                               }

                               validateAndEnableSubmit();
                               }
                            }

        function contactNumberValidation() {
            console.log("Validate contact number");
            let contactNumber = document.getElementById("contactNumber").value;
            console.log(contactNumber);
            let error = document.getElementById("contactNumberError");
            const request = new XMLHttpRequest();
            request.open("GET", "http://localhost:8080/SystemManagement/validateContactNumber/" + contactNumber);
            request.send();
            console.log(request);
            request.onload = function () {
                var ref = this.responseText;
                console.log(ref);
                error.innerHTML = ref;

                <!-- Enable and disable submit button for AJAX contact number validation-->
                if (ref === "") {
                    fieldsChecks["contactNumber"] = true;
                } else {
                    fieldsChecks["contactNumber"] = false;
                }

                validateAndEnableSubmit();
            }
            request.onerror = function () {
                        console.error("Request failed");
                        error.innerHTML = "<span style='color:red;'>Validation failed. Please try again.</span>";
                        fieldsChecks["contactNumber"] = false;
                        validateAndEnableSubmit();
                    }
        }


                    </script>
        </head>

        <body>
            <nav class="navbar navbar-dark bg-info">
                <div class="container-fluid">
                    <div class="navbar-header">

                        <!-- Add your logo here -->
                        <a class="navbar-brand" href="#">
                            <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz"
                                width="140" height="70">
                        </a>
                        <!-- End of logo -->


                        <a class="navbar-brand  text-dark" href="index.jsp"><b>Home</b></a>
                        <a class="navbar-brand  text-dark" href="SignIn.jsp"><b>SignIn</b></a>

                    </div>
                </div>
            </nav>



            <%---card---%>


                <div class="container mt-5 mb-5 d-flex justify-content-center">
                    <div class="card p-4 ">
                        <div class="card-body">


                            <form action="signup" method="post">

                                <h3><b>SignUp page</b></h3>

                                <div class="text-primary">${name}</div>
                                <span style="color:red;">
                                    <c:forEach items="${errors}" var="dtoError">
                                        ${dtoError.defaultMessage}</br>
                                    </c:forEach>
                                </span>





                                <div class="  row mb-3 ">
                                    <span id="firstNameError"></span><br>
                                    <label for="firstName" class="form-label  "><b>FirstName:</b></label>
                                    <input type="text" class="form-control " value="${dto.firstName}" id="firstName"
                                        name="firstName" onblur="firstNameValidation()" />
                                </div>

                                <div class="  row mb-3 ">
                                    <span id="lastNameError"></span><br>
                                    <label for="lastName" class="form-label  "><b>LastName:</b></label>
                                    <input type="text" class="form-control " value="${dto.lastName}" id="lastName"
                                        name="lastName" onblur="lastNameValidation()" />
                                </div>


                                <div class="  row mb-3 ">
                                    <span id="emailError"></span><br>
                                    <label for="email" class="form-label  "><b>Email:</b></label>
                                    <input type="email" class="form-control " value="${dto.email}" id="email"
                                        name="email" onblur="emailValidation()" />
                                </div>

                                <div class="  row mb-3 ">
                                    <span id="contactNumberError"></span><br>
                                    <label for="contactNumber" class="form-label  "><b>ContactNumber:</b></label>
                                    <input type="text" class="form-control " value="${dto.contactNumber}"
                                        id="contactNumber" name="contactNumber" onblur="contactNumberValidation()" />
                                </div>

                                <div class="  row mb-3 ">
                                    <span id="alternateNumberError"></span><br>
                                    <label for="alternateNumber" class="form-label  "><b>AlternateNumber:</b></label>
                                    <input type="text" class="form-control " value="${dto.alternateNumber}"
                                        id="alternateNumber" name="alternateNumber"
                                        onblur="alternateNumberValidation()" />
                                </div>

                                <div class="mb-3">
                                    <span id="addressError"></span><br>
                                    <b>Address</b>
                                    <label for="address" class="form-floating"></label>
                                    <textarea class="form-control" placeholder="Leave a Adrress here"
                                        value="${dto.address}" id="address" style="height: 100px" name="address"
                                        onblur="addressValidation()"></textarea>
                                </div>

                                </br>
                                <div class="list-group">


                                    <span id="agreeError"></span>
                                    <label for="agree" class="list-group-item">
                                        <input name="agree" id="agree" name="agree" class="form-check-input me-1"
                                            type="checkbox" value="yes" ${dto.agree eq 'yes' ? 'checked' : '' } />
                                        Agree
                                    </label>
                                </div></br>

                                <input class="btn btn-info" type="submit" id="submit" value="SignUp"/>

                            </form>

                        </div>
                    </div>
                </div>

        </body>
        </html>

