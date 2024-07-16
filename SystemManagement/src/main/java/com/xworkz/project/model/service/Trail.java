////package com.xworkz.project.model.service;
////
////public class Trail {
////
////
////
////    @Override
////    public void incrementFailedAttempts(String email) {
////
////        SignUpDTO user = mailRepo.findByEmail(email);
////        if (user != null) {
////            int attempts = user.getFailedAttempt() + 1;
////            user.setFailedAttempt(attempts);
////            if (attempts >= 3) {
////                user.setAccountLocked(true);
////            }
////            mailRepo.update(user);
////        }
////
////    }
////
////    @Override
////    public int getFailedAttempts(String email)
////    {
////        SignUpDTO user = mailRepo.findByEmail(email);
////        return (user != null) ? user.getFailedAttempt() : 0;
////    }
////
////
////    @Override
////    public void resetFailedAttempts(String email)
////    {
////
////        SignUpDTO user = mailRepo.findByEmail(email);
////        if (user != null)
////        {
////            user.setFailedAttempt(0); //false
////            mailRepo.update(user);
////
////
////        }
////    }
////
////
////    @Override
////    public void lockAccount(String email) {
////        SignUpDTO user = mailRepo.findByEmail(email);
////        if (user != null) {
////            user.setAccountLocked(true);
////            mailRepo.update(user);
////        }
////    }
////
////
////    //to unlock account when i new password generate
////    @Override
////    public void unlockAccount(String email) {
////        SignUpDTO user = mailRepo.findByEmail(email);
////        if (user != null) {
////            user.setAccountLocked(false);
////            mailRepo.update(user);
////        }
////    }
////}
//
//
////pruthvi
////@Override
////public SignUpDTO findByEmail(String email) {
////    EntityManager entityManager = entityManagerFactory.createEntityManager();
////    try {
////        Query query = entityManager.createQuery("select c from SignUpDTO c where email=:email");
////        query.setParameter("email", email);
////
////        List<SignUpDTO> resultList = query.getResultList();
////        if (resultList.isEmpty()) {
////            return null;
////        } else if (resultList.size() == 1) {
////            return resultList.get(0);
////        } else {
////            throw new NonUniqueResultException("Multiple results found for email: " + email);
////        }
////    } catch (NoResultException e) {
////        return null;
////    } finally {
////        entityManager.close();
////    }
////
////}
////
////@Override
////public boolean update(SignUpDTO signUpDto) {
////    EntityManager entityManager = entityManagerFactory.createEntityManager();
////    EntityTransaction tx = entityManager.getTransaction();
////
////    try {
////        tx.begin();
////        entityManager.merge(signUpDto);
////        tx.commit();
////    } catch (PersistenceException persistenceException) {
////        persistenceException.printStackTrace();
////        tx.rollback();
////        return false;
////    } finally {
////        entityManager.close();
////    }
////    return true;
////}
//
////@PostMapping("sign-in")
////public String signIn(SignUpDTO signUpDTO, @RequestParam String email, @RequestParam String password, Model model) {
////    System.out.println("signIn method is running...");
////
////
////    System.out.println("Email: " + email);
////    System.out.println("Password: " + password);
////
////    SignUpDTO signUpDTO1 = mailService.findByEmailAndPassword(email, password);
////    if (signUpDTO1 != null)
////
////    {
////
////        mailService.resetFailedAttempts(email);
////        model.addAttribute("wlcm", "Sign_In successful.Welcome, " + signUpDTO1.getFirstName());
////        //return "WelcomePage";
////        model.addAttribute("ProfilePageMessage", "Welcome To Issue Management System, " + signUpDTO1.getFirstName());
////        return "Profile";
////    }
////    else
////
////    {
////        mailService.incrementFailedAttempts(email);
////        int failedAttempts = mailService.getFailedAttempts(email);
////        System.out.println("Failed attempts for " + email + ": " + failedAttempts);
////
////
////        if (failedAttempts >= 3) {
////            mailService.lockAccount(email); // Lock account after 3 failed attempts
////            System.out.println(email + " :Your account is locked due to too may failed attempts");
////            model.addAttribute("error", "Your account is locked due to too many failed attempts.");
////            model.addAttribute("accountLocked", true);
////        }accountLocked
////
////        else
////
////        {
////
////            model.addAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
////            System.out.println("Invalid email Id and password");
////            model.addAttribute("accountLocked", false);
////        }
////        return "SignIn";
////    }
////}
//
//
//
//<%@ page isELIgnored="false"%>
//<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
//<!DOCTYPE html>
//<html>
//<head>
//    <meta charset="ISO-8859-1">
//<title>Student Form</title>
//    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
//    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
//    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
//</head>
//<body>
//    <nav class="navbar navbar-dark bg-dark">
//        <div class="container-fluid">
//            <div class="navbar-header">
//                <a class="navbar-brand" href="#">
//                    <img src="https://www.x-workz.in/Logo.png" alt="xworkz" width="140" height="70" />
//                </a>
//                <a class="navbar-brand text-white" href="index.jsp"><b>Home</b></a>
//            </div>
//            <div class="dropdown">
//                <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
//
//                    <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle">
//
//                </div>
//                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
//                    <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit</strong></a></li>
//                    <li><a class="dropdown-item" href="PasswordReset.jsp"><strong>Password Reset</strong></a></li>
//                    <li><a class="dropdown-item" href="view-profile"><strong>View</strong></a></li>
//                </ul>
//            </div>
//        </div>
//    </nav>
//
//    <form action="/edit-profile" method="post" enctype="multipart/form-data">
//        <div class="container mt-5 mb-5 d-flex justify-content-center">
//            <div class="card px-2 mt-5 mb-5 py-2 bg-body shadow mt-5 mb-6 rounded" style="width:40%; padding:30px;">
//                <div class="card-body text-center">
//                    <!-- Profile Image -->
//                 <!--   <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile Image" class="rounded-circle" style="width: 150px; height: 150px; object-fit: cover;">--!>
//                    <!-- Profile Page Message -->
//                    <strong style="color:blue"><h3>${ProfilePageMessage}</h3></strong>
//                </div>
//
//
//                <!-- File upload input -->
//              <!--  <div class="mb-3">
//                    <label for="file" class="form-label">Upload Profile Image</label>
//                    <input class="form-control" type="file" id="file" name="file">
//                </div>
//                <button type="submit" class="btn btn-primary">Upload</button>---!>
//            </div>
//        </div>
//    </form>
//</body>
//</html>