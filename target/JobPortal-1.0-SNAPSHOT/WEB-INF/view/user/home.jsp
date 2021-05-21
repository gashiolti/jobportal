<%@ page import="com.example.JobPost.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.JobPost.JobPostDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.Comparator" %>
<jsp:include page="header.jsp" />

<body>
    <!-- Preloader Start -->
    <div id="preloader-active">
        <div class="preloader d-flex align-items-center justify-content-center">
            <div class="preloader-inner position-relative">
                <div class="preloader-circle"></div>
                <div class="preloader-img pere-text">
                    <img src="${pageContext.request.contextPath}/assets/img/logo/logo.png" alt="logo">
                </div>
            </div>
        </div>
    </div>
    <!-- Preloader Start -->
    <jsp:include page="nav.jsp" />


    <main>
        <!-- slider Area Start-->
        <div class="slider-area ">
            <!-- Mobile Menu -->
            <div class="slider-active">
                <div class="single-slider slider-height d-flex align-items-center" data-background="${pageContext.request.contextPath}/assets/img/hero/h1_hero.jpg">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-6 col-lg-9 col-md-10">
                                <div class="hero__caption">
                                    <h1>Find the most exciting startup jobs</h1>
                                </div>
                            </div>
                        </div>
                        <!-- Search Box -->
                        <div class="row" style="margin-bottom: 50px;">
                            <div class="col-xl-8">
                                <!-- form -->
                                <form method="POST" action="${pageContext.request.contextPath}/searchpost?usertype=user"
                                      class="search-box">
                                    <div class="input-form">
                                        <input name="title" type="text" placeholder="Job Tittle or keyword">
                                    </div>
                                    <div class="select-form">
                                        <div class="select-itms">
                                            <select name="location" id="select1">
                                                <option disabled="disabled" selected="selected" value="location">LOCATION
                                                </option>
                                                <option value="Kosovo">Location - Kosovo</option>
                                                <option value="Albania">Location - Albania</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="search-form">
                                        <button type="submit" class="btn head-btn1 custom-search-btn">Search</button>
                                    </div>
                                </form>	
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- slider Area End-->
        <!-- Our Services Start -->
        <!-- Our Services Start -->
        <div class="our-services section-pad-t30">
            <div class="container">
                <!-- Section Tittle -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-tittle text-center">
                            <span>FEATURED TOURS Packages</span>
                            <h2>Browse Top Categories </h2>
                        </div>
                    </div>
                </div>
                <%
                    JobPostDAO dao = new JobPostDAO();
                    List<Post> postList = dao.displayJobPosts();
                %>
                <div class="row d-flex justify-contnet-center">
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                        <div class="single-services text-center mb-30">
                            <div class="services-ion">
                                <span class="flaticon-tour"></span>
                            </div>
                            <div class="services-cap">
                                <%
                                    List<Post> postList1 = postList.stream().filter(post -> post.getJobCategory() == 6)
                                            .collect(Collectors.toList());
                                    int total = postList1.size();
                                %>
                                <h5><a href="search?category=6&usertype=client">Design & Architecture</a></h5>
                                <span>(<%=total%>)</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                        <div class="single-services text-center mb-30">
                            <div class="services-ion">
                                <span class="flaticon-cms"></span>
                            </div>
                            <div class="services-cap">
                                <%
                                    List<Post> postList2 = postList.stream().filter(post -> post.getJobCategory() == 1)
                                            .collect(Collectors.toList());
                                    int total2 = postList2.size();
                                %>
                                <h5><a href="search?category=1&usertype=user">Design & Development</a></h5>
                                <span>(<%=total2%>)</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                        <div class="single-services text-center mb-30">
                            <div class="services-ion">
                                <span class="flaticon-report"></span>
                            </div>
                            <div class="services-cap">
                                <%
                                    List<Post> postList3 = postList.stream().filter(post -> post.getJobCategory() == 8)
                                            .collect(Collectors.toList());
                                    int total3 = postList3.size();
                                %>
                                <h5><a href="search?category=8&usertype=user">Sales & Marketing</a></h5>
                                <span>(<%=total3%>)</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                        <div class="single-services text-center mb-30">
                            <div class="services-ion">
                                <span class="flaticon-app"></span>
                            </div>
                            <div class="services-cap">
                                <h5><a href="search?category=1&usertype=user">Mobile Application</a></h5>
                                <span>(<%=total2%>)</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                        <div class="single-services text-center mb-30">
                            <div class="services-ion">
                                <span class="flaticon-helmet"></span>
                            </div>
                            <div class="services-cap">
                                <%
                                    List<Post> postList4 = postList.stream().filter(post -> post.getJobCategory() == 64)
                                            .collect(Collectors.toList());
                                    int total4 = postList4.size();
                                %>
                                <h5><a href="search?category=64&usertype=user">Construction</a></h5>
                                <span>(<%=total4%>)</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                        <div class="single-services text-center mb-30">
                            <div class="services-ion">
                                <span class="flaticon-high-tech"></span>
                            </div>
                            <div class="services-cap">
                                <h5><a href="search?category=1&usertype=user">Information Technology</a></h5>
                                <span>(<%=total2%>)</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                        <div class="single-services text-center mb-30">
                            <div class="services-ion">
                                <span class="flaticon-real-estate"></span>
                            </div>
                            <div class="services-cap">
                                <%
                                    List<Post> postList5 = postList.stream().filter(post -> post.getJobCategory() == 84)
                                            .collect(Collectors.toList());
                                    int total5 = postList5.size();
                                %>
                                <h5><a href="search?category=84&usertype=client">Real Estate</a></h5>
                                <span>(<%=total5%>)</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                        <div class="single-services text-center mb-30">
                            <div class="services-ion">
                                <span class="flaticon-content"></span>
                            </div>
                            <div class="services-cap">
                                <%
                                    List<Post> postList6 = postList.stream().filter(post -> post.getJobCategory() == 74)
                                            .collect(Collectors.toList());
                                    int total6 = postList6.size();
                                %>
                                <h5><a href="search?category=74&usertype=client">Content Writer</a></h5>
                                <span>(<%=total6%>)</span>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- More Btn -->
                <!-- Section Button -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="browse-btn2 text-center mt-50">
                            <a href="job_listing.jsp" class="border-btn2">Browse All Sectors</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Our Services End -->

        <!-- Featured_job_start -->
        <section class="featured-job-area feature-padding">
            <div class="container">
                <!-- Section Tittle -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-tittle text-center">
                            <span>Recent Job</span>
                            <h2>Featured Jobs</h2>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-xl-10">
                        <!-- single-job-content -->
                        <%
                            List<Post> temp = new ArrayList<Post>();
                            postList.sort(Comparator.comparing(Post::getId).reversed());
                            int i = 0;
                            while (i < 5) {
                                temp.add(postList.get(i));
                                i++;
                            }
                            for (Post post : temp) {
                        %>
                        <div class="single-job-items mb-30" style="border: 1px solid #e8e8e8;">
                            <div class="job-items">
                                <div class="company-img">
                                    <a href="${pageContext.request.contextPath}/dispatch?page=postdetails&postid=<%=post.getId()%>">
                                        <img src="imageServlet?postid=<%=post.getId()%>" alt="logo" width="90"
                                             height="90">
                                    </a>
                                </div>
                                <div class="job-tittle job-tittle2">
                                    <a href="${pageContext.request.contextPath}/dispatch?page=postdetails&postid=<%=post.getId()%>">
                                        <h4><%=post.getTitle()%></h4>
                                        <input type="hidden" name="postid" value="<%=post.getId()%>">
                                    </a>
                                    <ul>
                                        <li><%=post.getCompanyName()%></li>
                                        <li><i class="fas fa-map-marker-alt"></i><%=post.getLocation()%></li>
                                        <li><i class="fas fa-euro-sign"></i><%=post.getSalary()%></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="items-link items-link2 f-right">
                                <a href="${pageContext.request.contextPath}/dispatch?page=postdetails&postid
                                        =<%=post.getId()%>"><%=post.getJobType()%></a>
                                <span><%=post.getPosted()%></span>
                                <span><%=post.getExpires()%></span>
                            </div>
                        </div>
                        <%
                            } //close foreach loop
                        %>
                    </div>
                </div>
            </div>
        </section>
        <!-- Featured_job_end -->


         <!-- Support Company Start-->
         <div class="support-company-area support-padding fix">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-xl-6 col-lg-6">
                        <div class="right-caption">
                            <!-- Section Tittle -->
                            <div class="section-tittle section-tittle2">
                                <span>What we are doing</span>
                                <h2>24k Talented people are getting Jobs</h2>
                            </div>
                            <div class="support-caption">
                                <p class="pera-top">Mollit anim laborum duis au dolor in voluptate velit ess cillum dolore eu lore dsu quality mollit anim laborumuis au dolor in voluptate velit cillum.</p>
                                <p>Mollit anim laborum.Duis aute irufg dhjkolohr in re voluptate velit esscillumlore eu quife nrulla parihatur. Excghcepteur signjnt occa cupidatat non inulpadeserunt mollit aboru. temnthp incididbnt ut labore mollit anim laborum suis aute.</p>
                                <a href="${pageContext.request.contextPath}/postjob" class="btn post-btn">Post a job</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6">
                        <div class="support-location-img">
                            <img src="${pageContext.request.contextPath}/assets/img/service/support-img.jpg"
                                 alt="">
                            <div class="support-img-cap text-center">
                                <p>Since</p>
                                <span>1994</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Support Company End-->


    </main>

    <!--FOOTER -->
    <jsp:include page="footer.jsp" />

    <!-- JS here -->
    <jsp:include page="footer-js.jsp" />

        
    </body>
</html>