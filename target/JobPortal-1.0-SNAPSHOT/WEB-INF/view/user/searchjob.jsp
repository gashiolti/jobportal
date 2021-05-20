<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.JobPost.JobPostDAO" %>
<%@ page import="com.example.JobPost.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.JobPost.JobCategory" %>
<%@ page import="com.example.JobPost.JobType" %>

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
<main>

    <!-- Hero Area Start-->
    <div class="slider-area ">
        <div class="single-slider section-overly slider-height2 d-flex align-items-center"
             data-background="${pageContext.request.contextPath}/assets/img/hero/about.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="hero-cap text-center">
                            <h2>Get your job</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Hero Area End -->
    <!-- Job List Area Start -->
    <div class="job-listing-area pt-120 pb-120">
        <div class="container">
            <div class="row">
                <!-- Left content -->
                <div class="col-xl-3 col-lg-3 col-md-4">
                    <div class="row">
                        <div class="col-12">
                            <div class="small-section-tittle2 mb-45">
                                <div class="ion"> <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        xmlns:xlink="http://www.w3.org/1999/xlink"
                                        width="20px" height="12px">
                                    <path fill-rule="evenodd"  fill="rgb(27, 207, 107)"
                                          d="M7.778,12.000 L12.222,12.000 L12.222,10.000 L7.778,10.000 L7.778,12.000 ZM-0.000,-0.000 L-0.000,2.000 L20.000,2.000 L20.000,-0.000 L-0.000,-0.000 ZM3.333,7.000 L16.667,7.000 L16.667,5.000 L3.333,5.000 L3.333,7.000 Z"/>
                                </svg>
                                </div>
                                <h4>Filter Jobs</h4>
                            </div>
                        </div>
                    </div>
                    <!-- Job Category Listing start -->
                    <form method="POST" action="seachjobs?type=user">
                        <div class="job-category-listing mb-50">
                            <!-- single one -->
                            <div class="single-listing">
                                <div class="small-section-tittle2">
                                    <h4>Job Category</h4>
                                </div>
                                <!-- Select job items start -->
                                <div class="select-job-items2">
                                    <select name="category">
                                        <option disabled="disabled" selected="selected">All Category</option>
                                        <%
                                            JobPostDAO dao = new JobPostDAO();
                                            List<JobCategory> categories = dao.getCategories();
                                            for (JobCategory c : categories) {
                                        %>
                                        <option value="<%=c.getId()%>"><%=c.getCategory()%></option>
                                        <%
                                            } // close foreach loop
                                        %>
                                    </select>
                                </div>
                                <!--  Select job items End-->
                                <!-- select-Categories start -->
                                <div class="select-Categories pt-80 pb-50">
                                    <div class="small-section-tittle2">
                                        <h4>Job Type</h4>
                                    </div>
                                    <div class="select-job-items2">
                                        <select name="job_type">
                                            <option disabled="disabled" selected="selected">All Type</option>
                                            <%
                                                List<JobType> types = dao.getJobTypes();
                                                for (JobType t : types) {
                                            %>
                                            <option value="<%=t.getId()%>"><%=t.getJobType()%></option>
                                            <%
                                                } // close foreach loop
                                            %>
                                        </select>
                                    </div>
                                </div>
                                <!-- select-Categories End -->
                            </div>
                            <!-- single two -->
                            <div class="single-listing" style="margin-top: 30px;">
                                <div class="small-section-tittle2">
                                    <h4>Job Location</h4>
                                </div>
                                <!-- Select job items start -->
                                <div class="select-job-items2">
                                    <select name="location">
                                        <option disabled="disabled" selected="selected">Anywhere</option>
                                        <option value="Kosovo">Kosovo</option>
                                        <option value="Albania">Albania</option>
                                    </select>
                                </div>
                                <!--  Select job items End-->
                                <!-- select-Categories start -->
                                <div class="select-Categories pt-80 pb-50">
                                    <button type="submit" class="button button-contactForm boxed-btn">Search</button>
                                </div>
                                <!-- select-Categories End -->
                            </div>
                            <!-- single three -->
                        </div>
                    </form>
                    <!-- Job Category Listing End -->
                </div>
                <!-- Right content -->
                <div class="col-xl-9 col-lg-9 col-md-8">
                    <!-- Featured_job_start -->
                    <section class="featured-job-area">
                        <div class="container">
                            <!-- Count of Job list Start -->
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="count-job mb-35">
                                        <span>${total} Jobs found</span>
                                        <!-- Select job items start -->
                                        <div class="select-job-items">
                                            <span>Sort by</span>
                                            <form method="POST" action="seachjobs?type=client">
                                                <select name="sortbydate" onchange="this.form.submit()">
                                                    <option disabled="disabled" selected>None</option>
                                                    <option value="date_posted">D. Posted</option>
                                                    <option value="expiration_date">Expiration D. </option>
                                                </select>
                                            </form>
                                        </div>
                                        <!--  Select job items End-->
                                    </div>
                                </div>
                            </div>
                            <!-- Count of Job list End -->
                            <!-- single-job-content -->
                            <%
                                String query = request.getQueryString();
                                String url = request.getRequestURL() + query;
                                if (url.contains("title") && url.contains("location")) {
                            %>
                            <c:forEach items="${posts}" var="post">
                                <div class="single-job-items mb-30">
                                    <div class="job-items">
                                        <div class="company-img">
                                            <a href="${pageContext.request.contextPath}/dispatch?page=postdetails&postid=${post.getId()}">
                                                <img src="imageServlet?postid=${post.getId()}" alt="logo" width="90"
                                                     height="90">
                                            </a>
                                        </div>
                                        <div class="job-tittle job-tittle2">
                                            <a href="${pageContext.request.contextPath}/dispatch?page=postdetails&postid=${post.getId()}">
                                                <h4>${post.title}</h4>
                                                <input type="hidden" name="postid" value="${post.getId()}">
                                            </a>
                                            <ul>
                                                <li>${post.companyName}</li>
                                                <li><i class="fas fa-map-marker-alt"></i>${post.getLocation()}</li>
                                                <li><i class="fas fa-euro-sign"></i>${post.getSalary()}</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="items-link items-link2 f-right">
                                        <a href="${pageContext.request.contextPath}/dispatch?page=postdetails&postid=${post.getId()}">
                                                ${post.getJobType()}
                                        </a>
                                        <span>${post.getPosted()}</span>
                                        <span>${post.getExpires()}</span>
                                    </div>
                                </div>
                            </c:forEach>
                            <%
                            } else {
                            %>
                            <c:forEach items="${posts}" var="post">
                                <div class="single-job-items mb-30">
                                    <div class="job-items">
                                        <div class="company-img">
                                            <a href="${pageContext.request.contextPath}/dispatch?page=postdetails&postid=${post.getId()}">
                                                <img src="imageServlet?postid=${post.getId()}" alt="logo" width="90"
                                                     height="90">
                                            </a>
                                        </div>
                                        <div class="job-tittle job-tittle2">
                                            <a href="${pageContext.request.contextPath}/dispatch?page=postdetails&postid=${post.getId()}">
                                                <h4>${post.title}</h4>
                                                <input type="hidden" name="postid" value="${post.getId()}">
                                            </a>
                                            <ul>
                                                <li>${post.companyName}</li>
                                                <li><i class="fas fa-map-marker-alt"></i>${post.getLocation()}</li>
                                                <li><i class="fas fa-euro-sign"></i>${post.getSalary()}</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="items-link items-link2 f-right">
                                        <a href="${pageContext.request.contextPath}/dispatch?page=postdetails&postid=${post.getId()}">
                                                ${post.getJobType()}
                                        </a>
                                        <span>${post.getPosted()}</span>
                                        <span>${post.getExpires()}</span>
                                    </div>
                                </div>
                            </c:forEach>
                            <%
                                } // close else statement
                            %>
                        </div>
                    </section>
                    <!-- Featured_job_end -->
                </div>
            </div>
        </div>
    </div>
    <!-- Job List Area End -->
    <!--Pagination Start  -->
    <div class="pagination-area pb-115 text-center">
        <div class="container">
            <div class="row">
                <div class="col-xl-12">
                    <div class="single-wrap d-flex justify-content-center">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-start">
                                <li class="page-item active"><a class="page-link" href="#">01</a></li>
                                <li class="page-item"><a class="page-link" href="#">02</a></li>
                                <li class="page-item"><a class="page-link" href="#">03</a></li>
                                <li class="page-item"><a class="page-link" href="#"><span class="ti-angle-right"></span></a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Pagination End  -->

</main>

<jsp:include page="footer.jsp" />

<!-- JS here -->

<jsp:include page="footer-js.jsp" />

</body>
</html>