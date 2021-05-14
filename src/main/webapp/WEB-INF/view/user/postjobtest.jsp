
<jsp:include page="header.jsp" />

<body>

<%--    <!-- Preloader Start -->--%>
<%--    <div id="preloader-active">--%>
<%--        <div class="preloader d-flex align-items-center justify-content-center">--%>
<%--            <div class="preloader-inner position-relative">--%>
<%--                <div class="preloader-circle"></div>--%>
<%--                <div class="preloader-img pere-text">--%>
<%--                    <img src="${pageContext.request.contextPath}/pages/assets/img/logo/logo.png" alt="logo">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
    <!-- Preloader Start -->

    <!-- HEADER/NAV -->
    <jsp:include page="nav.jsp" />


    <div class="row row-custom">
        <!-- <div class="row-img"><img src="assets/img/headway-5QgIuuBxKwM-unsplash.jpg" alt="Image"></div> -->
        <div class="col-12">
            <h2 class="contact-title">Post a job advertisement</h2>
        </div>
        <div class="col-lg-8 col-lg-8-custom">
            <form class="form-contact contact_form" action="../../../pages/contact_process.php" method="post" id="contactForm" novalidate="novalidate">
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <p class="error-display">error to be displayed</p>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <input class="form-control valid input-white" name="name" id="name" type="text" placeholder="Job Title">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group select">
                            <!-- <input class="form-control valid input-white" name="name" id="name" type="text" placeholder="Job Type"> -->
                            <select class="form-control nice-select input-white --form-group-form-control-select"
                                    name="job-type">
                                <option class="option" value="">Job Category</option>
                                <option class="option" value="part-time">IT</option>
                                <option class="option" value="full-time">Call Center</option>
                                <option class="option" value="remote">Marketing dhe Reklama</option>
                                <option class="option" value="remote">Administrate</option>
                                <option class="option" value="remote">Arsim dhe Edukim</option>
                                <option class="option" value="remote">Telekomunikacion</option>
                                <option class="option" value="remote">Prodhimtari</option>
                                <option class="option" value="remote">Ndertimtari</option>
                                <option class="option" value="remote">Media</option>
                                <option class="option" value="remote">Media</option>
                                <option class="option" value="remote">Media</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group" >
                            <!-- <input class="form-control valid input-white" name="name" id="name" type="text" placeholder="Job Type"> -->
                            <select class="form-control input-white --form-group-form-control-select" name="job-type" id="job-type">
                                <option class="option" value="">Job Type</option>
                                <option class="option" value="part-time">Part Time</option>
                                <option class="option" value="full-time">Full Time</option>
                                <option class="option" value="remote">Remote</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <input class="form-control valid input-white" name="salary" id="salary" type="email"
                                   placeholder="Salary">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <input class="form-control valid input-white" name="city" id="city" type="email"
                                   placeholder="Salary">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group" id="select-country">
                            <!-- <input class="form-control valid input-white" name="name" id="name" type="text" placeholder="Job Type"> -->
                            <select class="form-control input-white --form-group-form-control-select" name="job-type" id="country">
                                <option class="option" value="">Country</option>
                                <option class="option" value="part-time">Kosovo</option>
                                <option class="option" value="full-time">Albania</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group form-group--label-input">
                            <input class="form-control valid input-white" name="logo" id="logo" type="file"
                                   placeholder="Company Logo">
                            <label for="logo">Company Logo</label>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <textarea class="form-control w-100 input-white" name="description" id="description" cols="30"
                                      rows="9"  placeholder="Job description" />
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <textarea class="form-control w-100 input-white" name="ksa" id="ksa" cols="30" rows="9"
                                      placeholder="Knowledge, skills and abilities required" />
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <textarea class="form-control w-100 input-white" name="exr" id="exr" cols="30" rows="9"
                                      placeholder="Education and experiece required" />
                        </div>
                    </div>
                </div>
                <div class="form-group mt-3">
                    <button type="submit" class="button button-contactForm boxed-btn transparent-btn">POST</button>
                </div>
            </form>
        </div>
    </div>


    <jsp:include page="footer.jsp" />

    <jsp:include page="footer-js.jsp" />
    
</body>
</html>