<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.companymanagment.CompanyDAO" %>
<%@ page import="com.example.companymanagment.Company" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.JobPost.*" %><%--
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
                <img src="${pageContext.request.contextPath}/assets/img/logo/logo.png" alt="">
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
                    <form action="${pageContext.request.contextPath}/editpost?postid=${postid}" method="POST"
                          enctype="multipart/form-data">
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <h5 class="mb-3 text-primary">Post Details</h5>
                            </div>
                            <%
                                String query = request.getQueryString();
                                String url = request.getRequestURL().toString() + query;
                                String idString = request.getParameter("postid");
                                int id = 0;
                                try {
                                    id = Integer.parseInt(idString);
                                } catch (NumberFormatException e) { e.printStackTrace(); }

                                JobPostDAO dao = new JobPostDAO();
                                List<JobCategory> categories = dao.getCategories();
                                List<JobType> types = dao.getJobTypes();
                                Post post = dao.displayPost(id);
                                Salary salary = dao.getSalary(id);
                                JobLocation location = dao.getJobLocation(id);
                                JobDetails details = dao.getJobDetails(id);

                                if(url.contains("error")) {
                            %>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <h5 class="error">${error}</h5>
                            </div>
                            <%
                                }
                                if (url.contains("message")) {
                            %>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <h5 class="success">${message}</h5>
                            </div>
                            <%
                                } // close if statement
                            %>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="fullName">Job Title</label>
                                    <input type="text" name="title" class="form-control" id="fullName"
                                           placeholder="Title" value="<%= post.getTitle() %>">
                                    <input type="hidden" name="postid" value="<%=id%>" />
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="jobcategory">Job Category</label>
                                    <select class="form-select" id="jobcategory" name="category">
                                        <option disabled="disabled" selected="selected" value="">SELECT</option>
                                        <%
                                            for (JobCategory c : categories) {
                                        %>
                                        <option value="<%=c.getId()%>"><%=c.getCategory()%></option>
                                        <%
                                            } // close for loop
                                        %>
<%--                                        <c:forEach items="${categories}" var="category">--%>
<%--                                            <option value="${category.getId()}">${category.getCategory()}</option>--%>
<%--                                        </c:forEach>--%>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="jobtype">Job Type</label>
                                    <select class="form-select" id="jobtype" name="type">
                                        <option disabled="disabled" selected="selected" value="">SELECT</option>
                                        <%
                                            for (JobType t : types) {
                                        %>
                                        <option value="<%=t.getId()%>"><%=t.getJobType()%></option>
                                        <%
                                            } // close for loop
                                        %>
<%--                                        <c:forEach items="${types}" var="type">--%>
<%--                                            <option value="${type.getId()}">${type.getJobType()}</option>--%>
<%--                                        </c:forEach>--%>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="salary">Salary</label>
                                    <input type="text" name="salary" class="form-control" id="salary"
                                           placeholder="Salary" value="<%=salary.getSalary()%>">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="city">City</label>
                                    <input type="text" name="city" class="form-control" id="city" placeholder="City"
                                        value="<%=location.getCity()%>">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="country">Country</label>
                                    <select class="form-select" id="country" name="country">
                                        <option disabled="disabled" selected="selected" value="">SELECT</option>
                                        <option value="Kosovo">Kosovo</option>
                                        <option value="Albania">Albania</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="vacancy">Vacancy</label>
                                    <input type="text" name="vacancy" class="form-control" id="vacancy"
                                           placeholder="Vacancy" value="<%=post.getVacancy()%>">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="description">Job Description</label>
                                    <textarea rows="6" class="form-control" name="description"
                                              placeholder="Job Description (add ; for line break)"
                                              id="description"><%=post.getDescription()%></textarea>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="ksa">Knowledge, skills and abilities</label>
                                    <textarea rows="6" class="form-control" name="ksa"
                                              placeholder="Knowledge, skills and abilites required (add ; for line break)"
                                              id="ksa"><%=details.getKnowledgeSkillAbilities()%></textarea>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="ee">Education and experience</label>
                                    <textarea rows="6" class="form-control" name="ee"
                                              placeholder="Education and experience required (add ; for line break)" id="ee">
                                        <%=details.getEducationExperience()%>
                                    </textarea>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="expires">Expires</label>
                                    <input class="input--style-6" type="date" name="expires" id="expires">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="expires">Logo - 10 MB max.</label>
                                    <input class="input--style-6" type="file" name="file" id="file">
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

