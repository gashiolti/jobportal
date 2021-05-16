<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.adminmanagment.model.Admin" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.adminmanagment.dao.AdminDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<%--<head>--%>
<%--    <!-- Required meta tags-->--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
<%--    <meta name="description" content="au theme template">--%>
<%--    <meta name="author" content="Hau Nguyen">--%>
<%--    <meta name="keywords" content="au theme template">--%>

<%--    <!-- Title Page-->--%>
<%--    <title>Tables</title>--%>

<%--    <!-- Fontfaces CSS-->--%>
<%--    <link href="../../../pages/admin/css/font-face.css" rel="stylesheet" media="all">--%>
<%--    <link href="../../../pages/admin/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">--%>
<%--    <link href="../../../pages/admin/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">--%>
<%--    <link href="../../../pages/admin/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">--%>

<%--    <!-- Bootstrap CSS-->--%>
<%--    <link href="../../../pages/admin/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">--%>

<%--    <!-- Vendor CSS-->--%>
<%--    <link href="../../../pages/admin/vendor/animsition/animsition.min.css" rel="stylesheet" media="all">--%>
<%--    <link href="../../../pages/admin/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">--%>
<%--    <link href="../../../pages/admin/vendor/wow/animate.css" rel="stylesheet" media="all">--%>
<%--    <link href="../../../pages/admin/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">--%>
<%--    <link href="../../../pages/admin/vendor/slick/slick.css" rel="stylesheet" media="all">--%>
<%--    <link href="../../../pages/admin/vendor/select2/select2.min.css" rel="stylesheet" media="all">--%>
<%--    <link href="../../../pages/admin/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">--%>

<%--    <!-- Main CSS-->--%>
<%--    <link href="../../../pages/admin/css/theme.css" rel="stylesheet" media="all">--%>

<%--</head>--%>

<body class="animsition">
    <div class="page-wrapper">

        <!-- HEADER MOBILE -->
        <jsp:include page="header-mobile.jsp" />
        <!-- END HEADER MOBILE-->

        <!-- MENU SIDEBAR-->
        <jsp:include page="menu-sidebar.jsp" />
        <!-- END MENU SIDEBAR-->

        <!-- PAGE CONTAINER-->
        <div class="page-container">
            <!-- HEADER DESKTOP-->
            <jsp:include page="header-desktop.jsp" />
            <!-- END HEADER DESKTOP-->

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">

                        <%
                            String query = request.getQueryString();
                            String url = request.getRequestURL().toString() + "?" + query;
                            if(url.contains("message")) {
                        %>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="success-message">
                                    <p class="success">${success}</p>
                                </div>
                            </div>
                        </div>
                        <%
                            } //close if statement
                        %>
                        <div class="row">
                            <div class="col-lg-6">
                                <!-- USER DATA-->
                                <div class="user-data m-b-30">
                                    <h3 class="title-3 m-b-30">
                                        <i class="zmdi zmdi-account-calendar"></i>admin data
                                    </h3>
                                    <div class="filters m-b-45">
                                        <div class="rs-select2--dark rs-select2--md m-r-10 rs-select2--border">
                                            <select class="js-select2" name="property">
                                                <option selected="selected">All Properties</option>
                                                <option value="">Products</option>
                                                <option value="">Services</option>
                                            </select>
                                            <div class="dropDownSelect2"></div>
                                        </div>
<%--                                        <div class="rs-select2--dark rs-select2--sm rs-select2--border">--%>
<%--                                            <select class="js-select2 au-select-dark" name="time">--%>
<%--                                                <option selected="selected">All Time</option>--%>
<%--                                                <option value="">By Month</option>--%>
<%--                                                <option value="">By Day</option>--%>
<%--                                            </select>--%>
<%--                                            <div class="dropDownSelect2"></div>--%>
<%--                                        </div>--%>
                                        <div class="rs-select2--dark rs-select2--md m-r-10 rs-select2--border">
                                            <a href="${pageContext.request.contextPath}/dispatch?page=signup"
                                               class="a-select2"><i
                                                    class="fas fa-plus"></i>
                                                Admin</a>
                                        </div>
                                    </div>
                                    <div class="table-responsive table-data">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <td>
                                                        <label class="au-checkbox">
                                                            <input type="checkbox">
                                                            <span class="au-checkmark"></span>
                                                        </label>
                                                    </td>
                                                    <td>name</td>
                                                    <td>role</td>
                                                    <td></td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%-- fetching the attributes of the request object which was set by servlet AdminServlet --%>
                                                <%
                                                    AdminDAO adminDAO = new AdminDAO();
                                                    List<Admin> admins = adminDAO.selectAdmins();
//                                                    ArrayList<admins> admins =
//                                                            (ArrayList<Admin>)request.getAttribute("data");

                                                    for(Admin a:admins) {
                                                %>
                                                <tr>
                                                    <td>
                                                        <label class="au-checkbox">
                                                            <input type="checkbox">
                                                            <span class="au-checkmark"></span>
                                                        </label>
                                                    </td>
                                                    <td>
                                                        <div class="table-data__info">
                                                            <h6><%=a.getName()%>></h6>
                                                            <span>
                                                                <a href="#"><%=a.getEmail()%></a>
                                                                <input type="hidden" value="<%=a.getId()%>" />
                                                            </span>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <span class="role admin">admin</span>
                                                    </td>
                                                    <td>
                                                        <div class="table-data-feature">
                                                            <a href="${pageContext.request.contextPath}/dispatch?page=edituser&userid=<%=a.getId()%>">
                                                                <button class="item" data-toggle="tooltip" data-placement="top" title="Edit">
                                                                    <i class="zmdi zmdi-edit"></i>
                                                                </button>
                                                            </a>
                                                            <a href="${pageContext.request.contextPath}/admin?page=deleteuser&userid=<%=a.getId()%>">
                                                                <button class="item" data-toggle="tooltip" data-placement="top" title="Delete">
                                                                    <i class="zmdi zmdi-delete"></i>
                                                                </button>
                                                            </a>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <span class="more">
                                                            <i class="zmdi zmdi-more"></i>
                                                        </span>
                                                    </td>
                                                </tr>
                                                <%}%><%-- close foreach loop --%>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="user-data__footer">
                                        <button class="au-btn au-btn-load">load more</button>
                                    </div>
                                </div>
                                <!-- END USER DATA-->
                            </div>
                            <div class="col-lg-6">
                                <!-- TOP CAMPAIGN-->
                                <div class="top-campaign">
                                    <h3 class="title-3 m-b-30">top campaigns</h3>
                                    <div class="table-responsive">
                                        <table class="table table-top-campaign">
                                            <tbody>
                                                <tr>
                                                    <td>1. Australia</td>
                                                    <td>$70,261.65</td>
                                                </tr>
                                                <tr>
                                                    <td>2. United Kingdom</td>
                                                    <td>$46,399.22</td>
                                                </tr>
                                                <tr>
                                                    <td>3. Turkey</td>
                                                    <td>$35,364.90</td>
                                                </tr>
                                                <tr>
                                                    <td>4. Germany</td>
                                                    <td>$20,366.96</td>
                                                </tr>
                                                <tr>
                                                    <td>5. France</td>
                                                    <td>$10,366.96</td>
                                                </tr>
                                                <tr>
                                                    <td>3. Turkey</td>
                                                    <td>$35,364.90</td>
                                                </tr>
                                                <tr>
                                                    <td>4. Germany</td>
                                                    <td>$20,366.96</td>
                                                </tr>
                                                <tr>
                                                    <td>5. France</td>
                                                    <td>$10,366.96</td>
                                                </tr>
                                                <tr>
                                                    <td>3. Turkey</td>
                                                    <td>$35,364.90</td>
                                                </tr>
                                                <tr>
                                                    <td>4. Germany</td>
                                                    <td>$20,366.96</td>
                                                </tr>
                                                <tr>
                                                    <td>5. France</td>
                                                    <td>$10,366.96</td>
                                                </tr>
                                                <tr>
                                                    <td>4. Germany</td>
                                                    <td>$20,366.96</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!--  END TOP CAMPAIGN-->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <!-- DATA TABLE -->
                                <h3 class="title-5 m-b-35">user table</h3>
                                <div class="table-data__tool">
                                    <div class="table-data__tool-left">
                                        <div class="rs-select2--light rs-select2--md">
                                            <select class="js-select2" name="property">
                                                <option selected="selected">All Properties</option>
                                                <option value="">Option 1</option>
                                                <option value="">Option 2</option>
                                            </select>
                                            <div class="dropDownSelect2"></div>
                                        </div>
                                        <div class="rs-select2--light rs-select2--sm">
                                            <select class="js-select2" name="time">
                                                <option selected="selected">Today</option>
                                                <option value="">3 Days</option>
                                                <option value="">1 Week</option>
                                            </select>
                                            <div class="dropDownSelect2"></div>
                                        </div>
                                        <button class="au-btn-filter">
                                            <i class="zmdi zmdi-filter-list"></i>filters</button>
                                    </div>
                                    <div class="table-data__tool-right">
                                        <button class="au-btn au-btn-icon au-btn--green au-btn--small">
                                            <i class="zmdi zmdi-plus"></i>add item</button>
                                        <div class="rs-select2--dark rs-select2--sm rs-select2--dark2">
                                            <select class="js-select2" name="type">
                                                <option selected="selected">Export</option>
                                                <option value="">Option 1</option>
                                                <option value="">Option 2</option>
                                            </select>
                                            <div class="dropDownSelect2"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-responsive table-responsive-data2">
                                    <table class="table table-data2">
                                        <thead>
                                            <tr>
                                                <th>
                                                    <label class="au-checkbox">
                                                        <input type="checkbox">
                                                        <span class="au-checkmark"></span>
                                                    </label>
                                                </th>
                                                <th>name</th>
                                                <th>Address</th>
                                                <th>email</th>
                                                <th>password</th>
                                                <th>webiste</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${companies}" var="company">
                                            <tr class="tr-shadow">
                                                <td>
                                                    <label class="au-checkbox">
                                                        <input type="checkbox">
                                                        <span class="au-checkmark"></span>
                                                    </label>
                                                </td>
                                                <td>${company.getName()}</td>
                                                <input type="hidden" name="id" value="${company.getId()}" />
                                                <td>
                                                    <span class="block-email">${company.getAddress()}</span>
                                                </td>
                                                <td class="desc">${company.getEmail()}</td>
                                                <td>${company.getPass()}</td>
                                                <td>
                                                    <span class="status--process">
                                                        <a href="${company.getWebUrl()}">${company.getWebUrl()}</a>
                                                    </span>
                                                </td>
                                                <td>
                                                    <div class="table-data-feature">
                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit">
                                                            <i class="zmdi zmdi-edit"></i>
                                                        </button>
                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Delete">
                                                            <i class="zmdi zmdi-delete"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="spacer"></tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                                <!-- END DATA TABLE -->
                            </div>
                        </div>
                        <div class="row m-t-30">
                            <div class="col-md-12">
                                <!-- DATA TABLE-->
                                <div class="table-responsive m-b-40">
                                    <h3 class="title-5 m-b-35">post table</h3>
                                    <table class="table table-borderless table-data3">
                                        <thead>
                                            <tr>
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
                                            <c:forEach items="${posts}" var="post">
                                            <tr>
                                                <input type="hidden" name="postid" value="${post.getId()}" />
                                                <td>${post.getCompanyName()}</td>
                                                <td>${post.getTitle()}</td>
                                                <td>${post.getJobType()}</td>
                                                <td>${post.getCategory()}</td>
                                                <td>${post.getLocation()}</td>
                                                <td>${post.getVacancy()}</td>
                                                <td>${post.getPosted()}</td>
                                                <td>${post.getExpires()}</td>
                                                <td>
                                                    <div class="table-data-feature">
                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit">
                                                            <i class="zmdi zmdi-edit"></i>
                                                        </button>
                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Delete">
                                                            <i class="zmdi zmdi-delete"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                            </c:forEach>
<%--                                            <tr>--%>
<%--                                                <td>Google</td>--%>
<%--                                                <td>Enginieer</td>--%>
<%--                                                <td>Full time</td>--%>
<%--                                                <td>IT</td>--%>
<%--                                                <td>USA NY</td>--%>
<%--                                                <td>4</td>--%>
<%--                                                <td>5/3/2021</td>--%>
<%--                                                <td>5/4/2021</td>--%>
<%--                                                <td>--%>
<%--                                                    <div class="table-data-feature">--%>
<%--                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit">--%>
<%--                                                            <i class="zmdi zmdi-edit"></i>--%>
<%--                                                        </button>--%>
<%--                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Delete">--%>
<%--                                                            <i class="zmdi zmdi-delete"></i>--%>
<%--                                                        </button>--%>
<%--                                                    </div>--%>
<%--                                                </td>--%>
<%--                                            </tr>--%>
<%--                                            <tr>--%>
<%--                                                <td>Google</td>--%>
<%--                                                <td>Enginieer</td>--%>
<%--                                                <td>Full time</td>--%>
<%--                                                <td>IT</td>--%>
<%--                                                <td>USA NY</td>--%>
<%--                                                <td>4</td>--%>
<%--                                                <td>5/3/2021</td>--%>
<%--                                                <td>5/4/2021</td>--%>
<%--                                                <td>--%>
<%--                                                    <div class="table-data-feature">--%>
<%--                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit">--%>
<%--                                                            <i class="zmdi zmdi-edit"></i>--%>
<%--                                                        </button>--%>
<%--                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Delete">--%>
<%--                                                            <i class="zmdi zmdi-delete"></i>--%>
<%--                                                        </button>--%>
<%--                                                    </div>--%>
<%--                                                </td>--%>
<%--                                            </tr>--%>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- END DATA TABLE-->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="copyright">
                                    <p>Copyright © 2018 Colorlib. All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <jsp:include page="footer-js.jsp" />

</body>

</html>
<!-- end document-->
