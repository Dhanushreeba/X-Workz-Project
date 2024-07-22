<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>View Raised Complaints</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</head>
<body>
<nav class="navbar navbar-dark bg-info">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <a class="navbar-brand text-dark" href="HomePage"><b>Home</b></a>
            <a class="navbar-brand text-dark" href="SignIn"><b>SignIn</b></a>
        </div>

        <!-- Display profile image when user is signed in -->
         <div class="dropdown">
             <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="80" height="80" class="rounded-circle profile-image" alt="Profile Image" id="profileImage">

                </div>

                 <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                        <li> <a class="dropdown-item" href="view-raiseComplaint?id=${userData.id}" ><strong>ViewRaiseComplaint</strong></a></li>

                              </ul>
                 </div>

    </div>
</nav>

<div class="container mt-5 mb-5">
    <div class="card">
        <div class="card-header">
            <h3><b>View Raised Complaint</b></h3>
        </div>
        <div class="card-body">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Serial Number</th>
                        <th>ID</th>
                        <th>Complaint Type</th>
                        <th>Country</th>
                        <th>State</th>
                        <th>City</th>
                        <th>Area</th>
                        <th>Address</th>
                        <th>Description</th>
                        <th>Edit</th>


                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ViewUserRaisedComplaint" items="${ViewUserRaisedComplaints}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${ViewUserRaisedComplaint.complaintId}</td>
                            <td>${ViewUserRaisedComplaint.complaintType}</td>
                            <td>${ViewUserRaisedComplaint.country}</td>
                            <td>${ViewUserRaisedComplaint.state}</td>
                            <td>${ViewUserRaisedComplaint.city}</td>
                            <td>${ViewUserRaisedComplaint.area}</td>
                            <td>${ViewUserRaisedComplaint.address}</td>
                            <td>${ViewUserRaisedComplaint.discription}</td>



                           <!--<td><a href="edit-complaint/${ViewUserRaisedComplaint.complaintId}">Edit</a></td>-->

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
