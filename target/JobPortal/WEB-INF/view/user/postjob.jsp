<%@ page import="com.example.JobPortal.DAO" %>
<%@ page import="java.util.Map" %>
<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />

<body>
    <div class="page-wrapper bg-dark p-t-100 p-b-50" style="background-image:
    url('${pageContext.request.contextPath}/pages/assets/img/headway-5QgIuuBxKwM-unsplash.jpg');
        background-color: rgb(61, 61, 61);
        background-blend-mode: multiply;
        background-size: cover;
        background-position: center;">
        <div class="wrapper wrapper--w900">
            <div class="card card-6">
                <div class="card-heading">
                    <h2 class="title">Post a Job Offer</h2>
                </div>
                <div class="card-body">
                    <form method="POST" action="postadded?userid=${userid}"
                          enctype="multipart/form-data">
                        <%
                            String queryString = request.getQueryString();
                            String url = request.getRequestURL() + "?" + queryString;

                            if(url.contains("error")) {
                        %>
                        <div class="form-row">
                            <p class="error-message">${errorMessage}</p>
                        </div>
                        <%
                            } //close if statement
                        %>
                        <div class="form-row">
                            <div class="name">Job Title</div>
                            <div class="value">
                                <input class="input--style-6" type="text" name="title">
                                <input class="input--style-6" type="hidden" name="userid" value="${userid}">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Job Category</div>
                            <div class="value">
                                <!-- <div class="input-group form-group"> -->
                                    <select class="nice-select select-width" name="category">
                                        <option disabled="disabled" selected="selected" value="">SELECT</option>
                                        <%
                                            DAO dao = new DAO();
                                            Map<String, String> categories = dao.getCategories();

                                            for (String c : categories.keySet()) {
                                        %>
                                        <option value="<%=c%>"><%=categories.get(c)%></option>
                                        <%
                                            } //close for statement
                                        %>
                                    </select>
                                    <!-- <input class="input--style-6" type="email" name="email" placeholder="example@email.com"> -->
                                <!-- </div> -->
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Job Type</div>
                            <div class="value">
                                <div class="input-group">
                                    <select class="nice-select select-width" name="jobtype">
                                        <option disabled="disabled" selected="selected" value="">SELECT</option>
                                        <%
                                            Map<String, String> jobTypes = dao.getJobTypes();
                                            for(String jt : jobTypes.keySet()) {
                                        %>
                                        <option value="<%=jt%>"><%=jobTypes.get(jt)%></option>
                                        <%
                                            } //close for statement
                                        %>
                                    </select>
                                    <!-- <input class="input--style-6" type="email" name="email" placeholder="example@email.com"> -->
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Salary</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="text" name="salary" placeholder="Salary">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">City</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="text" name="city" placeholder="Ex. Prishtine">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Country</div>
                            <div class="value">
                                <!-- <div class="input-group form-group"> -->
                                    <select class="nice-select select-width" name="country">
                                        <option disabled="disabled" selected="selected" value="">SELECT</option>
                                        <option value="Kosovo">Kosovo</option>
                                        <option value="Albania">Albania</option>
                                    </select>
                                    <!-- <input class="input--style-6" type="email" name="email" placeholder="example@email.com"> -->
                                <!-- </div> -->
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Vacancy</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="text" name="vacancy" placeholder="Vacancy">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Job Description</div>
                            <div class="value">
                                <div class="input-group">
                                    <textarea class="textarea--style-6" name="description"
                                              placeholder="Job description (Add ; for line break)"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Knowledge, skills and abilities</div>
                            <div class="value">
                                <div class="input-group">
                                    <textarea class="textarea--style-6" name="ksa"
                                              placeholder="Knowledge, skills and abilities required (Add ; for line break)"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Education and experiece</div>
                            <div class="value">
                                <div class="input-group">
                                    <textarea class="textarea--style-6" name="ex"
                                              placeholder="Education and experiece required (Add ; for line break)"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Expires</div>
                            <div class="value">
                                <input class="input--style-6" type="date" name="expires">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Upload logo</div>
                            <div class="value">
                                <div class="input-group js-input-file">
                                    <input class="input-file" type="file" name="logo" id="file">
                                    <label class="label--file" for="file">Choose file</label>
                                    <span class="input-file__info">No file chosen</span>
                                </div>
                                <div class="label--desc">Upload your company logo image. Max image size 10 MB</div>
                            </div>
                        </div>
                        <div class="card-footer">
                            <button class="btn btn--radius-2 btn--blue-2" type="submit">Post Job</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Jquery JS-->
    <jsp:include page="footer.jsp" />

    <jsp:include page="footer-js.jsp" />

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->