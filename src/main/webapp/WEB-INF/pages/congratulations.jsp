<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<title>congratulation page </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


</head>
<body>
<div class="container">
  <h2>Congratulations!!!</h2><p>
  <marquee>Hey Mr/Ms <b> ${employeeDTO.employeeName}</b> you have successfully logged-in:</marquee>        
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Employee Id</th>
        <th>Employee Name</th>
        <th>Email Id</th>
        <th>Password</th>
        <th>Salary</th>
        
        
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${employeeDTO.employeeId}</td>
        <td>${employeeDTO.employeeName}</td>
        <td>${employeeDTO.emailId}</td>
        <td>${employeeDTO.password}</td>
         <td>${employeeDTO.salary}</td>
      </tr>

    </tbody>
  </table>
  <a href="showEmployee">click here to get all the records</a> <p>
  <a href="logout">click here to logout</a>
</div>



</body>
</html>