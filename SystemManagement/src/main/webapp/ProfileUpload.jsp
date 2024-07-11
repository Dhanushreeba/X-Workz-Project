<%@ page  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>System Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
            .nav-item img {
                border-radius: 20px;
            }
            .dropdown-toggle::after {
                margin-left: 0;
            }
        </style>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>

<body>
              <nav class="navbar navbar-dark bg-info">
                <div class="container-fluid">

<div>
                <!-- Add your logo here -->
                <span><a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz"
                    width="120" height="70"></a></span>
                    <span><a class="navbar-brand text-dark" href="index.jsp"><b>Home</b></a>
                 </span>
                 </div>
                <!-- End of logo -->

                <div>
                <div class="dropdown">

             <!--     <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin-left=500px">
                      <img src="https://www.pexels.com/photo/green-and-blue-peacock-feather-674010/" class="d-flex" alt="" width=40 height=40   style="border-radius=20px"/>
                  </button>
                  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                  </div>
                </div> -->

 <div class="row">
             <div class="col-12">
                 <ul class="nav nav-tabs">
                     <li class="nav-item dropdown d-flex align-items-down">
                         <a class="nav-link dropdown-toggle mr-1" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"></a>
                         <div class="dropdown-menu dropdown-menu-left">
                             <a class="dropdown-item" href="view">View</a>
                             <a class="dropdown-item" href="edits">Edit</a>
                             <a class="dropdown-item" href="ResetPassword.jsp">Reset</a>
                         </div>
                         <img src="https://img.icons8.com/?size=100&id=c8SsrDOfQgn3&format=png&color=000000" alt="" width="60" height="60" style="border-radius=30px"/>
                     </li>
                 </ul>
             </div>
         </div>


            </nav>

            <div>
                 <form action="profile" method="post">
                  <div class="container mt-5 mb-5 d-flex justify-content-center">
                  <div class="card px-2 mt-5 mb-5 py-2 bg-body shadow mt-5 mb-6 rounded" style="width:40%; padding:30px;">
                  <div class="card-body">
                           <strong style="color:blue"><h3>${ProfilePageMessage}</h3></strong>
                  </div>
                     </div>
                          </div>

                  <Center><h3><b>Welcome</b></h3></Center>

         </form>

         </div>

</body>
</html>

