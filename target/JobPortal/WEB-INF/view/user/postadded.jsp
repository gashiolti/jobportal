<%--
  Created by IntelliJ IDEA.
  User: gashi
  Date: 5/9/2021
  Time: 1:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />

<body>
    <div class="post-added-wrapper">
        <div class="post-added-box">
            <header class="site-header" id="header">
                <h1 class="site-header__title" data-lead-id="site-header-title">POST ADDED</h1>
            </header>

            <div class="main-content">
                <i class="fa fa-check main-content__checkmark" id="checkmark"></i>
<%--                <p class="main-content__body" data-lead-id="main-content-body">${ksa}</p>--%>
                <p class="main-content__body" data-lead-id="main-content-body">Your post has been successfully added. Thanks for using our website!</p>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
    <jsp:include page="footer-js.jsp" />
</body>
</html>
