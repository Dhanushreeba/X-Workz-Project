<!--<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>System Raise Complaint Form</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

</head>

<body>
<nav class="navbar navbar-dark bg-info">
    <div class="container-fluid">
        <div class="navbar-header">
                <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <a class="navbar-brand text-dark" href="HomePage"><b>Home</b></a>
            <a class="navbar-brand text-dark" href="Admin"><b>Admin</b></a>
        </div>

<div class="dropdown">
                <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            </div>

                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

                    <li> <a class="dropdown-item" href="view-user-raise-complaint" ><strong>ViewRaiseComplaint</strong></a></li>
                    <li><a class="dropdown-item" href="HomePage"><strong>Logout</strong></a></li>
                     <li><a class="dropdown-item" href="ComplaintSearchByAdmin"><strong>Search</strong></a></li>

                </ul>
            </div>
    </div>
</nav>

<div class="container mt-5 mb-5">
    <div class="card">
        <div class="card-header">
            <h2> ViewRaiseComplaint Form</h2>
            <c:if test="${!empty errors}">
                <div class="alert alert-danger">
                    <c:forEach items="${errors}" var="objectError">
                        ${objectError.defaultMessage}<br>
                    </c:forEach>
                </div>
            </c:if>
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
                                        <option value="Water Problem">Water Problem</option>
                                    </select>
                                </div>


                <div class="form-group">
                    <label for="city">City:</label>
                    <input type="text" class="form-control" id="city" name="city">
                    <span class="error" id="cityError"></span>
                </div>

                <button type="submit" class="btn btn-info">Search</button>

            </form>
        </div>
    </div>
</div>
<div class="container mt-3" id="searchResults">
    <div class="card">
        <div class="card-body">
            <c:if test="${!empty message}">
                <strong style="color:green;">Search result for: ${message}</strong>

            </c:if>
            <strong style="color:red;">${msg}</strong>
            <table class="table">
                <thead>
                <tr>
                     <th scope="col">ID</th>
                     <th scope="col">Complaint Type</th>
                     <th scope="col">Country</th>
                     <th scope="col">State</th>
                     <th scope="col">City</th>
                    <th scope="col">Area </th>
                    <th scope="col">Address</th>
                    <th scope="col">Description</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${raiseComplaint}" var="raiseComplaint">
                    <tr>
                        <td>${raiseComplaint.complaintId}</td>
                        <td>${raiseComplaint.complaintType}</td>
                        <td>${raiseComplaint.country}</td>
                        <td>${raiseComplaint.state}</td>
                      <td>${raiseComplaint.city}</td>
                       <td>${raiseComplaint.area}</td>
                       <td>${raiseComplaint.address}</td>
                  <td>${raiseComplaint.discription}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>--!>