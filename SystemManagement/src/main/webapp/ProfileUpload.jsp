<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>System Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-dark bg-info">
    <div class="container-fluid">
        <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <img src="https://www.x-workz.in/Logo.png" alt="xworkz" width="140" height="70" />
                </a>
                <a class="navbar-brand text-dark" href="HomePage"><b>Home</b></a>

            </div>
            <div class="dropdown">
                <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                     <img src="${pageContext.request.contextPath}${sessionScope.profileImage}"  class="rounded-circle profile-image" width="80" height="80" alt="Profile" id="profileImage"
                       onerror="this.onerror=null;this.src='https://img.icons8.com/?size=100&id=c8SsrDOfQgn3&format=png&color=000000';">
                    <!--<img src="https://img.icons8.com/?size=100&id=c8SsrDOfQgn3&format=png&color=000000" alt="" width="60" height="60" style="border-radius=30px"/>-->
                    <!--<img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle"/>-->

                </div>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                    <li><a class="dropdown-item" href="edits?email=${userData.email}"><strong>Edit</strong></a></li>
                    <li><a class="dropdown-item" href="ResetPasswordPage"><strong>Password Reset</strong></a></li>
                    <li><a class="dropdown-item" href="view"><strong>View</strong></a></li>
                    <li><a class="dropdown-item" href="RaiseComplaintPage"><strong>RaiseComplaint</strong></a></li>
                    <li> <a class="dropdown-item" href="view-raiseComplaint?id=${userData.id}" ><strong>ViewRaiseComplaint</strong></a></li>
                    <!--<li> <a class="dropdown-item" href="EditRaiseComplaint" ><strong>EditRaiseComplaint</strong></a></li>-->


                </ul>
            </div>
        </div>
    </nav>


   <!-- <li><a class="dropdown-item" href="view">View</a></li>
                  <li><a class="dropdown-item" href="edits?email=${dto.email}">Edit</a></li>
                  <li><a class="dropdown-item" href="ResetPasswordPage">Reset</a></li>--!>


    <form action="/edit-profile" method="post" enctype="multipart/form-data">
        <div class="container mt-5 mb-5 d-flex justify-content-center">
            <div class="card px-2 mt-5 mb-5 py-2 bg-body shadow mt-5 mb-6 rounded" style="width:40%; padding:30px;">
                <div class="card-body text-center">
                    <!-- Profile Image -->
                 <!--   <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile Image" class="rounded-circle" style="width: 150px; height: 150px; object-fit: cover;">-->
                    <!-- Profile Page Message -->
                    <strong style="color:blue"><h3>${ProfilePageMessage}</h3></strong>
                </div>


                <!-- File upload input -->
              <!--  <div class="mb-3">
                    <label for="file" class="form-label">Upload Profile Image</label>
                    <input class="form-control" type="file" id="file" name="file">
                </div>
                <button type="submit" class="btn btn-primary">Upload</button>---!>
            </div>
        </div>
    </form>
</body>
</html>