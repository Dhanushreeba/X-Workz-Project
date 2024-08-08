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

                </div>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                    <li> <a class="dropdown-item" href="view-user-raise-complaint"><strong>ViewRaiseComplaint</strong></a></li>
                     <li> <a class="dropdown-item" href="view-user-details"><strong>ViewUserDetail</strong></a></li>
                     <li><a class="dropdown-item" href="ComplaintSearchByAdmin.jsp"><strong>Search</strong></a></li>
                     <li><a class="dropdown-item" href="DepartmentAdding"><strong>Add Department</strong></a></li>
                     <li><a class="dropdown-item" href="RegisterDepartmentAdmin"><strong>Register DepartmentAdmin</strong></a></li>
                     <li> <a class="dropdown-item" href="SubAdminLogin"><strong>SubAdminLogin</strong></a></li>
                     <li> <a class="dropdown-item" href="SubAdminResetPassword"><strong>Reset Password</strong></a></li>

                </ul>
            </div>
        </div>
    </nav>

<span style="color:green"><h3>${AdminProfilePageMessage}</h3></span>

</body>
</html>