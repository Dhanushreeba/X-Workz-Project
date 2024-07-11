//package com.xworkz.project.model.service;
//
//public class Trail {
//
//
//
//    @Override
//    public void incrementFailedAttempts(String email) {
//
//        SignUpDTO user = mailRepo.findByEmail(email);
//        if (user != null) {
//            int attempts = user.getFailedAttempt() + 1;
//            user.setFailedAttempt(attempts);
//            if (attempts >= 3) {
//                user.setAccountLocked(true);
//            }
//            mailRepo.update(user);
//        }
//
//    }
//
//    @Override
//    public int getFailedAttempts(String email)
//    {
//        SignUpDTO user = mailRepo.findByEmail(email);
//        return (user != null) ? user.getFailedAttempt() : 0;
//    }
//
//
//    @Override
//    public void resetFailedAttempts(String email)
//    {
//
//        SignUpDTO user = mailRepo.findByEmail(email);
//        if (user != null)
//        {
//            user.setFailedAttempt(0); //false
//            mailRepo.update(user);
//
//
//        }
//    }
//
//
//    @Override
//    public void lockAccount(String email) {
//        SignUpDTO user = mailRepo.findByEmail(email);
//        if (user != null) {
//            user.setAccountLocked(true);
//            mailRepo.update(user);
//        }
//    }
//
//
//    //to unlock account when i new password generate
//    @Override
//    public void unlockAccount(String email) {
//        SignUpDTO user = mailRepo.findByEmail(email);
//        if (user != null) {
//            user.setAccountLocked(false);
//            mailRepo.update(user);
//        }
//    }
//}


//pruthvi
//@Override
//public SignUpDTO findByEmail(String email) {
//    EntityManager entityManager = entityManagerFactory.createEntityManager();
//    try {
//        Query query = entityManager.createQuery("select c from SignUpDTO c where email=:email");
//        query.setParameter("email", email);
//
//        List<SignUpDTO> resultList = query.getResultList();
//        if (resultList.isEmpty()) {
//            return null;
//        } else if (resultList.size() == 1) {
//            return resultList.get(0);
//        } else {
//            throw new NonUniqueResultException("Multiple results found for email: " + email);
//        }
//    } catch (NoResultException e) {
//        return null;
//    } finally {
//        entityManager.close();
//    }
//
//}
//
//@Override
//public boolean update(SignUpDTO signUpDto) {
//    EntityManager entityManager = entityManagerFactory.createEntityManager();
//    EntityTransaction tx = entityManager.getTransaction();
//
//    try {
//        tx.begin();
//        entityManager.merge(signUpDto);
//        tx.commit();
//    } catch (PersistenceException persistenceException) {
//        persistenceException.printStackTrace();
//        tx.rollback();
//        return false;
//    } finally {
//        entityManager.close();
//    }
//    return true;
//}

//@PostMapping("sign-in")
//public String signIn(SignUpDTO signUpDTO, @RequestParam String email, @RequestParam String password, Model model) {
//    System.out.println("signIn method is running...");
//
//
//    System.out.println("Email: " + email);
//    System.out.println("Password: " + password);
//
//    SignUpDTO signUpDTO1 = mailService.findByEmailAndPassword(email, password);
//    if (signUpDTO1 != null)
//
//    {
//
//        mailService.resetFailedAttempts(email);
//        model.addAttribute("wlcm", "Sign_In successful.Welcome, " + signUpDTO1.getFirstName());
//        //return "WelcomePage";
//        model.addAttribute("ProfilePageMessage", "Welcome To Issue Management System, " + signUpDTO1.getFirstName());
//        return "Profile";
//    }
//    else
//
//    {
//        mailService.incrementFailedAttempts(email);
//        int failedAttempts = mailService.getFailedAttempts(email);
//        System.out.println("Failed attempts for " + email + ": " + failedAttempts);
//
//
//        if (failedAttempts >= 3) {
//            mailService.lockAccount(email); // Lock account after 3 failed attempts
//            System.out.println(email + " :Your account is locked due to too may failed attempts");
//            model.addAttribute("error", "Your account is locked due to too many failed attempts.");
//            model.addAttribute("accountLocked", true);
//        }accountLocked
//
//        else
//
//        {
//
//            model.addAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
//            System.out.println("Invalid email Id and password");
//            model.addAttribute("accountLocked", false);
//        }
//        return "SignIn";
//    }
//}

