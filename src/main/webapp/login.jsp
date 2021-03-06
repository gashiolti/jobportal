<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

    <!-- Font Icon -->
    <link rel="stylesheet" href="assets/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="assets/css/regstyle.css">
</head>
<body>

    <div class="main">
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <form action="<%=request.getContextPath()%>/home" method="POST" id="signup-form"
                          class="signup-form">
                        <h2 class="form-title">Sign in</h2>
                        <%
                            String queryString = request.getQueryString();
                            String url = request.getRequestURL().toString()+"?"+queryString;

                            String param = "error";

                            if(url.contains(param)) {
                        %>
                        <div class="form-group">
                            <p class="error-p"> ${param.error} </p>
                        </div>
                        <%  }  //endif %>

                        <div class="form-group">
                            <input type="text" class="form-input" name="email" id="name" placeholder="Email"/>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-input" name="password" id="password"
                                   placeholder="Password"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Sign in"/>
                        </div>
                    </form>
                    <p class="loginhere">
                        Do not have an account ? <a href="signup.jsp" class="loginhere-link">Sign up here</a>
                    </p>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="assets/js/main2.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>