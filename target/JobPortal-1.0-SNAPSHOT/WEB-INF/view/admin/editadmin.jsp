<%@ page import="com.example.adminmanagment.model.Admin" %>
<%@ page import="com.example.adminmanagment.dao.AdminDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.crypto.Crypto" %>
<%@ page import="java.util.Objects" %>
<!DOCTYPE html>
<html lang="en">

    <jsp:include page="header.jsp" />

    <body class="animsition">
        <div class="page-wrapper">
            <!-- HEADER MOBILE-->
            <jsp:include page="header-mobile.jsp" />
            <!-- END HEADER MOBILE-->
            
            <!-- MENU SIDEBAR-->
            <jsp:include page="menu-sidebar.jsp" />
            <!-- END MENU SIDEBAR-->
            

        <!-- PAGE CONTAINER-->
        <div class="page-container">
            <!-- HEADER DESKTOP-->
            <jsp:include page="header-desktop.jsp" />
            <!-- HEADER DESKTOP-->

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-10">
                                <div class="card">
                                    <div class="card-header">Update Admin</div>
                                    <div class="card-body card-block">
                                        <%
                                            String query = request.getQueryString();
                                            String userID = request.getParameter("userid");
                                            int id = 0;
                                            try {
                                                if(userID == null || userID.isEmpty()) {
                                                    return;
                                                }
                                                id = Integer.parseInt(userID);
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                            }

                                            AdminDAO dao = new AdminDAO();
                                            Admin admin = dao.selectAdmin(id);
//
                                        %>
                                        <form action="<%=request.getContextPath()%>/updateadmin" method="POST" class="">
                                            <%
                                                String queryURL = request.getQueryString();
                                                String url = request.getRequestURL().toString()+ "?" + queryURL;
                                                if(url.contains("error")) {
                                            %>
                                                <div class="form-group">
                                                    <div class="input-group input-group-error">
                                                        <p class="error-p">${errorDisplay}</p>
                                                    </div>
                                                </div>
                                            <%}%>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <div class="input-group-addon">
                                                        <i class="fas fa-user"></i>
                                                    </div>
                                                    <input type="hidden" id="id" name="id" value="<%=id%>">
                                                    <input type="text" id="name" name="name" placeholder="Full Name"
                                                           class="form-control" value="<%=admin.getName()%>">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <div class="input-group-addon">
                                                        <i class="fas fa-building"></i>
                                                    </div>
                                                    <input type="text" id="address" name="address"
                                                           placeholder="Address" class="form-control"
                                                           value="<%=admin.getAddress()%>">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-envelope"></i>
                                                    </div>
                                                    <input type="email" id="email" name="email" placeholder="Email"
                                                           class="form-control" value="<%=admin.getEmail()%>">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-asterisk"></i>
                                                    </div>
                                                    <input type="text" id="password" name="password"
                                                           placeholder="Password" class="form-control"
                                                           value="<%=Crypto.decode(admin.getPassword())%>">
                                                </div>
                                            </div>
                                            <div class="form-actions form-group">
                                                <button type="submit" class="btn btn-success btn-sm">Submit</button>
                                            </div>
                                        </form>
<%--                                        <%} //close if statement%>--%>
                                    </div>
                                </div>
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



    <jsp:include page="footer-js.jsp" />

</body>
</html>