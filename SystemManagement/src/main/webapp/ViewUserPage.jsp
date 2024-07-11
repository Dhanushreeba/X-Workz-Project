<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Profile View</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-info bg-info">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
                </a>
                <a class="navbar-brand text-dark" href="index.jsp"><b>Home</b></a>
                <a class="navbar-brand text-dark" href="ProfileUpload.jsp"><b>Profile</b></a>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h2>Profile</h2>
        <div class="card">
            <div class="card-body">
                <strong class="card-title">Name: ${dto.firstName} ${dto.lastName}</strong><br><br>
                <p class="card-text"><strong>Email: ${dto.email}</strong></p>
                <p class="card-text"><strong>Contact Number: ${dto.contactNumber}</strong></p>
                <p class="card-text"><strong>Alternate Number: ${dto.alternateNumber}</strong></p>
                <p class="card-text"><strong>Address: ${dto.address}</strong></p>
            </div>
        </div>
    </div>
</body>
</html>






