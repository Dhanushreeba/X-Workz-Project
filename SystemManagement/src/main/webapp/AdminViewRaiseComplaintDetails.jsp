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


             .status-select {
            width: 180px; /* Adjust as needed for desired width */
        }

        .card-size {
            width: 1400px; /* Adjust as needed for desired width */
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
    <div class="card card-size">
        <div class="card-header">
        <form action="searchBy" method="post" >
        <div class="row mb-3">
                                            <span id="complaintTypeError"></span>
                                            <label for="complaintType" class="form-label"><b>Complaint Type:</b></label>
                                            <select class="form-select custom-select-width" id="complaintType" name="complaintType">
                                                <option value="" >Select</option>
                                                <option value="Electric issue">Electric issue</option>
                                                <option value="Water Supply">Water Supply</option>
                                                <option value="Network Problem">Network Problem</option>
                                                <option value="System Problem">System Problem</option>
                                                <option value="Gas Leakage">Gas Leakage</option>
                                            </select>
                                        </div>


                        <div class="form-group">
                            <label for="city">City:</label>
                            <input type="text" class="form-control" id="city" name="city">
                            <span class="error" id="cityError"></span>
                        </div>

                        <button type="submit" class="btn btn-info">Search</button>

                    </form>




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
                    <th>Allocate Department</th>
                    <th>Status</th>
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

                  <form action="update-department" method="post">
                <input type="hidden" name="complaintId" value="${viewRaiseComplaintUsers.complaintId}">

                    <td>
                         <select class="form-select status-select" name="departmentId">
                         <option value="Select">Select</option>
                         <c:forEach var="department" items="${departments}">

    <option value="${department.id}" ${viewRaiseComplaintUsers.departmentDto != null && viewRaiseComplaintUsers.departmentDto.id == department.id ? 'selected' : ''}>${department.departmentType}</option>

                                        </c:forEach>
                                    </select>
                                </td>

         <td>
             <select class="form-select status-select" name="status">
          <option value="Select">Select</option>
   <option value="Pending" ${viewRaiseComplaintUsers.status == 'Pending' ? 'selected' : ''}>Pending</option>
 <option value="In Process" ${viewRaiseComplaintUsers.status == 'In Process' ? 'selected' : ''}>In Process</option>
   <option value="Completed" ${viewRaiseComplaintUsers.status == 'Completed' ? 'selected' : ''}>Completed</option>
  </select>
</td>

<td>
 <button type="submit" class="btn btn-primary">Submit</button>

 </td>
 </form>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
