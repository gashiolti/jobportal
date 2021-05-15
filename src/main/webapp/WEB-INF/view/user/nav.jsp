<%--
  Created by IntelliJ IDEA.
  User: gashi
  Date: 5/8/2021
  Time: 12:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <!-- Header Start -->
    <div class="header-area header-transparrent">
        <div class="headder-top header-sticky">
            <div class="container">
                <div class="row align-items-center">
                    <%
                        int id = 0;
                        try {
                            id = Integer.parseInt(request.getParameter("userid"));
                        }
                        catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    %>
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
                                        <li><a href="#">Page</a>
                                            <ul class="submenu">
                                                <li><a
                                                        href="${pageContext.request.contextPath}/dispatch?page=profile&userid=${userid}">
                                                    Profile</a></li>
                                                <li><a
                                                        href="${pageContext.request.contextPath}/dispatch?page=myposts&userid=${userid}">
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
