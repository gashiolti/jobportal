<%@ page import="com.example.companymanagment.CompanyDAO" %>
<%@ page import="com.example.companymanagment.Company" %>
<%@ page import="com.example.JobPost.JobPostDAO" %>
<%@ page import="com.example.JobPost.Post" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

    <!-- Fontfaces CSS-->
    <link href="${pageContext.request.contextPath}/pages/admin/css/font-face.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/pages/admin/vendor/mdi-font/css/material-design-iconic-font.min.css"
          rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="${pageContext.request.contextPath}/pages/admin/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="${pageContext.request.contextPath}/pages/admin/css/theme.css" rel="stylesheet" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/admin/css/custom-style.css">


    <!-- CSS here -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/price_rangs.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/slicknav.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/animate.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/magnific-popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/slick.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/nice-select.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/style.css">



</head>

    <body >
       
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


        <header>
            <!-- Header Start -->
            <div class="header-area header-transparrent">
                <div class="headder-top header-sticky">
                    <div class="container">
                        <div class="row align-items-center">
                            <div class="col-lg-3 col-md-2">
                                <!-- Logo -->
                                <div class="logo">
                                    <a href="${pageContext.request.contextPath}/dispatch?page=home"><img
                                            src="${pageContext.request.contextPath}/pages/assets/img/logo/logo.png"
                                            alt=""></a>
                                </div>
                            </div>
                            <div class="col-lg-9 col-md-9">
                                <div class="menu-wrapper">
                                    <!-- Main-menu -->
                                    <div class="main-menu">
                                        <nav class="d-none d-lg-block">
                                            <ul id="navigation">
                                                <li><a href="${pageContext.request.contextPath}/dispatch?page=home">Home</a></li>
                                                <li><a href="job_listing.html">Find a Jobs </a></li>
                                                <li><a href="about.html">About</a></li>
                                                <li><a href="#">Page</a>
                                                    <ul class="submenu">
                                                        <li><a href="${pageContext.request.contextPath}/dispatch?page=profile&userid=${userid}">
                                                            Profile</a></li>
                                                        <li><a href="${pageContext.request.contextPath}/dispatch?page=myposts&userid=${userid}">
                                                            My Posts</a></li>
                                                    </ul>
                                                </li>
                                                <li><a href="contact.html">Contact</a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                    <!-- Header-btn -->
                                    <div class="header-btn d-none f-right d-lg-block">
                                        <a href="${pageContext.request.contextPath}/dispatch?page=postjob"
                                           class="btn head-btn1">Post Job</a>
                                        <a href="${pageContext.request.contextPath}/logout" class="btn head-btn1">
                                            Logout</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Mobile Menu -->
                            <div class="col-12">
                                <div class="mobile_menu d-block d-lg-none"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header End -->
        </header>


                <div class="main-content col-lg-10" style="margin: auto; margin-bottom: 150px;">
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">
                            <div class="row m-t-30">
                                <div class="col-md-12">
                                    <!-- DATA TABLE-->

                                    <div class="table-responsive m-b-40">
                                        <h3 class="title-5 m-b-35">my posts</h3>

                                        <%
                                            int userid = 0;
                                            try {
                                                userid = Integer.parseInt(request.getParameter("userid"));
                                            } catch (NumberFormatException e) {}

                                            JobPostDAO dao = new JobPostDAO();
                                            List<Post> postList = dao.getMyPosts(userid);

                                            if(postList.isEmpty()) {
                                        %>
                                        <h5>You have not made any post yet.</h5>
                                        <%
                                            } else {
                                        %>
                                        <table class="table table-borderless table-data3">
                                            <thead>
                                                <tr>
                                                    <th>id</th>
                                                    <th>company</th>
                                                    <th>title</th>
                                                    <th>type</th>
                                                    <th>category</th>
                                                    <th>location</th>
                                                    <th>vacancy</th>
                                                    <th>posted</th>
                                                    <th>expires</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                                for (Post p : postList) {
                                            %>
                                                <tr>
                                                    <td>
                                                        <a href="${pageContext.request.contextPath}/dispatch?page=jobdetails&postid=<%=p.getId()%>">
                                                            <span style="z-index: 11; color: dodgerblue;"><%=p.getId()%></span>
                                                        </a>
                                                    </td>
                                                    <td><%=p.getCompanyName()%></td>
                                                    <td><%=p.getTitle()%></td>
                                                    <td><%=p.getJobType()%></td>
                                                    <td><%=p.getCategory()%></td>
                                                    <td><%=p.getLocation()%></td>
                                                    <td><%=p.getVacancy()%></td>
                                                    <td><%=p.getPosted()%></td>
                                                    <td><%=p.getExpires()%></td>
                                                    <td>
                                                        <div class="table-data-feature">
                                                            <a href="${pageContext.request.contextPath}/dispatch?page=editpost&postid=<%=p.getId()%>">
                                                                <button class="item" data-toggle="tooltip" data-placement="top" title="Edit">
                                                                    <i class="zmdi zmdi-edit"></i>
                                                                </button>
                                                            </a>
                                                            <a href="${pageContext.request.contextPath}/dispatch?page=deletepost&postid=<%=p.getId()%>">
                                                                <button class="item" data-toggle="tooltip" data-placement="top" title="Delete">
                                                                    <i class="zmdi zmdi-delete"></i>
                                                                </button>
                                                            </a>
                                                        </div>
                                                    </td>
                                                </tr>
                                            <%
                                                } //end foreach loop
                                            %>
                                            </tbody>
                                        </table>
                                        <%
                                            } //close else
                                        %>
                                    </div>
                                    <!-- END DATA TABLE-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp" />

        <script src="${pageContext.request.contextPath}/pages/admin/vendor/jquery-3.2.1.min.js"></script>
        <!-- Bootstrap JS-->
        <script src="${pageContext.request.contextPath}/pages/admin/vendor/bootstrap-4.1/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/pages/admin/vendor/bootstrap-4.1/bootstrap.min.js"></script>
        <!-- Vendor JS       -->
        <script src="${pageContext.request.contextPath}/pages/admin/vendor/animsition/animsition.min.js"></script>

        <!-- Main JS-->
        <script src="${pageContext.request.contextPath}/pages/admin/js/main.js"></script>


        <jsp:include page="footer-js.jsp" />
    
    </body>
</html>