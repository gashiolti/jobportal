<%@ page import="com.example.companymanagment.CompanyDAO" %>
<%@ page import="com.example.companymanagment.Company" %><%--
  Created by IntelliJ IDEA.
  User: gashi
  Date: 5/11/2021
  Time: 1:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />

<body>

    <!-- Preloader Start -->
    <div id="preloader-active">
        <div class="preloader d-flex align-items-center justify-content-center">
            <div class="preloader-inner position-relative">
                <div class="preloader-circle"></div>
                <div class="preloader-img pere-text">
                    <img src="${pageContext.request.contextPath}/pages/assets/img/logo/logo.png" alt="">
                </div>
            </div>
        </div>
    </div>
    <!-- Preloader Start -->

    <jsp:include page="nav.jsp" />


    <!-- DISPLAY PROFILE -->
    <div class="container" id="profile-container" style="margin-top: 80px; margin-bottom: 80px;">
        <div class="row gutters">
            <%
                int userid = Integer.parseInt(request.getParameter("userid"));
                int id = (int) session.getAttribute("userid");
                CompanyDAO dao = new CompanyDAO();

                Company company = dao.getCompany(id);
            %>
            <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                <div class="card h-100">
                    <div class="card-body">
                        <div class="account-settings">
                            <div class="user-profile">
                                <div class="user-avatar">
                                    <i class="fas fa-building fa-4x"></i>
                                </div>
                                <h5 class="user-name"><%=company.getName()%></h5>
                                <h6 class="user-email"><%=company.getEmail()%></h6>
                            </div>
                            <div class="about">
                                <h5 class="mb-2 text-primary">About</h5>
                                <%
                                    if(company.getDescription() != null) {
                                %>
                                <p><%=company.getDescription()%></p>
                                <%
                                    } // close if statement
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                <div class="card h-100">
                    <div class="card-body">
                        <form method="POST">
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <h5 class="mb-3 text-primary">Personal Details</h5>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="fullName">Company Name</label>
                                        <input type="text" class="form-control" id="fullName" placeholder="Company Name"
                                        value="<%=company.getName()%>">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="eMail">Email</label>
                                        <input type="email" class="form-control" id="eMail" placeholder="Enter Email"
                                        value="<%=company.getEmail()%>">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="phone">Phone Number</label>
                                        <%
                                            if(company.getPhoneNr() == null) {
                                        %>
                                        <input type="text" class="form-control" id="phone" placeholder="Enter phone number">
                                        <%
                                            } else {
                                        %>
                                        <input type="text" class="form-control" id="phone" placeholder="Enter phone number"
                                        value="<%=company.getPhoneNr()%>">
                                        <%
                                            } // close else statement
                                        %>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="website">Website URL</label>
                                        <input type="url" class="form-control" id="website" placeholder="Website url"
                                        value="<%=company.getWebUrl()%>">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="address">Address</label>
                                        <input type="text" class="form-control" id="address" placeholder="Address"
                                        value="<%=company.getAddress()%>">
                                    </div>
                                </div>
                            </div>
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <h5 class="mb-3 text-primary">Description</h5>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="description">Company Description</label>
                                        <%
                                            if(company.getDescription() == null) {
                                        %>
                                        <textarea rows="6" class="form-control" name="description"
                                                  placeholder="Company Description" id="description"></textarea>
                                        <%
                                            } else {
                                        %>
                                        <textarea rows="6" class="form-control" name="description"
                                                  placeholder="Company Description" id="description">
                                            <%=company.getDescription()%>
                                        </textarea>
                                        <%
                                            } // close else statement
                                        %>
                                    </div>
                                </div>
                            </div>
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <h5 class="mb-3 text-primary">Password</h5>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="password" class="form-control" id="password"
                                               placeholder="Password">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="confirmPass">Confirm Password</label>
                                        <input type="password" class="form-control" id="confirmPass"
                                               placeholder="Confirm Password">
                                    </div>
                                </div>
                            </div>
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="text-right">
                                        <a href="${pageContext.request.contextPath}/dispatch?page=home">
                                            <button type="button" name="submit" class="btn btn-secondary">Cancel</button>
                                        </a>
                                        <button type="submit"  name="submit" class="btn btn-primary">Update</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <jsp:include page="footer.jsp" />

    <jsp:include page="footer-js.jsp" />

</body>
</html>

