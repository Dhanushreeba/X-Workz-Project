<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Student Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

        <script src="/SystemManagement/js/RaiseComplaint.js"></script>

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
            <a class="navbar-brand text-dark" href="ProfileUploadPage"><b>ProfileUpload</b></a>
            <a class="dropdown-item" href="view-raiseComplaint?id=${userData.id}" ></b>ViewRaiseComplaint</b></a>

            </div>


            <!-- Display profile image when user is signed in -->
                    <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle">


        </div>

    </nav>

    <div class="card border-dark container w-25 mt-5 mb-5 justify-content-center">
        <div class="card-header">
            <h3><b><center>Raise Complaints</center></b></h3>
        </div>
        <div class="card-body text-dark">

        <span style="color:green"><h2>${raiseComplaintMsg}</h2></span>


            <form action="raise" method="post">

<!--For server side validation this is used--!>
                <div class="text-success"><strong>${msg}</strong></div>
                <span style="color:red;">
                    <c:forEach items="${errors}" var="objectError">
                        ${objectError.defaultMessage}<br>
                    </c:forEach>
                </span>

                <div class="row mb-3">
                    <span id="complaintTypeError"></span>
                    <label for="complaintType" class="form-label"><b>Complaint Type:</b></label>
                    <select class="form-select custom-select-width" id="complaintType" name="complaintType">
                        <option value="0" ${raiseComplaintDto.complaintType == null ? 'selected' : ''}>Select</option>
                        <option value="Electric issue" ${raiseComplaintDto.complaintType == 'Electric issue' ? 'selected' : ''}>Electric issue</option>
                        <option value="Water Supply" ${raiseComplaintDto.complaintType == 'Water Supply' ? 'selected' : ''}>Water Supply</option>
                        <option value="Network Problem" ${raiseComplaintDto.complaintType == 'Network Problem' ? 'selected' : ''}>Network Problem</option>
                        <option value="System Problem" ${raiseComplaintDto.complaintType == 'System Problem' ? 'selected' : ''}>System Problem</option>
                        <option value="Water Problem" ${raiseComplaintDto.complaintType == 'Water Problem' ? 'selected' : ''}>Water Problem</option>
                    </select><br>
                </div>


                <!----Country ---!>

                <div class="row mb-3">
                    <span id="countryNameError"></span>
                    <label for="countryName" class="form-label"><b>Country:</b></label>
                    <select class="form-select custom-select-width" id="countryName" name="country"  placeholder="Enter country">
                        <!-- Countries will be loaded here by JavaScript -->
                    </select><br>
                </div>

                <!----State ---!>

                <div class="row mb-3">
                    <span id="stateNameError"></span>
                    <label for="state" class="form-label"><b>State:</b></label>
                    <select class="form-select custom-select-width" id="state" name="state"  >
                        <!-- States will be loaded here by JavaScript -->
                    </select><br>
                </div>

                <!----City ---!>

                <div class="row mb-3">
                    <span id="cityNameError"></span>
                    <label for="city" class="form-label"><b>City:</b></label>
                    <select class="form-select custom-select-width" id="city" name="city" placeholder="Enter city">
                        <!-- Cities will be loaded here by JavaScript -->
                    </select><br>
                </div>


<div class="row mb-3">
                    <span id="areaError"></span><br>
                    <label for="area" class="form-label"><b>Area:</b></label>
                    <input type="text" class="form-control" id="area" name="area" placeholder="Enter area">
                </div>



<div class="mb-3">
                    <span id="errorAddress"></span><br>
                    <b>Address</b>
                    <label for="address" class="form-floating"></label>
                    <textarea class="form-control" placeholder="Enter address" id="address" style="height: 80px" name="address"></textarea>
                </div>



<div class="mb-3">
                <span id="discriptionError" class="text-danger"></span>
             <b>Description:</b>
                <div class="form-floating">
                    <textarea class="form-control" placeholder="Leave a comment here" name="discription" id="discription"  style="height:80px"  oninput="updateDiscriptionCount()" maxlength="300" onblur="validateDiscription()">${complaint.description}</textarea>
                    <label for="discription">Description</label>
                </div>
            </div>



                <div>
                    <center><input type="submit" id="submit" value="Apply" class="btn btn-primary oval-btn"></center>
                </div>

                <div class="mb-3">
                    <center><p>Already have an account? <a href="SignIn" class="link-primary"><strong>SignIn Here</strong></a></p></center>
                </div>
            </form>
        </div>
    </div>

    <!-- Including the JavaScript file after the DOM is loaded -->
</body>
</html>
