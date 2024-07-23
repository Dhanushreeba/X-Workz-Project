<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Complaint</title>
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

        </div>
    </div>
</nav>

<div class="container mt-5 mb-5 d-flex justify-content-center">
    <div class="card p-4">
          <div class="card-body">
            <h3><b>Edit Complaint</b></h3>
        </div>
        <div class="card-body">

             <form action="${pageContext.request.contextPath}/update-complaint" method="post">

                                <span style="color:green"><h4>${updateMsg}</h4></span>

                                 <span style="color:red"><h4>${updateErrorMsg}</h4></span>
                <input type="hidden" name="complaintId" value="${raiseComplaintDto.complaintId}"/>




                <div class="row mb-3">
                    <label for="complaintType" class="form-label">Complaint Type</label>
                    <input type="text" class="form-control" id="complaintType" name="complaintType" value="${raiseComplaintDto.complaintType}" readonly/>
                </div>

                <div class="row mb-3">
                    <label for="country" class="form-label">Country</label>
                    <input type="text" class="form-control" id="country" name="country" value="${raiseComplaintDto.country}" readonly>
                </div>

                <div class="row mb-3">
                    <label for="state" class="form-label">State</label>
                    <input type="text" class="form-control" id="state" name="state" value="${raiseComplaintDto.state}" readonly>
                </div>

                <div class="row mb-3">
                    <label for="city" class="form-label">City</label>
                    <input type="text" class="form-control" id="city" name="city" value="${raiseComplaintDto.city}" readonly>
                </div>

                <div class="row mb-3">
                    <label for="area" class="form-label">Area</label>
                    <input type="text" class="form-control" id="area" name="area" value="${raiseComplaintDto.area}" readonly>
                </div>

                <div class="row mb-3">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="address" name="address" value="${raiseComplaintDto.address}" readonly>
                </div>

                <div class="row mb-3">
                    <label for="discription" class="form-label">Description</label>
                    <textarea class="form-control" id="discription" name="discription" rows="3" required>${raiseComplaintDto.discription}</textarea>
                </div>

                <button type="submit" class="btn btn-primary">Update</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
