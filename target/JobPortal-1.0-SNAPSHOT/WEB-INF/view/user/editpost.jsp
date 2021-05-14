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
        <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
                <div class="card-body">
                    <div class="account-settings">
                        <div class="user-profile">
                            <div class="user-avatar">
                                <i class="fas fa-building fa-4x"></i>
                            </div>
                            <h5 class="user-name">Company Name</h5>
                            <h6 class="user-email">Email</h6>
                        </div>
                        <div class="about">
                            <h5 class="mb-2 text-primary">About</h5>
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
                                    <input type="text" class="form-control" id="fullName" placeholder="Title">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="jobcategory">Job Type</label>
                                    <select class="form-select" id="jobcategory" name="category">
                                        <option disabled="disabled" selected="selected" value="">SELECT</option>
                                        <option value="">Teknologji Informative</option>
                                        <option value="">Call Center</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="jobtype">Job Type</label>
                                    <select class="form-select" id="jobtype" name="category">
                                        <option disabled="disabled" selected="selected" value="">SELECT</option>
                                        <option value="">Full Time</option>
                                        <option value="">Part Time</option>
                                        <option value="">Remote</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="salary">Salary</label>
                                    <input type="text" class="form-control" id="salary" placeholder="Website url">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="city">City</label>
                                    <input type="text" class="form-control" id="city" placeholder="Address">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="country">Country</label>
                                    <select class="form-select" id="country" name="category">
                                        <option disabled="disabled" selected="selected" value="">SELECT</option>
                                        <option value="">Kosovo</option>
                                        <option value="">Albania</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="vacancy">Vacancy</label>
                                    <input type="text" class="form-control" id="vacancy" placeholder="vacancy">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="description">Job Description</label>
                                    <textarea rows="6" class="form-control" name="description"
                                              placeholder="Job Description" id="description"></textarea>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="ksa">Knowledge, skills and abilities</label>
                                    <textarea rows="6" class="form-control" name="ksa"
                                              placeholder="Knowledge, skills and abilites required" id="ksa"></textarea>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="ee">Education and experience</label>
                                    <textarea rows="6" class="form-control" name="ee"
                                              placeholder="Education and experience required" id="ee"></textarea>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="expires">Expires</label>
                                    <input class="input--style-6" type="date" name="expires" id="expires">
                                </div>
                            </div>
                        </div>
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <a href="${pageContext.request.contextPath}/dispatch?page=home">
                                        <button type="button"  name="submit" class="btn btn-secondary">Cancel</button>
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

