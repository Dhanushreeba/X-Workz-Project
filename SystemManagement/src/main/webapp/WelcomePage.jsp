<%@ page  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>System Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<script>
</script>
</head>
<body>
<nav class="navbar navbar-dark bg-info">
    <div class="container-fluid">
        <div class="navbar-header">

        <!-- Add your logo here -->
                                    <a class="navbar-brand" href="#">
                                        <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70" >
                                    </a>
                                    <!-- End of logo -->


        <a class="navbar-brand  text-dark" href="index.jsp"><b>Home</b></a>
        <a class="navbar-brand  text-dark" href="SignUp.jsp"><b>SignUp</b></a>
        <a class="navbar-brand  text-dark" href="SignIn.jsp"><b>SignIn</b></a>
        <a class="navbar-brand  text-dark" href="ForgetPassword.jsp"><b>ForgetPassword</b></a>

  </div>
</nav>

<span style="color:green">${msg}</span>

                    <div class="container mt-5 mb-200">
                    <div class="card">

                    </div>
                    </div>


</body>
</html>

