
<jsp:include page="header.jsp" />

<body class="animsition">
    <div class="page-wrapper">
        <div class="page-content--bge5" style="background-image: url('${pageContext.request.contextPath}/admin/images/headway-5QgIuuBxKwM-unsplash.jpg');
                                        background-color: #212121;
                                        background-blend-mode: multiply;">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                        <div class="login-logo">
                            <a href="${pageContext.request.contextPath}/dispatch?page=index">
                                <img src="${pageContext.request.contextPath}/admin/images/icon/logo.png" alt="CoolAdmin">
                            </a>
                        </div>
                        <div class="login-form">
                            <form action="<%=request.getContextPath()%>/register" method="post">
                                <%
                                    String queryString = request.getQueryString();
                                    String url = request.getRequestURL().toString()+"?"+queryString;

                                    String param = "error";

                                    if(url.contains(param)) {
                                %>
                                    <div class="form-group">
                                        <p class="error-p"> ${errorDisplay} </p>
                                    </div>
                                <%  }  //endif %>

                               <div class="form-group">
                                    <label>Full Name</label>
                                    <input class="au-input au-input--full" type="text" name="name" placeholder="Full name">
                                </div>
                                <div class="form-group">
                                    <label>Email Address</label>
                                    <input class="au-input au-input--full" type="email" name="email" placeholder="Email">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input class="au-input au-input--full" type="password" name="password" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <label>Confirm Password</label>
                                    <input class="au-input au-input--full" type="password" name="confirmpass" placeholder="Confirm Password">
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <input class="au-input au-input--full" type="text" name="address" placeholder="Address">
                                </div>
                                <div class="login-checkbox">
                                    <label>
                                        <input type="checkbox" name="aggree">Agree the terms and policy
                                    </label>
                                </div>
                                <button class="au-btn au-btn--block au-btn--green m-b-20" type="submit">register</button>
                                <div class="social-login-content">
                                    <div class="social-button">
                                        <button class="au-btn au-btn--block au-btn--blue m-b-20">register with facebook</button>
                                        <button class="au-btn au-btn--block au-btn--blue2">register with twitter</button>
                                    </div>
                                </div>
                            </form>
                            <div class="register-link">
                                <p>
                                    Already have account?
                                    <a href="${pageContext.request.contextPath}/login.jsp">Sign In</a>
                                </p>
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