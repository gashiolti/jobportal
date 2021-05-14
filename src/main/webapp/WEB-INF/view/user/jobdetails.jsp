<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.example.companymanagment.Company" %>
<%@ page import="com.example.companymanagment.CompanyDAO" %>
<%@ page import="com.example.JobPost.*" %>
<jsp:include page="header.jsp" />

   <body>
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
    <jsp:include page="nav.jsp" />


    <%
        int postid = 0;
        try {
            postid = Integer.parseInt(request.getParameter("postid"));
        } catch (NumberFormatException e) {}

        JobPostDAO dao = new JobPostDAO();
        Post post = dao.getPost(postid);

        Salary salary = dao.getSalary(postid);

        JobDetails jobDetails = dao.getJobDetails(postid);

        CompanyImage image = dao.getCompanyImage(postid);
    %>
    <main>
        <!-- job post company Start -->
        <div class="job-post-company pt-120 pb-120">
            <div class="container">
                <div class="row justify-content-between">
                    <!-- Left Content -->
                    <div class="col-xl-7 col-lg-8">
                        <!-- job single -->
                        <div class="single-job-items mb-50">
                            <div class="job-items">
                                <div class="company-img company-img-details">
                                    <a href="#"><img
                                            src=""
                                                     alt="Logo"></a>
                                </div>
                                <div class="job-tittle">
                                    <a href="#">
                                        <h4><%=post.getTitle()%></h4>
                                    </a>
                                    <ul>
                                        <li><%=post.getCompanyName()%></li>
                                        <li><i class="fas fa-map-marker-alt"></i><%=post.getLocation()%></li>
                                        <li><i class="fas fa-euro-sign"></i><%=salary.getSalary()%></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                          <!-- job single End -->
                       
                        <div class="job-post-details">
                            <div class="post-details1 mb-50">
                                <!-- Small Section Tittle -->
                                <div class="small-section-tittle">
                                    <h4>Job Description</h4>
                                </div>
                                <p><%=post.getDescription()%>.</p>
                            </div>
                            <div class="post-details2  mb-50">
                                 <!-- Small Section Tittle -->
                                <div class="small-section-tittle">
                                    <h4>Required Knowledge, Skills, and Abilities</h4>
                                </div>
                               <ul>
                                   <%
                                        String skillsAndAbilities = jobDetails.getKnowledgeSkillAbilities();
                                       List<String> list = Arrays.asList(skillsAndAbilities.split("\\s*;\\s*"));

                                       for (String item : list) {
                                   %>
                                   <li><%=item%></li>
                                   <%
                                       } // close foreach loop
                                   %>
                               </ul>
                            </div>
                            <div class="post-details2  mb-50">
                                 <!-- Small Section Tittle -->
                                <div class="small-section-tittle">
                                    <h4>Education + Experience</h4>
                                </div>
                               <ul>
                                   <%
                                       String educationAdnExperience = jobDetails.getEducationExperience();
                                       List<String> list2 = Arrays.asList(educationAdnExperience.split("\\s*;\\s*"));

                                       for (String item2 : list2) {
                                   %>
                                   <li><%=item2%></li>
                                   <%
                                       } // close foreach loop
                                   %>
                               </ul>
                            </div>
                        </div>

                    </div>
                    <!-- Right Content -->
                    <div class="col-xl-4 col-lg-4">
                        <div class="post-details3  mb-50">
                            <!-- Small Section Tittle -->
                           <div class="small-section-tittle">
                               <h4>Job Overview</h4>
                           </div>
                          <ul>
                              <li>Posted date : <span><%=post.getPosted()%></span></li>
                              <li>Location : <span><%=post.getLocation()%></span></li>
                              <li>Vacancy : <span><%=post.getVacancy()%></span></li>
                              <li>Job nature : <span><%=post.getJobType()%></span></li>
                              <li>Salary :  <span><%=salary.getSalary()%></span></li>
                              <li>Application date : <span><%=post.getExpires()%></span></li>
                          </ul>
                         <div class="apply-btn2">
                            <a href="mailto:${email}" class="btn">Apply Now</a>
                         </div>
                       </div>
                        <div class="post-details4  mb-50">
                            <!-- Small Section Tittle -->
                           <div class="small-section-tittle">
                               <h4>Company Information</h4>
                           </div>
                              <span><%=post.getCompanyName()%></span>
                              <p><%=post.getCompanyDesc()%></p>
                            <ul>
                                <li>Name: <span><%=post.getCompanyName()%> </span></li>
                                <li>Web :   <span><%=post.getCompanyWeb()%></span></li>
                                <li>Email: <span><%=post.getCompanyEmail()%></span></li>
                            </ul>
                       </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- job post company End -->

    </main>

    <jsp:include page="footer.jsp" />

	<!-- JS here -->
	
		<jsp:include page="footer-js.jsp" />
        
    </body>
</html>