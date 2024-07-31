<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Department Page</title>

 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

<!-- To include the Font Awesome library in your HTML and then use the appropriate classes to add the icons. -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>

    .dropdown-toggle-custom {
    color: #fff;
    background-color: transparent;
    border: 1px solid #fff;
    padding: 5px 10px;
    border-radius: 5px;
}
        .dropdown-toggle-custom:hover {
    background-color: #495057; /* Darker grey for hover */
}
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



<div class="dropdown">
            <button class="dropdown-toggle dropdown-toggle-custom" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                Admin
            </button>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

                    <li><a class="dropdown-item" href="view-user-details"><strong>ViewUserDetails</strong></a></li>
                    <li> <a class="dropdown-item" href="view-user-raise-complaint"><strong>ViewRaiseComplaint</strong></a></li>


            </ul>
        </div>
    </div>
</nav>

<div class="card border-dark container  w-25 mt-5 mb-5 justify-content-center">
    <div class="card-header">
        <h3><b><center>Add Department Form</center></b></h3>
    </div>

    <div class="card-body text-dark">

        <span style="color:green"><h2>${msg}</h2></span>
        <span style="color:red">${error}</span>

        <form action="department-add" method="post">
            <div class="row mb-3">
                <label for="departmentType" class="form-label"><b>Department Name:</b></label>
                <div class="input-group">
                    <span class="input-group-text"></i></span>
                    <input type="text" class="form-control" id="departmentType" name="departmentType" placeholder="Enter department name" value="" required>
                </div>
            </div>


            <div class="mb-3">
                <label for="area" class="form-label"><b>Area:</b></label>
                <div class="input-group">
                    <span class="input-group-text"></i></span>
                    <input type="text" class="form-control" id="area" name="area" placeholder="Enter area" value=""  required>
                </div>
            </div>


        <div class="mb-3">
                <label for="address" class="form-label"><b>Address:</b></label>
                <div class="input-group">
                    <span class="input-group-text"></i></span>
                    <input type="text" class="form-control" id="address" name="address" placeholder="Enter address" value=""  required>
                </div>
            </div>


            <div class="d-flex justify-content-center mt-3">
                <input type="submit" value="SignIn" class="btn btn-info oval-btn bold-text" id="signInButton">
            </div>
        </form>
    </div>
    </div>
</div>
</body>
</html>