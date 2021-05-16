<%--
  Created by IntelliJ IDEA.
  User: gashi
  Date: 5/6/2021
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- HEADER-->
<jsp:include page="header.jsp" />


<!-- HEADER MOBILE-->
<header class="header-mobile d-block d-lg-none">
    <div class="header-mobile__bar">
        <div class="container-fluid">
            <div class="header-mobile-inner">
                <a class="logo" href="${pageContext.request.contextPath}/dispatch?page=index">
                    <img src="${pageContext.request.contextPath}/admin/images/icon/logo.png"
                         alt="CoolAdmin" />
                </a>
                <button class="hamburger hamburger--slider" type="button">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                </button>
            </div>
        </div>
    </div>
    <nav class="navbar-mobile">
        <div class="container-fluid">
            <ul class="navbar-mobile__list list-unstyled">
                <li class="has-sub">
                    <a class="js-arrow" href="#">
                        <i class="fas fa-tachometer-alt"></i>Dashboard</a>
                    <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                        <li>
                            <a href="${pageContext.request.contextPath}/dispatch?page=index">Dashboard 1</a>
                        </li>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/index2.html">Dashboard 2</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/index3.html">Dashboard 3</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/index4.html">Dashboard 4</a>--%>
<%--                        </li>--%>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/dispatch?page=chart">
                        <i class="fas fa-chart-bar"></i>Charts</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/dispatch?page=table">
                        <i class="fas fa-table"></i>Tables</a>
                </li>
<%--                <li>--%>
<%--                    <a href="${pageContext.request.contextPath}/pages/admin/form.html">--%>
<%--                        <i class="far fa-check-square"></i>Forms</a>--%>
<%--                </li>--%>
<%--                <li>--%>
<%--                    <a href="${pageContext.request.contextPath}/pages/admin/calendar.html">--%>
<%--                        <i class="fas fa-calendar-alt"></i>Calendar</a>--%>
<%--                </li>--%>
<%--                <li>--%>
<%--                    <a href="${pageContext.request.contextPath}/pages/admin/map.html">--%>
<%--                        <i class="fas fa-map-marker-alt"></i>Maps</a>--%>
<%--                </li>--%>
<%--                <li class="has-sub">--%>
<%--                    <a class="js-arrow" href="#">--%>
<%--                        <i class="fas fa-copy"></i>Pages</a>--%>
<%--                    <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/login.html">Login</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/register.jsp">Register</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/forget-pass.html">Forget Password</a>--%>
<%--                        </li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="has-sub">--%>
<%--                    <a class="js-arrow" href="#">--%>
<%--                        <i class="fas fa-desktop"></i>UI Elements</a>--%>
<%--                    <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/button.html">Button</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/badge.html">Badges</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/tab.html">Tabs</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/card.html">Cards</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/alert.html">Alerts</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/progress-bar.html">Progress Bars</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/modal.html">Modals</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/switch.html">Switchs</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/grid.html">Grids</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/fontawesome.html">Fontawesome Icon</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/pages/admin/typo.html">Typography</a>--%>
<%--                        </li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
            </ul>
        </div>
    </nav>
</header>
<!-- END HEADER MOBILE-->
