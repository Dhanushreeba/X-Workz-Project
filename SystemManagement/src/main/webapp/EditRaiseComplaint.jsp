
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Complaint</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
        </div>
    </div>
</nav>

<div class="container mt-5 mb-5">
    <div class="card">
        <div class="card-header">
            <h3><b>Edit Complaint</b></h3>
        </div>
        <div class="card-body">
            <form action="edit-complaint" method="post">
                <input type="hidden" name="complaintId" value="${raiseComplaintDto.complaintId}"/>

                <div class="mb-3">
                    <label for="complaintType" class="form-label">Complaint Type</label>
                    <input type="text" class="form-control" id="complaintType" name="complaintType" value="${raiseComplaintDTO.complaintType}" readonly/>
                </div>

                <div class="mb-3">
                    <label for="country" class="form-label">Country</label>
                    <input type="text" class="form-control" id="country" name="country" value="${raiseComplaintDTO.country}" readonly>
                </div>

                <div class="mb-3">
                    <label for="state" class="form-label">State</label>
                    <input type="text" class="form-control" id="state" name="state" value="${raiseComplaintDTO.state}" readonly>
                </div>

                <div class="mb-3">
                    <label for="city" class="form-label">City</label>
                    <input type="text" class="form-control" id="city" name="city" value="${raiseComplaintDTO.city}" readonly>
                </div>

                <div class="mb-3">
                    <label for="area" class="form-label">Area</label>
                    <input type="text" class="form-control" id="area" name="area" value="${raiseComplaintDTO.area}" readonly>
                </div>

                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="address" name="address" value="${raiseComplaintDTO.address}" readonly>
                </div>

                <div class="mb-3">
                    <label for="discription" class="form-label">Description</label>
                    <textarea class="form-control" id="discription" name="discription" rows="3" required>${raiseComplaintDTO.description}</textarea>
                </div>

                <button type="submit" class="btn btn-primary">Update</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
