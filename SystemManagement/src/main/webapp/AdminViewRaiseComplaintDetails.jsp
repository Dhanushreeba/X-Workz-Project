<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">

     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>


<!--This for to adjust drop down button color!-->
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
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

                    <li><a class="dropdown-item" href="view-user-details"><strong>ViewUserDetails</strong></a></li>

                    </ul>
                </div>

    </div>
</nav>

<div class="container mt-5 mb-5">
    <div class="card">
        <div class="card-header">
            <h3><b>View User Complaint Details </b></h3>
        </div>
        <div class="card-body">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Serial Number</th>
                    <th>ID</th>
                    <th>Complaint Type</th>
                    <th>Country</th>
                    <th>State </th>
                    <th>City </th>
                    <th>Area</th>
                    <th>Address</th>
                    <th>Discription</th>


                </tr>
                </thead>
                <tbody>
                <c:forEach var="viewRaiseComplaintUsers" items="${viewRaiseComplaint}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${viewRaiseComplaintUsers.complaintId}</td>
                        <td>${viewRaiseComplaintUsers.complaintType}</td>
                        <td>${viewRaiseComplaintUsers.country}</td>
                        <td>${viewRaiseComplaintUsers.state}</td>
                        <td>${viewRaiseComplaintUsers.city}</td>
                        <td>${viewRaiseComplaintUsers.area}</td>
                        <td>${viewRaiseComplaintUsers.address}</td>
                        <td>${viewRaiseComplaintUsers.discription}</td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
